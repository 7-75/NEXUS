package brawl.nexuscore;

import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class NexusController {

    public static JavaPlugin                        plugin;
    public static String                            nexusBlockMaterial;
    public static String                            nexusItemLore;
    public static ArrayList<Location>               nexusBlocks;

    public static void initialise(JavaPlugin plugin) {
        NexusController.plugin = plugin;
        nexusBlockMaterial = plugin.getConfig().getString("nexusBlockMaterial");
        nexusItemLore = plugin.getConfig().getString("nexusItemLore");
        nexusBlocks = new ArrayList<>();

    }
}