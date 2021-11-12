package brawl.nexuscore.events;

import brawl.nexuscore.NexusController;
import brawl.nexuscore.util.WorldOperations;
import org.bukkit.Location;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class NexusRemovedEvent extends Event implements Cancellable {

    private final Location  location;
    private boolean isCancelled;

    private static final HandlerList HANDLERS = new HandlerList();

    public NexusRemovedEvent(Location location){
        this.location = location;
        this.isCancelled = false;
        NexusController.nexusBlocks.remove(location);
        WorldOperations.removeFromLocation(location);
    }

    @Override
    public boolean isCancelled() {
        return this.isCancelled;
    }

    @Override
    public void setCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;

        if (isCancelled)
        {
            NexusController.nexusBlocks.add(location);
            WorldOperations.addNexusToLocation(getLocation());
        }
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public Location getLocation()
    {
        return this.location;
    }
}
