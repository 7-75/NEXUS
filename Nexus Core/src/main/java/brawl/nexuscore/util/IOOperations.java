package brawl.nexuscore.util;

import brawl.nexuscore.NexusController;
import org.bukkit.Location;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class IOOperations {

    static private File nexusesConfigFile;
    static private YamlConfiguration nexusesConfig;

    public static void createNexusConfig() {
        nexusesConfigFile = new File(NexusController.plugin.getDataFolder(), "nexuses.yml");
        if (!nexusesConfigFile.exists()) {
            nexusesConfigFile.getParentFile().mkdirs();
            NexusController.plugin.saveResource("nexuses.yml", false);
        }

        nexusesConfig = new YamlConfiguration();
        try {
            nexusesConfig.load(nexusesConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

        public static void serializeNexuses() throws IOException {
            nexusesConfig.set("nexuses",NexusController.nexusBlocks);
            nexusesConfig.save(nexusesConfigFile);
        }

        public static void deserializeNexuses(){
            ArrayList<Location> locations = (ArrayList<Location>) nexusesConfig.get("nexuses");

            if (locations == null)
                return;

            NexusController.nexusBlocks.addAll(locations);

    }

}