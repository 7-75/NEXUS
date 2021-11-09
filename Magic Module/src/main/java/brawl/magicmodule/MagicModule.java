package brawl.magicmodule;

import brawl.magicmodule.util.MagicOperations;
import com.elmakers.mine.bukkit.api.magic.MagicAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class MagicModule extends JavaPlugin {

    private MagicModuleController magicModuleController;
    private MagicOperations magicOperations;

    @Override
    public void onEnable() {
        magicModuleController = new MagicModuleController(this, getMagicAPI());
        magicOperations = new MagicOperations();
        writeDefaultConfig();
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

    public MagicModuleController getMagicModuleController() {
        return  magicModuleController;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
