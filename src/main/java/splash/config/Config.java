package splash.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import splash.ClearHitboxes;

@me.shedaniel.autoconfig.annotation.Config(name = ClearHitboxes.MOD_ID)
@me.shedaniel.autoconfig.annotation.Config.Gui.Background("minecraft:textures/block/lime_concrete.png")
public class Config implements ConfigData {

    @ConfigEntry.Gui.PrefixText
    @ConfigEntry.Gui.Tooltip(count = 1)
    public boolean hitbox = true;

    @ConfigEntry.Gui.PrefixText
    @ConfigEntry.BoundedDiscrete(max = 254)
    public int red = 254;

    @ConfigEntry.BoundedDiscrete(max = 254)
    public int green = 254;

    @ConfigEntry.BoundedDiscrete(max = 254)
    public int blue = 254;
    @ConfigEntry.Gui.Tooltip(count = 2)
    @ConfigEntry.BoundedDiscrete(max = 10)
    public float alpha = 1;

    @ConfigEntry.Gui.PrefixText
    @ConfigEntry.Category(value = "page2")
    @ConfigEntry.Gui.Tooltip(count = 1)
    public boolean hitboxRGB = false;

    @ConfigEntry.Gui.CollapsibleObject()
    @ConfigEntry.Category(value = "page2")
    @ConfigEntry.Gui.Tooltip(count = 1)
    public boolean gradient = true;

    @ConfigEntry.BoundedDiscrete(max = 200, min = 3)
    @ConfigEntry.Gui.CollapsibleObject()
    @ConfigEntry.Category(value = "page2")
    @ConfigEntry.Gui.Tooltip(count = 1)
    public int gradientSpeed = 10;

    @ConfigEntry.Category(value = "page2")
    @ConfigEntry.Gui.Tooltip(count = 3)
    public float hitboxRGBB = 0.7f;


    @ConfigEntry.Category(value = "page2")
    @ConfigEntry.Gui.Tooltip(count = 1)
    public float gradientDelay = 0.1f;


    @ConfigEntry.Gui.PrefixText
    @ConfigEntry.Category(value = "page2")
    @ConfigEntry.Gui.Tooltip(count = 1)
    public boolean normalLines = false;
}