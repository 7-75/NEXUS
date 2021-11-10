package brawl.griefpreventionmodule.listeners;

import brawl.griefpreventionmodule.util.WorldOperations;
import brawl.nexuscore.events.NexusCreatedEvent;
import me.ryanhamshire.GriefPrevention.events.ClaimCreatedEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ClaimCreatedListener implements Listener {
    @EventHandler
    public void landClaimed(ClaimCreatedEvent event) {

        Player player = Bukkit.getPlayer(event.getClaim().getOwnerID());
        assert player != null;

        Location nexusLocation = WorldOperations.addNexusToCenterOfClaim(event.getClaim());

        NexusCreatedEvent nexusCreatedEvent = new NexusCreatedEvent(nexusLocation);
        Bukkit.getPluginManager().callEvent(nexusCreatedEvent);
    }
}
