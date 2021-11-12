package brawl.factionsmodule.listeners;

import brawl.factionsmodule.FactionsModuleController;
import brawl.factionsmodule.util.FactionsOperations;
import brawl.nexuscore.util.NexusOperations;
import com.massivecraft.factions.FLocation;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.event.LandUnclaimEvent;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.HashMap;


public class FactionUnclaimListener implements Listener {
    HashMap<Player, Boolean>        alreadyWarned;
    String                          unclaimWarnMessage;
    boolean                         autoCenterChunkMode;


    public FactionUnclaimListener()
    {
        autoCenterChunkMode     = FactionsModuleController.plugin.getConfig().getBoolean("autoCenterChunkMode");
        unclaimWarnMessage      = FactionsModuleController.plugin.getConfig().getString("unclaimWarnMessage");
        alreadyWarned           = new HashMap<>();
    }

    @EventHandler
    public void factionUnclaim (LandUnclaimEvent event) {
        FPlayer     fPlayer                     = event.getfPlayer();
        Player      player                      = fPlayer.getPlayer();
        Faction     faction                     = event.getFaction();
        boolean     playerWasAlreadyWarned      = alreadyWarned.containsKey(player);
        FLocation   fLocation                   = event.getLocation();


        if (!faction.hasHome())
            return;

        Location    location                    = faction.getHome();
        boolean     NexusIsInChunk              = fLocation.isInChunk(location);

        if (!NexusIsInChunk)
            return;


        if (!playerWasAlreadyWarned)
        {
            player.sendMessage(unclaimWarnMessage);
            alreadyWarned.put(player, true);
            event.setCancelled(true);
            return;
        }

        FactionsModuleController.board.unclaimAll(faction.getId());

        FactionsOperations.removeNexus(faction);

        if (autoCenterChunkMode)
            return;

        try{
            NexusOperations.addToInventory(player);
        } catch (Exception e) {
            player.sendMessage(e.getMessage());
            event.setCancelled(true);
        }
    }
}
