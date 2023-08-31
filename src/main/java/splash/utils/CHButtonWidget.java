package splash.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.PressableTextWidget;
import net.minecraft.text.Text;
import splash.config.ClearHitboxesConfig;

import java.awt.*;

public abstract class CHButtonWidget extends PressableTextWidget {
    protected final OverflowTextRenderer overflowTextRenderer;

    Color BACKGROUND = new Color(0, 0, 0, 125);

    public CHButtonWidget(int x, int y, int width, int height, String message) {
        super(x, y, width, height, Text.of(message), ButtonWidget::onPress, MinecraftClient.getInstance().textRenderer);
        setAlpha(0.8F);
        this.overflowTextRenderer = new OverflowTextRenderer(40, 40, 4, x+width/2-MinecraftClient.getInstance().textRenderer.getWidth(message)/2, y + (height - 8) / 2, width - 2);
    }


    @Override
    public void renderButton(DrawContext context, int mouseX, int mouseY, float delta) {
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        TextRenderer textRenderer = minecraftClient.textRenderer;

        int x1 = getX();
        int y1 = getY();
        int x2 = getX() + width;
        int y2 = getY() + height;

        Color color = new Color(255, 255, 255);

        if(isHovered()) {
            color = new Color((int) ClearHitboxesConfig.red, (int) ClearHitboxesConfig.green, (int) ClearHitboxesConfig.blue);
            context.fill(x1, y1, x2, y2, BACKGROUND.getRGB());
            context.fill(x1, y1, x2, y1 + 1, color.getRGB());
            context.fill(x1, y1 + 1, x1 + 1, y2 - 1, color.getRGB());
            context.fill(x2, y1 + 1, x2 - 1, y2 - 1, color.getRGB());
        }

        context.fill(x1,     y2,     x2,     y2 - 1, color.getRGB());

        overflowTextRenderer.render(context, textRenderer, getMessage(), delta, color.getRGB());
        if (!active) {

            context.fill(x1, y1, x2, y2, 0x50303030);
        }
    }
}