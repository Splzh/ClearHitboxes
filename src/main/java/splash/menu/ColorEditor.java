package splash.menu;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.RotationAxis;
import splash.config.ClearHitboxesConfig;
import splash.config.ConfigUpdate;
import splash.utils.*;

import java.awt.*;

public class ColorEditor extends Screen {
    protected final Screen parent;
    public ColorEditor(Screen parent, Text title) {
        super(title);
        this.parent = parent;
    }

    public void init()
    {
        /** --- Tab List --- */
        this.addDrawableChild(new CHButtonWidget(width/2-20+2+22, height/2-100+6, 45, 15, "Colors") {

            @Override
            public void onPress() {
                playDownSound(MinecraftClient.getInstance().getSoundManager());
                client.setScreen(new ColorEditor(parent, Text.empty()));
            }

        });
        this.addDrawableChild(new CHButtonWidget(width/2+120-5-2-62, height/2-100+6, 45, 15, "Extras") {

            @Override
            public void onPress() {
                playDownSound(MinecraftClient.getInstance().getSoundManager());
                client.setScreen(new ExtraEditor(parent, Text.empty()));
            }
        });

        /** --- Button List --- */
        this.addDrawableChild(new HTSliderWidget(width/2+62, height/2-55, 45, 8, ClearHitboxesConfig.red/255, Color.RED) {

            @Override
            protected void updateMessage() {
                setMessage(Text.empty());

            }

            @Override
            protected void applyValue() {
                setValue(this.value);
                ClearHitboxesConfig.red = value*255;
            }
        });
        this.addDrawableChild(new HTSliderWidget(width/2+62, height/2-40, 45, 8, ClearHitboxesConfig.green/255, Color.GREEN) {

            @Override
            protected void updateMessage() {
                setMessage(Text.empty());

            }

            @Override
            protected void applyValue() {
                setValue(this.value);
                ClearHitboxesConfig.green = value*255;
            }

        });
        this.addDrawableChild(new HTSliderWidget(width/2+62, height/2-25, 45, 8, ClearHitboxesConfig.blue/255, Color.BLUE) {

            @Override
            protected void updateMessage() {
                setMessage(Text.empty());

            }

            @Override
            protected void applyValue() {
                setValue(this.value);
                ClearHitboxesConfig.blue = value*255;
            }
        });
        this.addDrawableChild(new HTSliderWidget(width/2+62, height/2-10, 45, 8, ClearHitboxesConfig.alpha/255, Color.GRAY) {

            @Override
            protected void updateMessage() {
                setMessage(Text.empty());

            }

            @Override
            protected void applyValue() {
                setValue(this.value);
                ClearHitboxesConfig.alpha = value*255;
            }
        });
        this.addDrawableChild(new ToggleWidget(width/2+70, height/2+20, 27, 10, ClearHitboxesConfig.enableChroma) {

            @Override
            public void onPress() {
                playDownSound(MinecraftClient.getInstance().getSoundManager());
                ClearHitboxesConfig.enableChroma = !ClearHitboxesConfig.enableChroma;
                client.setScreen(client.currentScreen);
            }
        });
        this.addDrawableChild(new ToggleWidget(width/2+70, height/2+35, 27, 10, ClearHitboxesConfig.enableGradient) {

            @Override
            public void onPress() {
                playDownSound(MinecraftClient.getInstance().getSoundManager());
                ClearHitboxesConfig.enableGradient = !ClearHitboxesConfig.enableGradient;
                client.setScreen(client.currentScreen);
            }
        });
        this.addDrawableChild(new HTSliderWidget(width/2+62, height/2+50, 45, 8, ClearHitboxesConfig.gradientSpeed, Color.GRAY) {

            @Override
            protected void updateMessage() {
                setMessage(Text.empty());

            }

            @Override
            protected void applyValue() {
                setValue(this.value);
                ClearHitboxesConfig.gradientSpeed = value;
            }
        });
        this.addDrawableChild(new HTSliderWidget(width/2+62, height/2+65, 45, 8, ClearHitboxesConfig.rgbIntensity, Color.GRAY) {

            @Override
            protected void updateMessage() {
                setMessage(Text.empty());

            }

            @Override
            protected void applyValue() {
                setValue(this.value);
                ClearHitboxesConfig.rgbIntensity = value;
            }
        });
        this.addDrawableChild(new HTSliderWidget(width/2+62, height/2+80, 45, 8, ClearHitboxesConfig.rgbDelay, Color.GRAY) {

            @Override
            protected void updateMessage() {
                setMessage(Text.empty());

            }

            @Override
            protected void applyValue() {
                setValue(this.value);
                ClearHitboxesConfig.rgbDelay = value;
            }
        });
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float tickDelta) {
        Color BACKGROUND = new Color(166, 166, 166, 200);
        Color darkGray = new Color(100, 100, 100, 179);
        Color shadow = new Color(0, 0, 0, 255);
        Color shade = new Color(49, 49, 49, 215);
        Color light = new Color(168, 168, 168, 255);
        Color lighter = new Color(182, 182, 182, 255);
        Color extraColor = new Color((int) ClearHitboxesConfig.red, (int) ClearHitboxesConfig.green, (int) ClearHitboxesConfig.blue, (int) ClearHitboxesConfig.alpha);

        int menuX = width/2+120;
        int menuX2 = width/2-120;
        int menuY = height/2+100;
        int menuY2 = height/2-100;


        /** --- Menu Background --- */
        fill(matrices,menuX, menuY, menuX2, menuY2, BACKGROUND.getRGB());
        //fillGradient(matrices, menuX, menuY, menuX2, menuY2, BACKGROUNDFADE.getRGB(), Color.TRANSLUCENT);
        fill(matrices,menuX, menuY, menuX2,menuY + 1, shadow.getRGB());
        fill(matrices,menuX, menuY, menuX2,menuY- 2, darkGray.getRGB());
        fill(matrices,menuX, menuY2, menuX2,menuY2- 1, light.getRGB());
        fill(matrices,menuX-2,menuY2,menuX2,menuY2+ 2, lighter.getRGB());
        fill(matrices,menuX,menuY+ 1,menuX + 1, menuY2, shadow.getRGB());
        fill(matrices,menuX,menuY-1,menuX -2, menuY2- 1, darkGray.getRGB());
        fill(matrices,menuX2,menuY,menuX2- 1, menuY2 - 1, light.getRGB());
        fill(matrices,menuX2,menuY,menuX2+2,menuY2 + 2, lighter.getRGB());

        /** --- Settings Overlay --- */
        fill(matrices,width/2-20,menuY2+25,menuX-5,menuY -5, shade.getRGB());
        fill(matrices,menuX-8,height/2-56,menuX-9,height/2-1, darkGray.getRGB());
        fill(matrices,menuX-8,height/2+19,menuX-9,height/2+91, darkGray.getRGB());
        fill(matrices,width/2-20+2,height/2-73,menuX-5-2,height/2-60, darkGray.getRGB());
        fill(matrices,width/2-20+2,height/2+2,menuX-5-2,height/2+15, darkGray.getRGB());
        fill(matrices,width/2-16,height/2-56,width/2-17,height/2-1, darkGray.getRGB());
        fill(matrices,width/2-16,height/2+19,width/2-17,height/2+91, darkGray.getRGB());

        /** --- Button Label --- */
        fill(matrices,width/2-20+2+22, height/2-100+6, width/2-20+2+22+45, height/2-100+6+15, shade.getRGB());

        /** --- Finishing Touches --- */
        MenuUtils.drawPreview(matrices, menuX2+50, height/2+70);
        Render.drawShadowString(matrices, 1.2f, "Clear Hitboxes",  menuX2+10, menuY2+10,Color.white.getRGB());
        matrices.push();
        matrices.translate((float)(menuX2+10+textRenderer.getWidth("Clear Hitboxes")*100/80-10), menuY2+3F, 0.0F);
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(20.0F));
        matrices.pop();

        /** --- Settings Information --- */
        Render.drawString(matrices, 1f, "Basic Colors",  width/2-10+60-textRenderer.getWidth("Basic Colors")/2, height/2-70, extraColor.getRGB());
        Render.drawShadowString(matrices,1.0f, "Red", (float) (width/2-10), height/2-55, Color.white.getRGB());
        Render.drawShadowString(matrices,1.0f, "Green", (float) (width/2-10), height/2-40, Color.white.getRGB());
        Render.drawShadowString(matrices, 1.0f, "Blue", (float) (width/2-10), height/2-25, Color.white.getRGB());
        Render.drawShadowString(matrices, 1.0f, "Alpha", (float) (width/2-10), height/2-10, Color.white.getRGB());
        if(ClearHitboxesConfig.enableChroma){
            Render.drawShadowString(matrices, 1f, "Chroma Settings", width/2-10+60-textRenderer.getWidth("Chroma Settings")/2, height/2+05, ColorUtils.rainbow(0*300));
        } else{
            Render.drawString(matrices, 1f, "Chroma Settings", width/2-10+60-textRenderer.getWidth("Chroma Settings")/2, height/2+05, light.getRGB());
        }
        Render.drawShadowString(matrices,0.8f, "Enable Chroma", (float) (width/2-10), height/2+20, Color.white.getRGB());
        Render.drawShadowString(matrices, 0.8f, "Enable Gradient", (float) (width/2-10), height/2+35, Color.white.getRGB());
        Render.drawShadowString(matrices, 0.8f, "Gradient Speed", (float) (width/2-10), height/2+50, Color.white.getRGB());
        Render.drawShadowString(matrices, 0.7f, "Chroma Brightness", (float) (width/2-10), height/2+65, Color.white.getRGB());
        Render.drawShadowString(matrices, 0.8f, "Chroma Delay", (float) (width/2-10), height/2+80, Color.white.getRGB());

        super.render(matrices, mouseX, mouseY, tickDelta);
    }


    public void close() {
        this.client.setScreen(this.parent);
        ConfigUpdate.run();
    }

}
