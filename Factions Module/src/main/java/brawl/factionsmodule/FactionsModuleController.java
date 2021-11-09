package brawl.factionsmodule;

import com.massivecraft.factions.Board;
import com.massivecraft.factions.Factions;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class FactionsModuleController {

    public static JavaPlugin                                    plugin;
    public static Factions                                      factionsAPI;
    public static Board                                         board;
    public static BukkitScheduler                               bukkitScheduler;

    public FactionsModuleController(JavaPlugin plugin, Factions factionsAPI, Board board) {
        FactionsModuleController.plugin                          =   plugin;
        FactionsModuleController.factionsAPI                     =   factionsAPI;
        FactionsModuleController.board                           =   board;
        FactionsModuleController.bukkitScheduler                 =   plugin.getServer().getScheduler();

    }

}
