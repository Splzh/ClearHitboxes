package splash.utils;

import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Box;
import splash.config.Config;


public class Render extends DrawableHelper {
    public static void drawBox(MatrixStack matrix, VertexConsumer vertices, Entity entity, float red, float green, float blue, float red2, float green2, float blue2, float alpha, float lineWidth) {
        Config config = AutoConfig.getConfigHolder(Config.class).getConfig();
        Box box = entity.getBoundingBox().offset(-entity.getX(), -entity.getY(), -entity.getZ());
        if (config == null) {
            config = AutoConfig.getConfigHolder(Config.class).getConfig();
        }
        if (config.normalLines) {
            BoxUtils.drawBox(matrix, vertices, box.maxX, box.maxY, box.maxZ, box.minX, box.minY, box.minZ, red, green, blue, alpha, red2, green2, blue2, lineWidth);
        }
        else{
            BoxUtils.drawBoxThin(matrix, box.maxX, box.maxY, box.maxZ, box.minX, box.minY, box.minZ, red, green, blue, alpha, red2, green2, blue2, lineWidth);
        }
    }
}

