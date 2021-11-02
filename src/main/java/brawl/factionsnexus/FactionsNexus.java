package brawl.factionsnexus;

import com.elmakers.mine.bukkit.api.magic.MagicAPI;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.massivecraft.factions.Faction;
import events.*;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.HashMap;


public final class FactionsNexus extends JavaPlugin {

    MagicAPI                magicAPI;
    ObjectMapper            objectMapper;
    String                  path;

    public static HashMap nexuses;

    @Override
    public void onEnable() {

        magicAPI                = getMagicAPI();
        path                    = "plugins/FactionsNexus/nexuses.json";
        objectMapper            = new ObjectMapper();

        writeDefaultConfig();
        registerListeners();
        readNexuses();

    }

    private void readNexuses()
    {
        try {
            nexuses = objectMapper.readValue(new File(path), HashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeNexuses()
    {
        try {
            objectMapper.writeValue(new File(path), nexuses);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        writeNexuses();

    }

    private void writeDefaultConfig()
    {
        this.saveDefaultConfig();
    }

    private void registerListeners()
    {
        PluginManager pluginManager = getServer().getPluginManager();

        pluginManager.registerEvents(new FactionCreateListener(this, magicAPI), this);
        pluginManager.registerEvents(new FactionSetHomeListener(this), this);
        pluginManager.registerEvents(new FactionLeaveListener(this, magicAPI),this);
        pluginManager.registerEvents(new FactionDisbandListener(this, magicAPI), this);
        pluginManager.registerEvents(new BlockPlaceListener(this, magicAPI, nexuses), this);
    }

}
