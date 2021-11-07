package brawl.nexuscore;

import com.elmakers.mine.bukkit.api.magic.MagicAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class NexusCore extends JavaPlugin {

    private NexusController nexusController;

    @Override
    public void onEnable() {

        nexusController = new NexusController(this, getMagicAPI());

    }

    MagicAPI getMagicAPI() {
        Plugin magicPlugin = Bukkit.getPluginManager().getPlugin("Magic");
        if (!(magicPlugin instanceof MagicAPI)) {
            return null;
        }
        return (MagicAPI)magicPlugin;
    }

    public NexusController getNexusController() {
        return  nexusController;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
