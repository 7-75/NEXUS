package brawl.nexuscore.events;

import brawl.nexuscore.NexusController;
import brawl.nexuscore.util.WorldOperations;
import org.bukkit.Location;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class NexusCreatedEvent extends Event implements Cancellable {

    private final   Location    location;
    private final   double      adaptiveParameterValue;
    private boolean             isCancelled;

    private static final HandlerList HANDLERS = new HandlerList();

    public NexusCreatedEvent(Location location, double adaptiveParameterValue)
    {
        this.location = location;
        this.adaptiveParameterValue = adaptiveParameterValue;
        NexusController.nexusBlocks.add(location);
        WorldOperations.addNexusToLocation(location);

    }

    public boolean isCancelled() {
        return this.isCancelled;
    }

    @Override
    public void setCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;

        if (isCancelled)
        {
            NexusController.nexusBlocks.remove(location);
            WorldOperations.addNexusToLocation(getLocation());
        }
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public double getAdaptiveParameterValue()
    {
        return this.adaptiveParameterValue;
    }

    public Location getLocation()
    {
        return this.location;
    }

    @Override
    public HandlerList getHandlers() {
        return null;
    }
}
