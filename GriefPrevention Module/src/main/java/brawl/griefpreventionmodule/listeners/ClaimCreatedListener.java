package brawl.griefpreventionmodule.listeners;

import brawl.nexuscore.util.NexusOperations;
import me.ryanhamshire.GriefPrevention.events.ClaimCreatedEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ClaimCreatedListener implements Listener {
    @EventHandler
    public void landClaimed(ClaimCreatedEvent event) {
        try
        {
            NexusOperations.addToInventory((Player) event.getCreator());
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
