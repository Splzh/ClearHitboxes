package splash.menu;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import splash.utils.ColorUtils;
import splash.utils.CHButtonWidget;
import splash.utils.Render;

import java.awt.*;

public class FirstTime extends Screen {
    protected final Screen parent;

    public FirstTime(Screen parent, Text title) {
        super(title);
        this.parent = parent;
    }

    public void init()
    {

        /** --- Button List --- */
        this.addDrawableChild(new CHButtonWidget(width/2-40, height/2-50+75, 80, 15, "Sounds good") {

            @Override
            public void onPress() {
                playDownSound(MinecraftClient.getInstance().getSoundManager());
                close();
            }
        });
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float tickDelta) {
        Color BACKGROUND = new Color(166, 166, 166, 200);
        Color BACKGROUNDFADE = new Color(100, 100, 100, 179);
        Color darkGray = new Color(100, 100, 100, 179);
        Color shadow = new Color(0, 0, 0, 255);
        Color shade = new Color(49, 49, 49, 215);
        Color light = new Color(168, 168, 168, 255);
        Color lighter = new Color(182, 182, 182, 255);

        int menuX = width/2+95;
        int menuX2 = width/2-95;
        int menuY = height/2+50;
        int menuY2 = height/2-50;

        this.renderBackground(matrices);

        /** --- Menu Background --- */
        fill(matrices, 0, 0, width, height, shade.getRGB());

        fill(matrices,menuX, menuY, menuX2, menuY2, BACKGROUND.getRGB());
        fill(matrices,menuX, menuY, menuX2,menuY + 1, shadow.getRGB());
        fill(matrices,menuX, menuY, menuX2,menuY- 2, darkGray.getRGB());
        fill(matrices,menuX, menuY2, menuX2,menuY2- 1, light.getRGB());
        fill(matrices,menuX-2,menuY2,menuX2,menuY2+ 2, lighter.getRGB());
        fill(matrices,menuX,menuY+ 1,menuX + 1, menuY2, shadow.getRGB());
        fill(matrices,menuX,menuY-1,menuX -2, menuY2- 1, darkGray.getRGB());
        fill(matrices,menuX2,menuY,menuX2- 1, menuY2 - 1, light.getRGB());
        fill(matrices,menuX2,menuY,menuX2+2,menuY2 + 2, lighter.getRGB());

        /** --- Lines --- */
        fill(matrices,menuX-25,menuY2+25,menuX2+25,menuY2+23, BACKGROUNDFADE.getRGB());
        fill(matrices,menuX-20,menuY2+67,menuX2+20,menuY2+68, shade.getRGB());

        /** --- Messages --- */
        Render.drawShadowString(matrices, 1.2f, Formatting.BOLD+"Clear Hitboxes",  width/2-textRenderer.getWidth(Formatting.BOLD+"Clear Hitboxes")/2*100/90, menuY2+10, ColorUtils.rainbow(0*300));
        Render.drawShadowString(matrices,1.0f, "Thank you for downloading! :)",  width/2-textRenderer.getWidth("Thank you for downloading! :)")/2, menuY2+30,Color.white.getRGB());
        Render.drawShadowString(matrices,1.0f, "Press RIGHT CTRL for a menu.",  width/2-textRenderer.getWidth("Press RIGHT CTRL for a menu.")/2, menuY2+41,Color.white.getRGB());
        Render.drawShadowString(matrices,1.0f, "This can be changed in Settings.",  width/2-textRenderer.getWidth("This can be changed in settings.")/2, menuY2+52,Color.white.getRGB());
        super.render(matrices, mouseX, mouseY, tickDelta);
    }

    public void close() {
        this.client.setScreen(this.parent);

    }

}
