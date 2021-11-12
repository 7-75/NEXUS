package brawl.factionsmodule.listeners;

import brawl.factionsmodule.FactionsModuleController;
import brawl.nexuscore.events.NexusRemovedEvent;
import brawl.nexuscore.util.NexusOperations;
import brawl.nexuscore.util.WorldOperations;
import com.massivecraft.factions.FLocation;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.event.LandUnclaimEvent;
import org.bukkit.Bukkit;
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


        System.out.println("test0: check");


        if (!faction.hasHome())
            return;

        System.out.println("test1: check");

        Location    location                    = faction.getHome();
        boolean     NexusIsInChunk              = fLocation.isInChunk(location);

        if (!NexusIsInChunk)
            return;


        System.out.println("test2: check");

        if (!playerWasAlreadyWarned)
        {
            player.sendMessage(unclaimWarnMessage);
            alreadyWarned.put(player, true);
            event.setCancelled(true);
            return;
        }


        System.out.println("test3: check");

        FactionsModuleController.board.unclaimAll(faction.getId());

        System.out.println("test4: check");

        Location nexusLocation = WorldOperations.getNexusAtChunk(location.getChunk());

        System.out.println(nexusLocation);

        NexusRemovedEvent nexusRemovedEvent = new NexusRemovedEvent(nexusLocation);
        Bukkit.getPluginManager().callEvent(nexusRemovedEvent);

        if (autoCenterChunkMode) {
            return;
        }

        try
        {
            NexusOperations.addToInventory(player);
        }catch (Exception e)
        {
            player.sendMessage(e.getMessage());
            event.setCancelled(true);
        }
    }
}
