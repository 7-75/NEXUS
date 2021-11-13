package brawl.griefpreventionmodule.listeners;

import brawl.griefpreventionmodule.GriefPreventionModuleController;
import brawl.nexuscore.events.NexusCreatedEvent;
import brawl.nexuscore.util.WorldOperations;
import me.ryanhamshire.GriefPrevention.events.ClaimCreatedEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ClaimCreatedListener implements Listener {


    int minimumClaimedArea;
    String areaTooSmallError;

    public ClaimCreatedListener()
    {
        minimumClaimedArea  = GriefPreventionModuleController.plugin.getConfig().getInt("minimumClaimedArea");
        areaTooSmallError   = GriefPreventionModuleController.plugin.getConfig().getString("areaTooSmallError");
    }

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


        int radius = WorldOperations.getRadius(
                lesserBoundaryCorner,
                greaterBoundaryCorner
                );



        if (!(event.getClaim().getArea() >= minimumClaimedArea))
        {
            player.sendMessage(areaTooSmallError);
            return;
        }

        NexusCreatedEvent nexusCreatedEvent = new NexusCreatedEvent(nexusLocation, radius);
        Bukkit.getPluginManager().callEvent(nexusCreatedEvent);
    }
}
