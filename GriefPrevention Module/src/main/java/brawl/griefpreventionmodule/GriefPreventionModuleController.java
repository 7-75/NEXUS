package brawl.griefpreventionmodule;

import me.ryanhamshire.GriefPrevention.GriefPrevention;
import org.bukkit.plugin.java.JavaPlugin;

public class GriefPreventionModuleController {

    public static JavaPlugin        plugin;
    public static GriefPrevention   griefPrevention;

    public static void initialise(JavaPlugin plugin, GriefPrevention griefPrevention) {

        GriefPreventionModuleController.plugin          = plugin;
        GriefPreventionModuleController.griefPrevention = griefPrevention;

    }
}
