package brawl.griefpreventionmodule.listeners;

import brawl.nexuscore.events.NexusCreatedEvent;
import brawl.nexuscore.util.WorldOperations;
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


        Location lesserBoundaryCorner = event.getClaim().getLesserBoundaryCorner();
        Location greaterBoundaryCorner = event.getClaim().getGreaterBoundaryCorner();

        Location nexusLocation = WorldOperations.getCenterLocation(
                greaterBoundaryCorner,
                lesserBoundaryCorner
        );
        WorldOperations.addNexusToLocation(nexusLocation);


        int radius = WorldOperations.getRadius(
                lesserBoundaryCorner,
                greaterBoundaryCorner
                );

        NexusCreatedEvent nexusCreatedEvent = new NexusCreatedEvent(nexusLocation, radius );
        Bukkit.getPluginManager().callEvent(nexusCreatedEvent);
    }
}
