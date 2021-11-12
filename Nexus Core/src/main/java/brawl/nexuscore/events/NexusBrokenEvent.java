package brawl.nexuscore.events;

import brawl.nexuscore.NexusController;
import brawl.nexuscore.util.WorldOperations;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockBreakEvent;
import org.jetbrains.annotations.NotNull;

public class NexusBrokenEvent extends BlockBreakEvent implements Cancellable {

    BlockBreakEvent event;
    private boolean         isCancelled;

    private static final HandlerList HANDLERS = new HandlerList();

        public NexusBrokenEvent(BlockBreakEvent event){
            super(event.getBlock(), event.getPlayer());
            this.event    = event;
            this.isCancelled = false;
            NexusController.nexusBlocks.remove(event.getBlock().getLocation());
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
                event.setCancelled(true);
                NexusController.nexusBlocks.add(event.getBlock().getLocation());
                WorldOperations.addNexusToLocation(event.getBlock().getLocation());
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
            return event.getBlock().getLocation();
        }

        public Player   getPlayer()
        {
            return event.getPlayer();
        }
    }
