package brawl.magicmodule.listeners;

import brawl.magicmodule.util.MagicOperations;
import brawl.nexuscore.events.NexusPlacedEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NexusPlacedListener implements Listener {
    @EventHandler
    public void placed(NexusPlacedEvent event)
    {
        if(!event.isCancelled())
            MagicOperations.addMagicBlockToMap(event.getBlock().getLocation(), -1);
    }
}
