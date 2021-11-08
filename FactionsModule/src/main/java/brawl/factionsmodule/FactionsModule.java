package brawl.factionsmodule;

import com.massivecraft.factions.Board;
import com.massivecraft.factions.Factions;
import brawl.factionsmodule.listeners.*;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import brawl.factionsmodule.util.FactionAddonOperations;


public final class FactionsModule extends JavaPlugin {

    FactionsNexusController nexusController;
    FactionAddonOperations nexusOperations;

    @Override
    public void onEnable() {



        nexusController = new FactionsNexusController(this, getFactionsInstance(), getBoardInstance());

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

        pluginManager.registerEvents(new BlockBreakListener(),this);
        pluginManager.registerEvents(new BlockPlaceListener(), this);
        pluginManager.registerEvents(new BlockExplodeListener(),this);
        pluginManager.registerEvents(new EntityExplodeListener(),this);
        pluginManager.registerEvents(new FactionCreateListener(), this);
        pluginManager.registerEvents(new FactionDisbandListener(), this);
        pluginManager.registerEvents(new FactionLeaveListener(),this);
        pluginManager.registerEvents(new FactionSetHomeListener(), this);
        pluginManager.registerEvents(new FactionUnclaimAllListener(),this);
        pluginManager.registerEvents(new FactionUnclaimListener(),this);

    }

}
