package brawl.factionsnexus;

import com.elmakers.mine.bukkit.api.magic.MagicAPI;
import com.google.gson.Gson;
import events.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.HashMap;


public final class FactionsNexus extends JavaPlugin {

    MagicAPI                magicAPI;
    BufferedReader          nexusesReader;
    BufferedWriter          nexusesWriter;
    Gson                    gson;

    public HashMap nexuses;

    @Override
    public void onEnable() {
        // Plugin startup logic
        writeDefaultConfig();

        gson                    = new Gson();
        magicAPI                = getMagicAPI();

        try {
            nexusesWriter       = new BufferedWriter(new FileWriter("nexuses.json"));
            nexusesReader       = new BufferedReader(new FileReader("nexuses.json"));
            registerListeners();
        } catch (IOException e) {
            e.printStackTrace();
        }

        nexuses = gson.fromJson(nexusesReader, HashMap.class);

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
        try {
            nexusesWriter.write(gson.toJson(nexuses));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeDefaultConfig()
    {
        this.saveDefaultConfig();
    }

    private void registerListeners() throws FileNotFoundException
    {
        PluginManager pluginManager = getServer().getPluginManager();

        pluginManager.registerEvents(new FactionCreateListener(this, magicAPI), this);
        pluginManager.registerEvents(new FactionSetHomeListener(this), this);
        pluginManager.registerEvents(new FactionLeaveListener(this, magicAPI, nexuses),this);
        pluginManager.registerEvents(new FactionDisbandListener(this, magicAPI, nexuses), this);
        pluginManager.registerEvents(new BlockPlaceListener(this, magicAPI, nexuses), this);
    }

}
