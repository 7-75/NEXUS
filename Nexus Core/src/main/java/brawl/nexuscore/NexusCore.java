package brawl.nexuscore;

import brawl.nexuscore.listeners.*;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class NexusCore extends JavaPlugin {

    private NexusController nexusController;

    @Override
    public void onEnable() {
        nexusController = new NexusController(this);
        writeDefaultConfig();
        registerEvents();
    }

    private void writeDefaultConfig()
    {
        this.saveDefaultConfig();
    }

    private void registerEvents()
    {
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new BlockBreakListener(),this);
        pluginManager.registerEvents(new BlockExplodeListener(),this);
        pluginManager.registerEvents(new BlockPlaceListener(),this);
        pluginManager.registerEvents(new EntityExplodeListener(),this);
        pluginManager.registerEvents(new ItemDropListener(), this);
        pluginManager.registerEvents(new ItemShiftClickListener(), this);
        pluginManager.registerEvents(new ItemClickListener(),this);
        pluginManager.registerEvents(new ItemDragListener(),this);
        pluginManager.registerEvents(new PlayerDeathListener(), this);
    }

    public NexusController getNexusController() {
        return  nexusController;
    }

    @Override
    public void onDisable() {

    }
}
