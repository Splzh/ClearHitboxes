package splash.utils;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;

import java.awt.*;


@Environment(EnvType.CLIENT)
public abstract class CHSliderWidget extends SliderWidget {
    private static final int HANDLE_WIDTH = 7;

    public CHSliderWidget(int x, int y, int width, int height, Text text, double value) {
        super(x, y, width, height, text, value);
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
        fill(matrices,x1,     y1,     x2,     y1 + 1, 0xFF000000);
        fill(matrices,x1,     y2,     x2,     y2 - 1, 0xFF000000);
        fill(matrices,x1,     y1 + 1, x1 + 1, y2 - 1, 0xFF000000);
        fill(matrices,x2,     y1 + 1, x2 - 1, y2 - 1, 0xFF000000);
        renderBg(matrices,minecraftClient, mouseX, mouseY);

        drawCenteredText(matrices,textRenderer, textRenderer.trimToWidth(getMessage(), width).getString(), this.getX() + width / 2, this.getY() + (height - 8) / 2, Color.WHITE.getRGB() | MathHelper.ceil(alpha * 255.0F) << 24);
        if (!active) {
            fill(matrices,x1, y1, x2, y2, 0x50303030);
        }
    }

    protected void renderBg(MatrixStack matrices, MinecraftClient client, int mouseX, int mouseY) {
        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
        int x1 = this.getX() + (int) (value * (width - HANDLE_WIDTH));
        int y1 = this.getY();
        int x2 = x1 + HANDLE_WIDTH;
        int y2 = y1 + height;
        int color = isHovered() && active ? 0xFFFFFFFF : 0xFF000000;
        fill(matrices,x1,     y1,     x2,     y1 + 1, color);
        fill(matrices,x1,     y2,     x2,     y2 - 1, color);
        fill(matrices,x1,     y1 + 1, x1 + 1, y2 - 1, color);
        fill(matrices,x2,     y1 + 1, x2 - 1, y2 - 1, color);
        fill(matrices,x1 + 1, y1 + 1, x2 - 1, y2 - 1, 0x7F9F9F9F);
        drawCenteredText(matrices,textRenderer, textRenderer.trimToWidth(getMessage(), width).getString(), this.getX() + width / 2, this.getY() + (height - 8) / 2, 0x00FFFFFF | MathHelper.ceil(alpha * 255.0F) << 24);
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