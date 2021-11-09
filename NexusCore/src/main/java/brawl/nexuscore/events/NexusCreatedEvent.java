package brawl.nexuscore.events;

import brawl.nexuscore.NexusController;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class NexusCreatedEvent extends BlockPlaceEvent {

    private boolean isCancelled;

    public NexusCreatedEvent(@NotNull final Block placedBlock, @NotNull final BlockState replacedBlockState, @NotNull final Block placedAgainst, @NotNull final ItemStack itemInHand, @NotNull final Player thePlayer, final boolean canBuild, @NotNull final EquipmentSlot hand)
    {
        super( placedBlock, replacedBlockState, placedAgainst, itemInHand, thePlayer, canBuild, hand);
        this.isCancelled    = false;
        NexusController.nexusBlocks.add(super.block.getLocation());
    }

    private static final HandlerList HANDLERS = new HandlerList();

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
            super.setCancelled(true);
            NexusController.nexusBlocks.remove(super.block.getLocation());
        }
        this.isCancelled = isCancelled;
    }
}