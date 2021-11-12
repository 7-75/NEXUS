package brawl.nexuscore.events;

import brawl.nexuscore.NexusController;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockBreakEvent;
import org.jetbrains.annotations.NotNull;

public class NexusBrokenEvent extends BlockBreakEvent implements Cancellable {

        private final Location  location;
        private boolean         isCancelled;
        private final Player    player;

        public NexusBrokenEvent(Location location, Player player){
            super(location.getBlock(), player);
            this.location = location;
            this.player   = player;
            this.isCancelled = false;
            NexusController.nexusBlocks.remove(location);
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

        public Location getLocation()
        {
            return this.location;
        }
    }
