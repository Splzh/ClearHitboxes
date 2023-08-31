package splash.config;

import net.fabricmc.loader.api.FabricLoader;
import splash.ClearHitboxes;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

public class ConfigUpdate {

    public static void run(){
        File configFile = new File(FabricLoader.getInstance().getGameDir()+"/clearhitboxes/settings.txt");
        try {
            Properties props = new Properties();
            FileReader reader = new FileReader(configFile);
            props.load(reader);
            FileWriter writer = new FileWriter(configFile);
            writer.flush();
            writer.write("red="+ ClearHitboxesConfig.red+"\n");
            writer.write("green="+ ClearHitboxesConfig.green+"\n");
            writer.write("blue="+ ClearHitboxesConfig.blue+"\n");
            writer.write("alpha="+ ClearHitboxesConfig.alpha+"\n");
            writer.write("enableChroma="+ ClearHitboxesConfig.enableChroma+"\n");
            writer.write("enableGradient="+ ClearHitboxesConfig.enableGradient+"\n");
            writer.write("gradientSpeed="+ ClearHitboxesConfig.gradientSpeed+"\n");
            writer.write("rgbIntensity="+ ClearHitboxesConfig.rgbIntensity+"\n");
            writer.write("rgbDelay="+ ClearHitboxesConfig.rgbDelay+"\n");
            writer.write("enableClearHitboxes="+ ClearHitboxesConfig.enableClearHitboxes+"\n");
            writer.write("enableDebugHitbox="+ ClearHitboxesConfig.enableDebugHitbox+"\n");
            writer.write("firstTime="+ ClearHitboxesConfig.firstTime+"\n");
            writer.write("enableByDefault="+ ClearHitboxesConfig.enableByDefault+"\n");
            writer.write("hitboxSize="+ ClearHitboxesConfig.hitboxSize+"\n");
            writer.write("coordX="+ ClearHitboxesConfig.coordX+"\n");
            writer.write("coordY="+ ClearHitboxesConfig.coordY+"\n");
            writer.close();

        } catch (FileNotFoundException ex) {
            ClearHitboxes.LOGGER.info(configFile.getName()+"does not exist. "+ex);
        } catch (IOException ex) {
            ClearHitboxes.LOGGER.info("I/O Error. "+ex);
        } catch (Exception ex){
            ClearHitboxes.LOGGER.info("Exception. More details: "+(ex));
        }
    }

    public static void copy(){
        File configFile = new File(FabricLoader.getInstance().getGameDir()+"/clearhitboxes/settings.txt");
        try {
            Properties props = new Properties();
            FileReader reader = new FileReader(configFile);
            props.load(reader);
            List<String> lines = Files.readAllLines(Paths.get(FabricLoader.getInstance().getGameDir()+"/clearhitboxes/settings.txt"));

            ClearHitboxesConfig.red = Double.parseDouble(lines.get(0).substring(lines.get(0).lastIndexOf("=")+1));
            ClearHitboxesConfig.green = Double.parseDouble(lines.get(1).substring(lines.get(1).lastIndexOf("=")+1));
            ClearHitboxesConfig.blue = Double.parseDouble(lines.get(2).substring(lines.get(2).lastIndexOf("=")+1));
            ClearHitboxesConfig.alpha = Double.parseDouble(lines.get(3).substring(lines.get(3).lastIndexOf("=")+1));
            ClearHitboxesConfig.enableChroma = Boolean.parseBoolean(lines.get(4).substring(lines.get(4).lastIndexOf("=")+1));
            ClearHitboxesConfig.enableGradient = Boolean.parseBoolean(lines.get(5).substring(lines.get(5).lastIndexOf("=")+1));
            ClearHitboxesConfig.gradientSpeed = Double.parseDouble(lines.get(6).substring(lines.get(6).lastIndexOf("=")+1));
            ClearHitboxesConfig.rgbIntensity = Double.parseDouble(lines.get(7).substring(lines.get(7).lastIndexOf("=")+1));
            ClearHitboxesConfig.rgbDelay = Double.parseDouble(lines.get(8).substring(lines.get(8).lastIndexOf("=")+1));
            ClearHitboxesConfig.enableClearHitboxes = Boolean.parseBoolean(lines.get(9).substring(lines.get(9).lastIndexOf("=")+1));
            ClearHitboxesConfig.enableDebugHitbox = Boolean.parseBoolean(lines.get(10).substring(lines.get(10).lastIndexOf("=")+1));
            ClearHitboxesConfig.firstTime = Boolean.parseBoolean(lines.get(11).substring(lines.get(11).lastIndexOf("=")+1));
            ClearHitboxesConfig.enableByDefault = Boolean.parseBoolean(lines.get(12).substring(lines.get(12).lastIndexOf("=")+1));
            ClearHitboxesConfig.hitboxSize = Float.parseFloat(lines.get(13).substring(lines.get(13).lastIndexOf("=")+1));
            ClearHitboxesConfig.coordX = Float.parseFloat(lines.get(14).substring(lines.get(14).lastIndexOf("=")+1));
            ClearHitboxesConfig.coordY = Float.parseFloat(lines.get(15).substring(lines.get(15).lastIndexOf("=")+1));

        } catch (FileNotFoundException ex) {
            ClearHitboxes.LOGGER.info(configFile.getName()+"does not exist. "+ex);
        } catch (IOException ex) {
            ClearHitboxes.LOGGER.info("I/O Error. "+ex);
        } catch (Exception ex){
            ClearHitboxes.LOGGER.info("Exception. More details: "+(ex));
        }
    }

}
