package brawl.nexuscore.events;

import brawl.nexuscore.NexusController;
import brawl.nexuscore.util.WorldOperations;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockPlaceEvent;
import org.jetbrains.annotations.NotNull;

public class NexusPlacedEvent extends BlockPlaceEvent {

    BlockPlaceEvent event;
    private boolean isCancelled;

    private static final HandlerList HANDLERS = new HandlerList();

    public NexusPlacedEvent(BlockPlaceEvent event) {
        super(event.getBlockPlaced(),event.getBlockReplacedState(),event.getBlockPlaced(),event.getItemInHand(),event.getPlayer(),event.canBuild(),event.getHand());
        this.event = event;
        NexusController.nexusBlocks.add(event.getBlock().getLocation());
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public boolean isCancelled() {
        return this.isCancelled;
    }

    @Override
    public void setCancelled(boolean isCancelled) {

        if (isCancelled)
        {
            event.setCancelled(true);
            NexusController.nexusBlocks.remove(event.getBlock().getLocation());
            WorldOperations.removeFromLocation(event.getBlock().getLocation());
        }
        this.isCancelled = isCancelled;
    }
}