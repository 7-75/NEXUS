package brawl.magicmodule.listeners;

import brawl.magicmodule.util.MagicOperations;
import brawl.nexuscore.events.NexusCreatedEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NexusCreatedListener implements Listener {
    @EventHandler
    public void created(NexusCreatedEvent event)
    {
        if(!event.isCancelled())
            MagicOperations.addMagicBlockToMap(event.getBlock().getLocation());
    }
}
