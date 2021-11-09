package brawl.nexuscore;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class NexusController {

    public static JavaPlugin                        plugin;
    public static String                            nexusBlockMaterial;
    public static String                            nexusItemLore;
    public static ArrayList<Location>               nexusBlocks;
    public static ObjectMapper                      objectMapper;

    public NexusController(JavaPlugin plugin) {
        NexusController.plugin = plugin;
        nexusBlockMaterial = plugin.getConfig().getString("nexusBlockMaterial");
        nexusItemLore = plugin.getConfig().getString("nexusItemLore");
        nexusBlocks = new ArrayList<>();
        objectMapper = new ObjectMapper();
    }
}