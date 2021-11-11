package brawl.nexuscore.listeners;

import brawl.nexuscore.events.NexusBrokenEvent;
import brawl.nexuscore.util.WorldOperations;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NexusBrokenListener implements Listener {
    @EventHandler
    public void broken(NexusBrokenEvent event)
    {
        WorldOperations.removeFromLocation(event.getLocation());
    }
}
