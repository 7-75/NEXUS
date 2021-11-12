package brawl.magicmodule.listeners;

import brawl.magicmodule.util.MagicOperations;
import brawl.nexuscore.events.NexusRemovedEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NexusRemovedListener implements Listener {
    @EventHandler
    public void removed(NexusRemovedEvent event)
    {
        MagicOperations.removeMagicBlockFromMap(event.getLocation());
    }
}
