package splash.utils;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Box;
import org.joml.Quaternionf;
import splash.ClearHitboxes;

public class Render {

    public static void drawBox(MatrixStack matrix, Entity entity, float red, float green, float blue, float red2, float green2, float blue2, float alpha, float lineWidth) {

        Box box = entity.getBoundingBox().offset(-entity.getX(), -entity.getY(), -entity.getZ());

        try {
            BoxUtils.drawBoxThin(matrix, box.maxX, box.maxY, box.maxZ, box.minX, box.minY, box.minZ, red, green, blue, alpha, red2, green2, blue2, lineWidth);
        }catch (NullPointerException e) {
            ClearHitboxes.LOGGER.info("An Error has occurred while trying to render Hitboxes. "+e);
        }
    }

    public static void drawEntity2(int x, int y, int size, float mouseX, float mouseY, LivingEntity entity) {
        double timeState = Math.ceil(System.currentTimeMillis());
        timeState %= 18000;
        float rotation = (float) (timeState/1000);
        rotation %= 360;
        float f = (float)Math.atan(mouseX / 40.0F);
        float g = (float)Math.atan(mouseY / 40.0F);
        MatrixStack matrixStack = RenderSystem.getModelViewStack();
        matrixStack.push();
        matrixStack.translate((float)x, (float)y, 1050.0F);
        matrixStack.scale(1.0F, 1.0F, -1.0F);
        RenderSystem.applyModelViewMatrix();
        MatrixStack matrixStack2 = new MatrixStack();
        matrixStack2.translate(0.0F, 0.0F, 1000.0F);
        matrixStack2.scale((float)size, (float)size, (float)size);

        Quaternionf quaternionf = (new Quaternionf()).rotateZ(3.1415927F);
        Quaternionf quaternionf2 = (new Quaternionf()).rotateX(g * 20.0F * 0.017453292F);
        quaternionf.mul(quaternionf2);
        matrixStack2.multiply(quaternionf);
        float h = entity.bodyYaw;
        float i = entity.getYaw();
        float j = entity.getPitch();
        float k = entity.prevHeadYaw;
        float l = entity.headYaw;

        entity.bodyYaw = (180.0F + rotation * 40.0F);
        entity.setYaw(180.0F + rotation * 40.0F);
        entity.setPitch(-g * 20.0F);
        entity.headYaw = entity.getYaw();
        entity.prevHeadYaw = entity.getYaw();
        DiffuseLighting.method_34742();
        EntityRenderDispatcher entityRenderDispatcher = MinecraftClient.getInstance().getEntityRenderDispatcher();
        quaternionf2.conjugate();
        entityRenderDispatcher.setRotation(quaternionf2);
        entityRenderDispatcher.setRenderShadows(false);
        VertexConsumerProvider.Immediate immediate = MinecraftClient.getInstance().getBufferBuilders().getEntityVertexConsumers();

        RenderSystem.runAsFancy(() -> {
            entityRenderDispatcher.render(entity, 0.0, 0.0, 0.0, 0.0F, 1.0F, matrixStack2, immediate, 15728880);
        });
        immediate.draw();
        entityRenderDispatcher.setRenderShadows(true);
        entity.bodyYaw = h;
        entity.setYaw(i);
        entity.setPitch(j);
        entity.prevHeadYaw = k;
        entity.headYaw = l;

        matrixStack.pop();
        RenderSystem.applyModelViewMatrix();
        DiffuseLighting.enableGuiDepthLighting();
    }

    public static void drawString(DrawContext context, final float scale, final String text, final float xPos, final float yPos, final int color) {
        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
        context.getMatrices().push();
        //matrices.push();
        context.getMatrices().scale(scale, scale, scale);
        //matrices.scale(scale, scale, scale);
        context.drawText(textRenderer, text, (int) (xPos/scale), (int) (yPos/scale), color, false);
        context.getMatrices().pop();
        //matrices.pop();
    }
    public static void drawShadowString(DrawContext context, final float scale, final String text, final float xPos, final float yPos, final int color) {

        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
        context.getMatrices().push();
        //matrices.push();
        context.getMatrices().scale(scale, scale, scale);
        //matrices.scale(scale, scale, scale);
        context.drawTextWithShadow(textRenderer, text, (int) (xPos/scale), (int) (yPos/scale), color);
        context.getMatrices().pop();
        //matrices.pop();
    }

    public static void drawPlayer(int x, int y, float scale, int mouseX, int mouseY){
        drawEntity2(x, y, (int) scale, mouseX, mouseY, MinecraftClient.getInstance().player);
    }
}

