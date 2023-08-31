package splash;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import splash.config.ClearHitboxesConfig;
import splash.config.ConfigUpdate;
import splash.menu.ColorEditor;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ClearHitboxes implements ClientModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("ClearHitboxes");

    @Override
    public void onInitializeClient() {
        try {
            Files.createDirectories(Path.of(FabricLoader.getInstance().getGameDir() + "/clearhitboxes/"));
            File directory = new File(FabricLoader.getInstance().getGameDir() + "/clearhitboxes/settings.txt");

            if (directory.createNewFile()) {
                LOGGER.info("Folder & Config created: " + directory.getAbsolutePath());
            } else {
                LOGGER.info("Config ready.");
            }
        } catch (IOException e) {
            LOGGER.info("An error occurred while generating the config.");
            e.printStackTrace();
        }
        ConfigUpdate.copy();

        KeyBinding clearHitboxesMenu = KeyBindingHelper.registerKeyBinding(new KeyBinding("ClearHitboxes Menu", InputUtil.Type.KEYSYM, InputUtil.GLFW_KEY_RIGHT_CONTROL, "key.categories.clearhitboxes"));
        KeyBinding toggleClearHitboxes = KeyBindingHelper.registerKeyBinding(new KeyBinding("Toggle ClearHitboxes", InputUtil.Type.KEYSYM, InputUtil.UNKNOWN_KEY.getCode(), "key.categories.clearhitboxes"));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            while (clearHitboxesMenu.wasPressed()) {

                client.setScreen(new ColorEditor(client.currentScreen, Text.empty()));

            }
            while (toggleClearHitboxes.wasPressed()) {
                ClearHitboxesConfig.enableClearHitboxes = !ClearHitboxesConfig.enableClearHitboxes;

                client.player.sendMessage(Text.of(Formatting.GRAY + "[" + Formatting.RESET + "ClearHitboxes" + Formatting.GRAY + "]" + Formatting.RESET + " Custom hitbox set to " + ClearHitboxesConfig.enableClearHitboxes + "."), true);
            }
        });
    }
}
