package splash.utils;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import java.awt.*;

public abstract class HTSliderWidget extends SliderWidget {
    private static final int HANDLE_WIDTH = 7;
    protected double value;
    protected Color color;
    final Identifier bubble = new Identifier("clearhitboxes", "textures/bubble.png");
    final Identifier circle = new Identifier("clearhitboxes", "textures/circle.png");
    public HTSliderWidget(int x, int y, int width, int height, double value, Color color) {
        super(x, y, width, height, Text.empty(), value);
        this.value = value;
        this.color = color;
        setAlpha(0.8F);
        updateMessage();
    }

    @Override
    public void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        TextRenderer textRenderer = minecraftClient.textRenderer;

        int x1 = this.getX();
        int y1 = this.getY();
        int x2 = this.getX() + width;
        int y2 = this.getY() + height;

        Color shadow = new Color(0x383838);
        Color light = new Color(168, 168, 168, 255);

        fill(matrices,x1,     y1,     x2,     y1 + 1, shadow.getRGB());
        fill(matrices,x1,     y2,     x2,     y2 - 1, light.getRGB());
        fill(matrices,x1,     y1 + 1, x1 + 1, y2 - 1, shadow.getRGB());
        fill(matrices,x2,     y1 + 1, x2 - 1, y2 - 1, light.getRGB());
        int j = active ? 0x00FFFFFF : 0x00A0A0A0;
        Text message = getMessage();
        drawCenteredTextWithShadow(matrices,textRenderer, (Text) textRenderer.getTextHandler().trimToWidth(message, width, message.getStyle()), this.getX() + width / 2, this.getY() + (height - 8) / 2, j | MathHelper.ceil(alpha * 255.0F) << 24);
        if (!active) {
            fill(matrices,x1, y1, x2, y2, 0x50303030);
        }
        int XX = this.getX() + (int) (value * (width - HANDLE_WIDTH));
        int YY = this.getY();

        int Y3 = YY + height;

        RenderSystem.setShaderTexture(0, circle);
        MinecraftClient.getInstance().getTextureManager().bindTexture(circle);
        fill(matrices,getX()+1, YY + 1, XX, Y3 - 1, color.getRGB());
        drawTexture(matrices, XX-10, YY-10, 0, 0,27,27,27, 27);

        if(isHovered()){
            RenderSystem.setShaderTexture(0, bubble);
            MinecraftClient.getInstance().getTextureManager().bindTexture(bubble);
            drawTexture(matrices, XX-7, YY-17, 0, 0,18,18,18, 18);
            Render.drawString(matrices,0.8f, ""+Math.round(value*10)/10D, (XX-textRenderer.getWidth(""+Math.round(value*10)/10D)+11),  YY-12, Color.black.getRGB());
        }
    }

    @Override
    public void onClick(double mouseX, double mouseY) {
        setValueFromMouse(mouseX);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        boolean bl = keyCode == 263;
        if (bl || keyCode == 262) {
            double f = bl ? -1.0 : 1.0;
            setValue(value + f / (width - HANDLE_WIDTH));
            return true;
        }

        return false;
    }

    protected void setValueFromMouse(double mouseX) {
        setValue((mouseX - (this.getX() + (HANDLE_WIDTH / 2.0D - 1.0D))) / (width - HANDLE_WIDTH));
    }

    public void setValue(double newValue) {
        double oldValue = value;
        value = MathHelper.clamp(newValue, 0.0D, 1.0D);
        if (oldValue != value) {
            applyValue();
        }

        updateMessage();
    }

    @Override
    protected void onDrag(double mouseX, double mouseY, double deltaX, double deltaY) {
        setValueFromMouse(mouseX);

    }

}