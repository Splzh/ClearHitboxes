package splash.utils;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Matrix3f;
import net.minecraft.util.math.Matrix4f;


public class BoxUtils {
    static void startDrawingLines(BufferBuilder buffer)
    {
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        RenderSystem.applyModelViewMatrix();
        buffer.begin(VertexFormat.DrawMode.DEBUG_LINES, VertexFormats.POSITION_COLOR);
    }
    public static void drawBoxThin(MatrixStack matrices, double x1, double y1, double z1, double x2, double y2, double z2, float red, float green, float blue, float alpha, float red2, float green2, float blue2, float lineWidth)
    {
        Matrix4f matrix4f = matrices.peek().getPositionMatrix();
        Matrix3f matrix3f = matrices.peek().getNormalMatrix();
        float f = (float)x1;
        float g = (float)y1;
        float h = (float)z1;
        float i = (float)x2;
        float j = (float)y2;
        float k = (float)z2;

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        RenderSystem.enableCull();
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableTexture();
        RenderSystem.enableDepthTest();
        RenderSystem.enableCull();
        RenderSystem.depthMask(true);

        startDrawingLines(buffer);
        buffer.vertex(matrix4f, f, g, h).color(red, green, blue, alpha).normal(matrix3f, 1.0F, 0.0F, 0.0F).next();
        buffer.vertex(matrix4f, i, g, h).color(red, green, blue, alpha).normal(matrix3f, 1.0F, 0.0F, 0.0F).next();
        buffer.vertex(matrix4f, f, g, h).color(red, green, blue, alpha).normal(matrix3f, 0.0F, 1.0F, 0.0F).next();
        buffer.vertex(matrix4f, f, j, h).color(red2, green2, blue2, alpha).normal(matrix3f, 0.0F, 1.0F, 0.0F).next();

        buffer.vertex(matrix4f, f, g, h).color(red, green, blue, alpha).normal(matrix3f, 0.0F, 0.0F, 1.0F).next();
        buffer.vertex(matrix4f, f, g, k).color(red, green, blue, alpha).normal(matrix3f, 0.0F, 0.0F, 1.0F).next();
        buffer.vertex(matrix4f, i, g, h).color(red, green, blue, alpha).normal(matrix3f, 0.0F, 1.0F, 0.0F).next();
        buffer.vertex(matrix4f, i, j, h).color(red2, green2, blue2, alpha).normal(matrix3f, 0.0F, 1.0F, 0.0F).next();

        buffer.vertex(matrix4f, i, j, h).color(red2, green2, blue2, alpha).normal(matrix3f, -1.0F, 0.0F, 0.0F).next(); /**LOW2*/
        buffer.vertex(matrix4f, f, j, h).color(red2, green2, blue2, alpha).normal(matrix3f, -1.0F, 0.0F, 0.0F).next(); /**LOW2*/
        buffer.vertex(matrix4f, f, j, h).color(red2, green2, blue2, alpha).normal(matrix3f, 0.0F, 0.0F, 1.0F).next(); /**LOW1*/
        buffer.vertex(matrix4f, f, j, k).color(red2, green2, blue2, alpha).normal(matrix3f, 0.0F, 0.0F, 1.0F).next(); /**LOW1*/

        buffer.vertex(matrix4f, f, j, k).color(red2, green2, blue2, alpha).normal(matrix3f, 0.0F, -1.0F, 0.0F).next();
        buffer.vertex(matrix4f, f, g, k).color(red, green, blue, alpha).normal(matrix3f, 0.0F, -1.0F, 0.0F).next();
        buffer.vertex(matrix4f, f, g, k).color(red, green, blue, alpha).normal(matrix3f, 1.0F, 0.0F, 0.0F).next(); /**TOP2*/
        buffer.vertex(matrix4f, i, g, k).color(red, green, blue, alpha).normal(matrix3f, 1.0F, 0.0F, 0.0F).next(); /**TOP2*/

        buffer.vertex(matrix4f, i, g, k).color(red, green, blue, alpha).normal(matrix3f, 0.0F, 0.0F, -1.0F).next(); /**TOP1*/
        buffer.vertex(matrix4f, i, g, h).color(red, green, blue, alpha).normal(matrix3f, 0.0F, 0.0F, -1.0F).next(); /**TOP1*/
        buffer.vertex(matrix4f, f, j, k).color(red2, green2, blue2, alpha).normal(matrix3f, 1.0F, 0.0F, 0.0F).next(); /**LOW4*/
        buffer.vertex(matrix4f, i, j, k).color(red2, green2, blue2, alpha).normal(matrix3f, 1.0F, 0.0F, 0.0F).next(); /**LOW4*/

        buffer.vertex(matrix4f, i, g, k).color(red, green, blue, alpha).normal(matrix3f, 0.0F, 1.0F, 0.0F).next();
        buffer.vertex(matrix4f, i, j, k).color(red2, green2, blue2, alpha).normal(matrix3f, 0.0F, 1.0F, 0.0F).next();
        buffer.vertex(matrix4f, i, j, h).color(red2, green2, blue2, alpha).normal(matrix3f, 0.0F, 0.0F, 1.0F).next(); /**LOW3*/
        buffer.vertex(matrix4f, i, j, k).color(red2, green2, blue2, alpha).normal(matrix3f, 0.0F, 0.0F, 1.0F).next(); /**LOW3*/

        tessellator.draw();
    }

    public static void drawBox(MatrixStack matrices, VertexConsumer buffer, double x1, double y1, double z1, double x2, double y2, double z2, float red, float green, float blue, float alpha, float red2, float green2, float blue2, float lineWidth) {
        Matrix4f matrix4f = matrices.peek().getPositionMatrix();
        Matrix3f matrix3f = matrices.peek().getNormalMatrix();
        float f = (float)x1;
        float g = (float)y1;
        float h = (float)z1;
        float i = (float)x2;
        float j = (float)y2;
        float k = (float)z2;

        matrices.push();
        RenderSystem.lineWidth(lineWidth * 0.5F);
        buffer.vertex(matrix4f, f, g, h).color(red, green, blue, alpha).normal(matrix3f, 1.0F, 0.0F, 0.0F).next();
        buffer.vertex(matrix4f, i, g, h).color(red, green, blue, alpha).normal(matrix3f, 1.0F, 0.0F, 0.0F).next();
        buffer.vertex(matrix4f, f, g, h).color(red, green, blue, alpha).normal(matrix3f, 0.0F, 1.0F, 0.0F).next();
        buffer.vertex(matrix4f, f, j, h).color(red2, green2, blue2, alpha).normal(matrix3f, 0.0F, 1.0F, 0.0F).next();

        buffer.vertex(matrix4f, f, g, h).color(red, green, blue, alpha).normal(matrix3f, 0.0F, 0.0F, 1.0F).next();
        buffer.vertex(matrix4f, f, g, k).color(red, green, blue, alpha).normal(matrix3f, 0.0F, 0.0F, 1.0F).next();
        buffer.vertex(matrix4f, i, g, h).color(red, green, blue, alpha).normal(matrix3f, 0.0F, 1.0F, 0.0F).next();
        buffer.vertex(matrix4f, i, j, h).color(red2, green2, blue2, alpha).normal(matrix3f, 0.0F, 1.0F, 0.0F).next();

        buffer.vertex(matrix4f, i, j, h).color(red2, green2, blue2, alpha).normal(matrix3f, -1.0F, 0.0F, 0.0F).next(); /**LOW2*/
        buffer.vertex(matrix4f, f, j, h).color(red2, green2, blue2, alpha).normal(matrix3f, -1.0F, 0.0F, 0.0F).next(); /**LOW2*/
        buffer.vertex(matrix4f, f, j, h).color(red2, green2, blue2, alpha).normal(matrix3f, 0.0F, 0.0F, 1.0F).next(); /**LOW1*/
        buffer.vertex(matrix4f, f, j, k).color(red2, green2, blue2, alpha).normal(matrix3f, 0.0F, 0.0F, 1.0F).next(); /**LOW1*/

        buffer.vertex(matrix4f, f, j, k).color(red2, green2, blue2, alpha).normal(matrix3f, 0.0F, -1.0F, 0.0F).next();
        buffer.vertex(matrix4f, f, g, k).color(red, green, blue, alpha).normal(matrix3f, 0.0F, -1.0F, 0.0F).next();
        buffer.vertex(matrix4f, f, g, k).color(red, green, blue, alpha).normal(matrix3f, 1.0F, 0.0F, 0.0F).next(); /**TOP2*/
        buffer.vertex(matrix4f, i, g, k).color(red, green, blue, alpha).normal(matrix3f, 1.0F, 0.0F, 0.0F).next(); /**TOP2*/

        buffer.vertex(matrix4f, i, g, k).color(red, green, blue, alpha).normal(matrix3f, 0.0F, 0.0F, -1.0F).next(); /**TOP1*/
        buffer.vertex(matrix4f, i, g, h).color(red, green, blue, alpha).normal(matrix3f, 0.0F, 0.0F, -1.0F).next(); /**TOP1*/
        buffer.vertex(matrix4f, f, j, k).color(red2, green2, blue2, alpha).normal(matrix3f, 1.0F, 0.0F, 0.0F).next(); /**LOW4*/
        buffer.vertex(matrix4f, i, j, k).color(red2, green2, blue2, alpha).normal(matrix3f, 1.0F, 0.0F, 0.0F).next(); /**LOW4*/

        buffer.vertex(matrix4f, i, g, k).color(red, green, blue, alpha).normal(matrix3f, 0.0F, 1.0F, 0.0F).next();
        buffer.vertex(matrix4f, i, j, k).color(red2, green2, blue2, alpha).normal(matrix3f, 0.0F, 1.0F, 0.0F).next();
        buffer.vertex(matrix4f, i, j, h).color(red2, green2, blue2, alpha).normal(matrix3f, 0.0F, 0.0F, 1.0F).next(); /**LOW3*/
        buffer.vertex(matrix4f, i, j, k).color(red2, green2, blue2, alpha).normal(matrix3f, 0.0F, 0.0F, 1.0F).next(); /**LOW3*/

        matrices.pop();
    }


}
