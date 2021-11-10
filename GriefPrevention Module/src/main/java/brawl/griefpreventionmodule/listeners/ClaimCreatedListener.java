package brawl.griefpreventionmodule.listeners;

import brawl.griefpreventionmodule.GriefPreventionModuleController;
import brawl.griefpreventionmodule.util.WorldOperations;
import brawl.nexuscore.NexusController;
import me.ryanhamshire.GriefPrevention.events.ClaimCreatedEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ClaimCreatedListener implements Listener {
    @EventHandler
    public void landClaimed(ClaimCreatedEvent event) {
        if (!GriefPreventionModuleController.plugin.getConfig().getBoolean("AutoGiveNexusBlockAtClaim"))
            return;

        Player player = Bukkit.getPlayer(event.getClaim().getOwnerID());
        assert player != null;

        Location nexusLocation = WorldOperations.addBlockToCenterOfClaim(event.getClaim());
        NexusController.nexusBlocks.add(nexusLocation);
    }
}
