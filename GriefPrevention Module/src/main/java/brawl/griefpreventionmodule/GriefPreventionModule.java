package brawl.griefpreventionmodule;

import brawl.griefpreventionmodule.listeners.ClaimCreatedListener;
import brawl.griefpreventionmodule.listeners.ClaimDeletedListener;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class GriefPreventionModule extends JavaPlugin {

    @Override
    public void onEnable() {
        GriefPreventionModuleController.initialise(this, getGriefPrevention());
        saveDefaultConfig();
        registerListeners();
    }

    GriefPrevention getGriefPrevention()
    {
        Plugin griefPrevention  = Bukkit.getPluginManager().getPlugin("GriefPrevention");
        if (!(griefPrevention instanceof GriefPrevention)) {
            return null;
        }
        return (GriefPrevention) griefPrevention;

    }


    void registerListeners()
    {
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new ClaimCreatedListener(), this);
        pluginManager.registerEvents(new ClaimDeletedListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
