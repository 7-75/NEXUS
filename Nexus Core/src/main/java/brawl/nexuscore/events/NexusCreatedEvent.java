package brawl.nexuscore.events;

import brawl.nexuscore.NexusController;
import org.bukkit.Location;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class NexusCreatedEvent extends Event implements Cancellable {

    private final   Location    location;
    private int                 radius;
    private boolean             isCancelled;

    public NexusCreatedEvent(Location location)
    {
        this.location = location;
        NexusController.nexusBlocks.add(location);
    }

    public NexusCreatedEvent(Location location, int radius)
    {
        this.location = location;
        this.radius   = radius;
        NexusController.nexusBlocks.add(location);

    }

    public boolean isCancelled() {
        return this.isCancelled;
    }

    @Override
    public void setCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;

        if (isCancelled)
        {
            NexusController.nexusBlocks.add(location);
        }
    }

    private static final HandlerList HANDLERS = new HandlerList();

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public int getRadius()
    {
        return this.radius;
    }

    public Location getLocation()
    {
        return this.location;
    }
}
