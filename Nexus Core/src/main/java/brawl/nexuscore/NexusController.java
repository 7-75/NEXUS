package brawl.nexuscore;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class NexusController {

    public static JavaPlugin                        plugin;
    public static String                            nexusBlockMaterialKey;
    public static String                            nexusItemLore;
    public static ArrayList<Location>               nexusBlocks;
    public static Material                          nexusBlockMaterial;

    public static void initialise(JavaPlugin plugin) {
        NexusController.plugin = plugin;
        nexusBlockMaterialKey = plugin.getConfig().getString("nexusBlockMaterial");
        nexusItemLore = plugin.getConfig().getString("nexusItemLore");
        nexusBlocks = new ArrayList<>();
        nexusBlockMaterial = Material.getMaterial(nexusBlockMaterialKey);
    }
}