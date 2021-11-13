package brawl.vaultmodule;

import brawl.vaultmodule.tasks.NexusTaxationTask;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ArrayList;

public class VaultModuleController {
    public static JavaPlugin                    plugin;
    public static Economy                       economy;
    public static BukkitScheduler               scheduler;
    public static ArrayList<NexusTaxationTask>  tasks;

    public void initialise(JavaPlugin plugin, Economy economy)
    {
        VaultModuleController.plugin    = plugin;
        VaultModuleController.economy   = economy;
        scheduler                       = Bukkit.getScheduler();

        tasks.forEach(nexusTaxationTask ->
                scheduler.scheduleSyncRepeatingTask(plugin, nexusTaxationTask, 0, plugin.getConfig().getInt("runTaxationEveryXTicks")));


    }
}
