package brawl.magicmodule;

import brawl.magicmodule.listeners.NexusCreatedListener;
import brawl.magicmodule.listeners.NexusPlacedListener;
import brawl.magicmodule.listeners.NexusBrokenListener;
import brawl.magicmodule.util.MagicOperations;
import com.elmakers.mine.bukkit.api.magic.MagicAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class MagicModule extends JavaPlugin {

    private MagicOperations magicOperations;

    @Override
    public void onEnable() {
        MagicModuleController.initialize(this, getMagicAPI());
        writeDefaultConfig();
        registerListeners();
    }

    private void writeDefaultConfig()
    {
        this.saveDefaultConfig();
    }

    MagicAPI getMagicAPI() {
        Plugin magicPlugin = Bukkit.getPluginManager().getPlugin("Magic");
        if (!(magicPlugin instanceof MagicAPI)) {
            return null;
        }
        return (MagicAPI)magicPlugin;
    }

    void registerListeners()
    {
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new NexusPlacedListener(),this);
        pluginManager.registerEvents(new NexusBrokenListener(),this);
        pluginManager.registerEvents(new NexusCreatedListener(),this);
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
