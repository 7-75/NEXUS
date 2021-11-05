package brawl.factionsnexus;

import com.elmakers.mine.bukkit.api.magic.MagicAPI;
import com.massivecraft.factions.Board;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.Factions;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import tasks.FactionRefreshBarrierTask;
import util.SchedulerOperations;

import java.util.ArrayList;

public class NexusController {

    public static JavaPlugin                                    plugin;
    public static MagicAPI                                      magicAPI;
    public static Factions                                      factionsAPI;
    public static Board                                         board;
    public static BukkitScheduler                               bukkitScheduler;
    public static ArrayList<FactionRefreshBarrierTask>          factionRefreshBarrierTimers;

    public NexusController(MagicAPI magicAPI, JavaPlugin plugin, Factions factionsAPI, Board board) {
        NexusController.plugin                          =   plugin;
        NexusController.magicAPI                        =   magicAPI;
        NexusController.factionsAPI                     =   factionsAPI;
        NexusController.board                           =   board;
        NexusController.bukkitScheduler                 =   plugin.getServer().getScheduler();
        NexusController.factionRefreshBarrierTimers     =   new ArrayList<>();

        for (Faction faction : factionsAPI.getAllFactions())
        {

            if (faction.hasHome())
            {
                factionRefreshBarrierTimers.add(SchedulerOperations.addScheduler(faction));
            }

        }

    }

}
