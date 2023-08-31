package splash.mixin;

import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import splash.config.ClearHitboxesConfig;
import splash.utils.ColorUtils;
import splash.utils.Render;

import java.awt.*;

@Mixin(EntityRenderDispatcher.class)
public class HitboxMixin {


    @Inject(method = "renderHitbox", at = @At("HEAD"), cancellable = true)
    private static void renderHitbox(MatrixStack matrices, VertexConsumer vertices, Entity entity, float tickDelta, CallbackInfo ci) {
        float r = (float) (ClearHitboxesConfig.red/255);
        float g = (float) (ClearHitboxesConfig.green/255);
        float b = (float) (ClearHitboxesConfig.blue/255);
        float a = (float) (ClearHitboxesConfig.alpha/255);

        if(ClearHitboxesConfig.enableClearHitboxes) {
            if (!ClearHitboxesConfig.enableChroma) {
                Render.drawBox(matrices, entity, r, g, b, r, g, b, a, ClearHitboxesConfig.hitboxSize);
                ci.cancel();
            } else if (!ClearHitboxesConfig.enableGradient) {
                final int color = ColorUtils.rainbow(0*300);
                float R = (color >> 16 & 255) / 255.0f;
                float G = (color >> 8 & 255) / 255.0f;
                float B = (color & 255) / 255.0f;
                Render.drawBox(matrices, entity, R, G, B, R, G, B, a, ClearHitboxesConfig.hitboxSize);

            } else {
                    double loopTime = System.currentTimeMillis() % 10000L / 10000.0f;
                    double loopTime2 = System.currentTimeMillis() % 10000L / 10000.0f + ClearHitboxesConfig.rgbDelay;

                    Color rgb = ColorUtils.getRainbow(loopTime);
                    Color rgb2 = ColorUtils.getRainbow(loopTime2);
                    Render.drawBox(matrices, entity, rgb.getRed(), rgb.getGreen(), rgb.getBlue(), rgb2.getRed(), rgb2.getGreen(), rgb2.getBlue(), a, ClearHitboxesConfig.hitboxSize);

                }
                ci.cancel();
            }
        }
    }



