package brawl.factionsnexus;

import com.elmakers.mine.bukkit.api.magic.MagicAPI;
import com.massivecraft.factions.Factions;
import events.BlockPlaceListener;
import events.FactionCreateListener;
import events.FactionDisbandListener;
import events.FactionSetHomeListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class FactionsNexus extends JavaPlugin {

    MagicAPI            magicAPI;
    Factions            factionsPlugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        writeDefaultConfig();

        magicAPI = getMagicAPI();

        registerListeners();

    }

    MagicAPI getMagicAPI() {
        Plugin magicPlugin = Bukkit.getPluginManager().getPlugin("Magic");
        if (!(magicPlugin instanceof MagicAPI)) {
            return null;
        }
        return (MagicAPI)magicPlugin;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void writeDefaultConfig()
    {
        this.saveDefaultConfig();
    }

    private void registerListeners()
    {
        PluginManager pluginManager = getServer().getPluginManager();

        pluginManager.registerEvents(new FactionCreateListener(this, magicAPI), this);
        pluginManager.registerEvents(new FactionDisbandListener(), this);
        pluginManager.registerEvents(new FactionSetHomeListener(), this);
        pluginManager.registerEvents(new BlockPlaceListener(this, magicAPI), this);

    }
}
