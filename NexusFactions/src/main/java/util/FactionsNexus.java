package util;


import brawl.factionsnexus.FactionsNexusController;
import brawl.nexuscore.NexusController;
import brawl.nexuscore.NexusCore;
import com.massivecraft.factions.Board;
import com.massivecraft.factions.Factions;
import main.java.events.*;
import util.NexusOperations;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public final class FactionsNexus extends JavaPlugin {

    FactionsNexusController nexusController;
    NexusOperations nexusOperations;

    @Override
    public void onEnable() {



        nexusController = new FactionsNexusController(NexusCore.getNexusController().getInstance(),this, getFactionsInstance(), getBoardInstance());
        nexusOperations = new NexusOperations();

        writeDefaultConfig();
        registerListeners();

    }

    Board getBoardInstance(){
        Board board = Board.getInstance();
        if (board == null){
            return null;
        }
        return board;
    }

    Factions getFactionsInstance(){
        Factions factions = Factions.getInstance();
        if (factions == null){
            return null;
        }
        return factions;
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

    }

    private void writeDefaultConfig()
    {
        this.saveDefaultConfig();
    }

    private void registerListeners()
    {
        PluginManager pluginManager = getServer().getPluginManager();

        pluginManager.registerEvents(new FactionCreateListener(), this);
        pluginManager.registerEvents(new FactionSetHomeListener(), this);
        pluginManager.registerEvents(new FactionLeaveListener(),this);
        pluginManager.registerEvents(new FactionDisbandListener(), this);
        pluginManager.registerEvents(new BlockPlaceListener(), this);
        pluginManager.registerEvents(new BlockBreakListener(),this);
        pluginManager.registerEvents(new FactionUnclaimListener(),this);
        pluginManager.registerEvents(new FactionUnclaimAllListener(),this);

    }

}
