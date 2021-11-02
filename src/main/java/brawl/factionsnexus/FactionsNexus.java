package brawl.factionsnexus;

import com.elmakers.mine.bukkit.api.magic.MagicAPI;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import events.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.nio.file.Paths;
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
            nexuses = objectMapper.readValue(Paths.get(path).toFile(), new TypeReference<HashMap>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeNexuses()
    {
        System.out.println(nexuses);

        try {
            objectMapper.writeValue(Paths.get(path).toFile(), nexuses);
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

        NexusController nexusController = new NexusController(getMagicAPI(),this);

        pluginManager.registerEvents(new FactionCreateListener(nexusController), this);
        pluginManager.registerEvents(new FactionSetHomeListener(nexusController), this);
        pluginManager.registerEvents(new FactionLeaveListener(nexusController),this);
        pluginManager.registerEvents(new FactionDisbandListener(nexusController), this);
        pluginManager.registerEvents(new BlockPlaceListener(nexusController), this);
        pluginManager.registerEvents(new BlockBreakListener(nexusController),this);
        pluginManager.registerEvents(new FactionUnclaimListener(nexusController),this);
        pluginManager.registerEvents(new FactionUnclaimAllListener(nexusController),this);

    }

}
