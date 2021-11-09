package brawl.nexuscore.listeners;

import brawl.nexuscore.util.NexusOperations;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class ItemDropListener implements Listener {
    @EventHandler
    public void dropItem(PlayerDropItemEvent event)
    {
        if (NexusOperations.isNexus(event.getItemDrop().getItemStack()))
        {
            event.setCancelled(true);
        }
    }
}
