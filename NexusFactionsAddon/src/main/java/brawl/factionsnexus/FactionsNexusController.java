package brawl.factionsnexus;

import com.massivecraft.factions.Board;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.Factions;
import main.java.tasks.FactionRefreshBarrierTask;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import util.SchedulerOperations;

import java.util.ArrayList;

public class FactionsNexusController {

    public static JavaPlugin                                    plugin;
    public static Factions                                      factionsAPI;
    public static Board                                         board;
    public static BukkitScheduler                               bukkitScheduler;
    public static ArrayList<FactionRefreshBarrierTask>          factionRefreshBarrierTimers;

    public FactionsNexusController(JavaPlugin plugin, Factions factionsAPI, Board board) {
        FactionsNexusController.plugin                          =   plugin;
        FactionsNexusController.factionsAPI                     =   factionsAPI;
        FactionsNexusController.board                           =   board;
        FactionsNexusController.bukkitScheduler                 =   plugin.getServer().getScheduler();
        FactionsNexusController.factionRefreshBarrierTimers     =   new ArrayList<>();

        for (Faction faction : factionsAPI.getAllFactions())
        {

            if (faction.hasHome())
            {
                factionRefreshBarrierTimers.add(SchedulerOperations.addScheduler(faction));
            }

        }

    }

}
