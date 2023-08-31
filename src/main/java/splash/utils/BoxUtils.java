package splash.utils;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import splash.config.ClearHitboxesConfig;

public class BoxUtils {

    public static void drawBoxThin(MatrixStack matrices, double x1, double y1, double z1, double x2, double y2, double z2, float red, float green, float blue, float alpha, float red2, float green2, float blue2, float lineWidth) {
        Matrix4f matrix4f = matrices.peek().getPositionMatrix();
        Matrix3f matrix3f = matrices.peek().getNormalMatrix();

        float f = (float) x1;
        float g = (float) y1;
        float h = (float) z1;
        float i = (float) x2;
        float j = (float) y2;
        float k = (float) z2;

        matrices.push();

        matrices.translate(MinecraftClient.getInstance().getCameraEntity().getX(), MinecraftClient.getInstance().getCameraEntity().getY(), MinecraftClient.getInstance().getCameraEntity().getZ());

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();

        RenderSystem.setShader(GameRenderer::getPositionColorProgram);
        RenderSystem.enableCull();
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();
        RenderSystem.disableCull();
        RenderSystem.depthMask(true);

        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        RenderSystem.applyModelViewMatrix();
        RenderSystem.setShader(GameRenderer::getRenderTypeLinesProgram);
        RenderSystem.lineWidth(ClearHitboxesConfig.hitboxSize * 2.5f);
        buffer.begin(VertexFormat.DrawMode.LINES, VertexFormats.LINES);
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

        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        tessellator.draw();
        matrices.pop();
    }
}