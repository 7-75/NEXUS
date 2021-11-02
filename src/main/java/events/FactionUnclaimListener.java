package events;

import brawl.factionsnexus.FactionsNexus;
import brawl.factionsnexus.NexusController;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.event.LandUnclaimEvent;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import util.NexusOperations;

import java.util.HashMap;

public class FactionUnclaimListener implements Listener {
    NexusOperations nexusOperations;
    HashMap<Player, Boolean>        alreadyWarned;
    String                          unclaimWarnMessage;

    public FactionUnclaimListener(NexusController nexusController)
    {
        nexusOperations         = new NexusOperations(nexusController.plugin, nexusController.magicAPI);
        unclaimWarnMessage      = nexusController.plugin.getConfig().getString("unclaimWarnMessage");
        alreadyWarned           = new HashMap<>();
    }

    @EventHandler
    public void factionUnclaim (LandUnclaimEvent event)
    {
        FPlayer     fPlayer                     = event.getfPlayer();
        Player      player                      = fPlayer.getPlayer();
        Faction     faction                     = event.getFaction();
        Location    location                    = (Location) FactionsNexus.nexuses.get(faction);
        boolean     NexusIsInChunk              = event.getLocation().isInChunk(location);
        boolean     playerWasAlreadyWarned      = alreadyWarned.containsKey(player);


        if (!NexusIsInChunk)
            return;

        if (!playerWasAlreadyWarned)
        {
            player.sendMessage(unclaimWarnMessage);
            alreadyWarned.put(player, true);
            return;
        }

        try
        {
            nexusOperations.addToInventory(player);
        }catch (Exception e)
        {
            player.sendMessage(e.getMessage());
            event.setCancelled(true);
            return;
        }

        nexusOperations.removeFromMap(faction);
    }
}
