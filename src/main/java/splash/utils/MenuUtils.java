package splash.utils;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.SharedConstants;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.world.World;

import java.awt.*;

public class MenuUtils {

    public static void drawPreview(DrawContext context, int x, int y){
        Color background = new Color(0, 0, 0, 189);
        Color shadow = new Color(0, 0, 0, 255);
        Color light = new Color(168, 168, 168, 255);
        Render.drawString(context,0.9f, "Preview", x-28,y-89, 4210752);
        context.fill(x-30, y+10, x+30, y-80, background.getRGB());
        context.fill(x+30, y+10, x+29, y-80, shadow.getRGB());
        context.fill(x-30, y+9, x+30, y+10, shadow.getRGB());
        context.fill(x-30, y+10, x-29, y-80, light.getRGB());
        context.fill(x-30, y-80, x+30, y-79, light.getRGB());

        if(MinecraftClient.getInstance().getCurrentServerEntry() == null && MinecraftClient.getInstance().isInSingleplayer() == false){
            Render.drawString(context, 0.9f, " Ingame only", x-28,y-41, 16733525);
        } else{ Render.drawPlayer(x, y, 1.5f*25,0, 0);}
    }
    public static void drawInfobox(DrawContext context, int x, int y){
        EntityRenderDispatcher entityRenderDispatcher = MinecraftClient.getInstance().getEntityRenderDispatcher();
        Color background = new Color(0, 0, 0, 189);
        Color shadow = new Color(0, 0, 0, 255);
        Color light = new Color(168, 168, 168, 255);
        Render.drawString(context,0.9f, "Debug Info", x-28,y-89, 4210752);
        context.fill(x-30, y-40, x+30, y-80, background.getRGB());
        context.fill(x+30, y-40, x+29, y-80, shadow.getRGB());
        context.fill(x-30, y-40, x+30, y-41, shadow.getRGB());
        context.fill(x-30, y-40, x-29, y-80, light.getRGB());
        context.fill(x-30, y-80, x+30, y-79, light.getRGB());
        Render.drawString(context,0.6f, "Version: "+ SharedConstants.getGameVersion().getName(), x-26,y-75, Color.white.getRGB());
        Render.drawString(context,0.6f, "Library: "+MinecraftClient.getInstance().getVersionType(), x-26,y-67, Color.white.getRGB());
        Render.drawString(context,0.5f, "Debug Hitbox: "+entityRenderDispatcher.shouldRenderHitboxes(), x-26,y-59, Color.white.getRGB());
        Render.drawString(context,0.6f, "FPS: "+MinecraftClient.getInstance().getCurrentFps(), x-26,y-47, Color.white.getRGB());
    }
}
