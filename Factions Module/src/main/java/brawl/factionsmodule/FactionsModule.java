package brawl.factionsmodule;

import brawl.factionsmodule.listeners.*;
import com.massivecraft.factions.Board;
import com.massivecraft.factions.Factions;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public final class FactionsModule extends JavaPlugin {

    FactionsModuleController nexusController;

    @Override
    public void onEnable() {



        nexusController = new FactionsModuleController(this, getFactionsInstance(), getBoardInstance());

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

        pluginManager.registerEvents(new NexusDestroyedListener(), this);
        pluginManager.registerEvents(new FactionUnclaimListener(),this);
        pluginManager.registerEvents(new FactionUnclaimAllListener(),this);
        pluginManager.registerEvents(new FactionDisbandListener(), this);
        pluginManager.registerEvents(new FactionSetHomeListener(), this);
        pluginManager.registerEvents(new FactionLeaveListener(),this);

        if(!this.getConfig().getBoolean("autoCenterChunkMode"))
        {
            pluginManager.registerEvents(new NexusPlacedListener(),this);
            pluginManager.registerEvents(new FactionCreateListener(), this);
        }


    }

}
