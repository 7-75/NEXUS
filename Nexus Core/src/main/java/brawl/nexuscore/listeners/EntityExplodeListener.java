package brawl.nexuscore.listeners;

import brawl.nexuscore.NexusController;
import brawl.nexuscore.events.NexusRemovedEvent;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class EntityExplodeListener implements Listener {

    @EventHandler
    public void EntityExplodeEvent(EntityExplodeEvent event) {
        for (Block block : event.blockList()
        ) {
            if (NexusController.nexusBlocks.contains(block.getLocation()))
            {
                NexusRemovedEvent nexusRemovedEvent = new NexusRemovedEvent(block.getLocation());
                Bukkit.getPluginManager().callEvent(nexusRemovedEvent);
            }
        }
    }
}