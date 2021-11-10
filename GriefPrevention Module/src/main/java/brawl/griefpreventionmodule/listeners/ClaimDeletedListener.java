package brawl.griefpreventionmodule.listeners;

import brawl.griefpreventionmodule.util.WorldOperations;
import brawl.nexuscore.events.NexusBrokenEvent;
import me.ryanhamshire.GriefPrevention.events.ClaimDeletedEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;

public class ClaimDeletedListener implements Listener {
    @EventHandler
    public void claimDeleted(ClaimDeletedEvent event) throws Exception {
        Player player = Bukkit.getPlayer(event.getClaim().getOwnerID());
        assert player != null;
        List<Location> nexusLocations = WorldOperations.removeFromClaim(event.getClaim());

        for (Location location: nexusLocations
             ) {

            NexusBrokenEvent nexusBrokenEvent = new NexusBrokenEvent(location);
            Bukkit.getPluginManager().callEvent(nexusBrokenEvent);
        }

    }
}
