package brawl.griefpreventionmodule.listeners;

import brawl.nexuscore.util.NexusOperations;
import me.ryanhamshire.GriefPrevention.events.ClaimDeletedEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ClaimDeletedListener implements Listener {
    @EventHandler
    public void claimDeleted(ClaimDeletedEvent event)
    {
        Player player = Bukkit.getPlayer(event.getClaim().getOwnerID());
        assert player != null;
        NexusOperations.removeFromPlayer(player);
    }
}
