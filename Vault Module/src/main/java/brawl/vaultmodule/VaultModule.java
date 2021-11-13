package brawl.vaultmodule;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class VaultModule extends JavaPlugin {
    private static Economy econ = null;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        registerEvents();
        setupEconomy();


    }

    private void setupEconomy() {

        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        econ = rsp.getProvider();
    }

    void registerEvents()
    {
        PluginManager pluginManager = getServer().getPluginManager();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
