package splash.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;

import static net.minecraft.client.gui.DrawableHelper.drawTextWithShadow;

public class OverflowTextRenderer {
    private final int startPauseTime;
    private final int endPauseTime;
    private final int xOffset;
    private final int x;
    private final int y;
    private final int maxWidth;
    private float tickCount;

    /** I do not own this code, I can't remember where I got it from so let me know and I will credit you, Apologies. **/

    public OverflowTextRenderer(int startPauseTime, int endPauseTime, int xOffset, int x, int y, int maxWidth) {
        this.startPauseTime = startPauseTime;
        this.endPauseTime = endPauseTime;
        this.xOffset = xOffset;
        this.x = x;
        this.y = y;
        this.maxWidth = maxWidth;
        tickCount = -startPauseTime;
    }

    public void restart() {
        tickCount = -startPauseTime;
    }

    public void render(MatrixStack matrices, TextRenderer textRenderer, Text text, float tickDelta, int color) {
        int stringWidth = textRenderer.getWidth(text);
        int scrollRoom = stringWidth - maxWidth + (xOffset * 2);
        if (scrollRoom > 0) { // We want to start scrolling a bit before the true max width.
            if ((tickCount += tickDelta) > scrollRoom + endPauseTime)
                tickCount = -30; // We want to reset the count endPauseTime ticks after it finishes scrolling, and we want to start scrolling startPauseTime ticks after the reset
            float scrollOffset = MathHelper.clamp(tickCount, 0, scrollRoom);
            double scale = MinecraftClient.getInstance().getWindow().getScaleFactor();
            int startX = x - (maxWidth / 2);
            //ScissorStack.pushScissorArea((int) (startX * scale), 0, (int) (maxWidth * scale), MinecraftClient.getInstance().getWindow().getWidth());
            drawTextWithShadow(matrices,textRenderer, text, (int) (startX - scrollOffset + xOffset), (int) y, color);
            //ScissorStack.popScissorArea();
        } else {
            tickCount = 0;
            drawTextWithShadow(matrices,textRenderer, text, x, y, color);
        }
    }
}
