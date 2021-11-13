package brawl.vaultmodule.util;

import brawl.nexuscore.NexusController;
import brawl.vaultmodule.VaultModuleController;
import brawl.vaultmodule.tasks.NexusTaxationTask;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class IOOperations {

    static private File tasksConfigFile;
    static private YamlConfiguration tasksConfig;

    public static void createNexusConfig() {
        tasksConfigFile = new File(NexusController.plugin.getDataFolder(), "taxationTasks.yml");
        if (!tasksConfigFile.exists()) {
            tasksConfigFile.getParentFile().mkdirs();
            NexusController.plugin.saveResource("taxationTasks.yml", false);
        }

        tasksConfig = new YamlConfiguration();
        try {
            tasksConfig.load(tasksConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static void serializeNexuses() throws IOException {
        tasksConfig.set("tasks", VaultModuleController.tasks);
        tasksConfig.save(tasksConfigFile);
    }

    public static void deserializeNexuses(){
        ArrayList<NexusTaxationTask> tasks = (ArrayList<NexusTaxationTask>) tasksConfig.get("tasks");

        if (tasks == null)
            return;

        VaultModuleController.tasks.addAll(tasks);

    }

}