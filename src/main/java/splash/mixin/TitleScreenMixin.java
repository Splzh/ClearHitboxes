package splash.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import splash.config.ClearHitboxesConfig;
import splash.menu.FirstTime;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {
    protected Screen parent;

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    public void render(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if(ClearHitboxesConfig.enableByDefault){
            EntityRenderDispatcher entityRenderDispatcher = MinecraftClient.getInstance().getEntityRenderDispatcher();
            entityRenderDispatcher.setRenderHitboxes(true);
        }
    if(ClearHitboxesConfig.firstTime){
        MinecraftClient.getInstance().setScreen(new FirstTime(parent, Text.empty()));
        ClearHitboxesConfig.firstTime = false;
    }
    }
}
