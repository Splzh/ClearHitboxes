package splash.mixin;

import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import splash.config.Config;
import splash.utils.ColorUtils;
import splash.utils.Render;

import java.awt.*;

@Mixin(EntityRenderDispatcher.class)
public class HitboxMixin {


    @Inject(method = "renderHitbox", at = @At("HEAD"), cancellable = true)
    private static void renderHitbox(MatrixStack matrices, VertexConsumer vertices, Entity entity, float tickDelta, CallbackInfo ci) {
        Config config = AutoConfig.getConfigHolder(Config.class).getConfig();
        float r = 256 - config.red;
        float g = 256 - config.green;
        float b = 256 - config.blue;
        float a = config.alpha;

        if (config == null) {
            config = AutoConfig.getConfigHolder(Config.class).getConfig();
        }
        if (config.hitbox) {
            if (!config.hitboxRGB) {
                Render.drawBox(matrices, vertices, entity, r, g, b, r, g, b, a, 1);
                ci.cancel();
            } else if (!config.gradient) {
                final int color = ColorUtils.rainbow(0*300);
                float R = (color >> 16 & 255) / 255.0f;
                float G = (color >> 8 & 255) / 255.0f;
                float B = (color & 255) / 255.0f;
                Render.drawBox(matrices, vertices, entity, R, G, B, R, G, B, config.alpha, 1);
            } else {
                    double loopTime = System.currentTimeMillis() % 10000L / 10000.0f;
                    double loopTime2 = System.currentTimeMillis() % 10000L / 10000.0f + config.gradientDelay;

                    Color rgb = ColorUtils.getRainbow(loopTime);
                    Color rgb2 = ColorUtils.getRainbow(loopTime2);
                    Render.drawBox(matrices, vertices, entity, rgb.getRed(), rgb.getGreen(), rgb.getBlue(), rgb2.getRed(), rgb2.getGreen(), rgb2.getBlue(), config.alpha, 1);
                }
                ci.cancel();
            }
        }
    }



