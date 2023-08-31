package splash.utils;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.PressableTextWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public abstract class ToggleWidget extends PressableTextWidget {
    protected boolean toggled;
    final Identifier on = new Identifier("clearhitboxes", "textures/on.png");
    final Identifier off = new Identifier("clearhitboxes", "textures/off.png");
    public ToggleWidget(int x, int y, int width, int height, Boolean toggled) {
        super(x, y, width, height, Text.of(String.valueOf(toggled)), ButtonWidget::onPress, MinecraftClient.getInstance().textRenderer);
        setAlpha(0.8F);
        this.toggled = toggled;
    }

    @Override
    public void renderButton(DrawContext context, int mouseX, int mouseY, float delta) {
        MinecraftClient instance = MinecraftClient.getInstance();

        int x1 = getX();
        int y1 = getY();
        int x2 = getX() + width;
        int y2 = getY() + height;

        if(!toggled){
            RenderSystem.setShaderTexture(0, off);
           instance.getTextureManager().bindTexture(off);
            context.drawTexture(off,  x1, y1-11, 0, 0,width,27,width, 27);
        } else {
            RenderSystem.setShaderTexture(0, on);
            instance.getTextureManager().bindTexture(on);
            context.drawTexture(on, x1, y1-11, 0, 0,width,27,width, 27);
        }
        if (!active) {
            context.fill(x1, y1, x2, y2, 0x50303030);
        }
    }
}