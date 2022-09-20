package splash;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigManager;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import splash.config.Config;

public class ClearHitboxes implements ClientModInitializer {

    public static final String MOD_ID = "clearhitboxes";
    public static ConfigManager configManager;
    public static Config config;


    @Override
    public void onInitializeClient() {

        configManager = (ConfigManager) AutoConfig.register(Config.class, GsonConfigSerializer::new);
        KeyBinding toggleClearHitboxes = KeyBindingHelper.registerKeyBinding(new KeyBinding("Toggle ClearHitboxes", InputUtil.Type.KEYSYM, InputUtil.UNKNOWN_KEY.getCode(), "key.categories.clearhitboxes"));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (config == null) {
                config = AutoConfig.getConfigHolder(Config.class).getConfig();
            }
            while (toggleClearHitboxes.wasPressed()) {
                config.hitbox = !config.hitbox;
                configManager.save();
                client.player.sendMessage(Text.of(Formatting.GRAY+"["+ Formatting.RESET+"ClearHitboxes"+Formatting.GRAY+"]"+Formatting.RESET+" Custom hitbox set to "+config.hitbox+"."), true);
            }
         });

    }
}
