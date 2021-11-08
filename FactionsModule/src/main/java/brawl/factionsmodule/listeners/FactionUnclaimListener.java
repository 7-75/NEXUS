package brawl.factionsmodule.listeners;

import brawl.factionsmodule.FactionsNexusController;
import brawl.factionsmodule.util.SchedulerOperations;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.event.LandUnclaimEvent;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import brawl.factionsmodule.util.FactionAddonOperations;
import brawl.nexuscore.util.NexusOperations;

import java.util.HashMap;

public class FactionUnclaimListener implements Listener {
    HashMap<Player, Boolean>        alreadyWarned;
    String                          unclaimWarnMessage;

    public FactionUnclaimListener()
    {
        unclaimWarnMessage      = FactionsNexusController.plugin.getConfig().getString("unclaimWarnMessage");
        alreadyWarned           = new HashMap<>();
    }

    @EventHandler
    public void factionUnclaim (LandUnclaimEvent event) throws Exception {
        FPlayer     fPlayer                     = event.getfPlayer();
        Player      player                      = fPlayer.getPlayer();
        Faction     faction                     = event.getFaction();
        boolean     playerWasAlreadyWarned      = alreadyWarned.containsKey(player);

        if (!faction.hasHome())
            return;


        Location    location                    = faction.getHome().getBlock().getLocation();
        boolean     NexusIsInChunk              = event.getLocation().isInChunk(location);

        if (!NexusIsInChunk)
            return;


        if (!playerWasAlreadyWarned)
        {
            player.sendMessage(unclaimWarnMessage);
            alreadyWarned.put(player, true);
            event.setCancelled(true);
            return;
        }

        try
        {
            NexusOperations.addToInventory(player);
        }catch (Exception e)
        {
            player.sendMessage(e.getMessage());
            event.setCancelled(true);
            return;
        }

        FactionAddonOperations.removeFromMap(faction);
        FactionAddonOperations.removeMagicBlockFromMap(location);
        FactionsNexusController.board.unclaimAll(faction.getId());
        NexusOperations.addToInventory(player);

        int taskId = SchedulerOperations.getTaskByFactionId(faction.getId()).taskId;
        FactionsNexusController.bukkitScheduler.cancelTask(taskId);
    }
}
