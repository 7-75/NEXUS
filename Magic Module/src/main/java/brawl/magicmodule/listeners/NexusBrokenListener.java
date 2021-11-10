package brawl.magicmodule.listeners;

import brawl.magicmodule.util.MagicOperations;
import brawl.nexuscore.events.NexusBrokenEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NexusBrokenListener implements Listener {
    @EventHandler
    public void broken(NexusBrokenEvent event)
    {
        MagicOperations.removeMagicBlockFromMap(event.getLocation());
    }
}
