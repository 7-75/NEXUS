package brawl.magicmodule.listeners;

import brawl.magicmodule.MagicModuleController;
import brawl.magicmodule.util.MagicOperations;
import brawl.nexuscore.events.NexusCreatedEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NexusCreatedListener implements Listener {

    public boolean radiusEqualsGPClaimRadius = MagicModuleController.plugin.getConfig().getBoolean("radiusEqualsGPClaimRadius");
    @EventHandler
    public void created(NexusCreatedEvent event)
    {
        int radius = -1;
        if (radiusEqualsGPClaimRadius)
            radius = event.getRadius();
        MagicOperations.addMagicBlockToMap(event.getLocation(), radius);
    }
}
