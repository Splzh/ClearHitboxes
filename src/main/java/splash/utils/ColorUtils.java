package splash.utils;

import me.shedaniel.autoconfig.AutoConfig;
import splash.config.Config;

import java.awt.*;

public class ColorUtils {

    public static Color getRainbow(double percent) {
        return getRainbow(percent, 128, 128, 128, 127, 127, 127);
    }

    public static Color getRainbow(double percent, int rMid, int gMid, int bMid, int rRange, int gRange, int bRange) {
        double offSet = Math.PI * 2 / 3;
        double pos = percent * (Math.PI * 2);
        float red   = (float) ((Math.sin(pos             ) * rRange) + rMid);
        float green = (float) ((Math.sin(pos + offSet    ) * gRange) + gMid);
        float blue  = (float) ((Math.sin(pos + offSet * 2) * bRange) + bMid);
        return new Color((int) red, (int) green, (int) blue, 255);
    }
    public static int rainbow(int delay) {
        Config config = AutoConfig.getConfigHolder(Config.class).getConfig();
        double rainbowState = Math.ceil((System.currentTimeMillis() + delay) / config.gradientSpeed);
        rainbowState %= 360;
        return Color.getHSBColor((float) (rainbowState / 360.0f), config.hitboxRGBB, config.hitboxRGBB).getRGB();

    }
}
