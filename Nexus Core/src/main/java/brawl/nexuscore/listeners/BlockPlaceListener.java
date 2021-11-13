package brawl.nexuscore.listeners;

import brawl.nexuscore.events.NexusPlacedEvent;
import brawl.nexuscore.util.NexusOperations;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class BlockPlaceListener implements Listener {
    public BlockPlaceListener() {
    }

    @EventHandler
    public void blockPlace(BlockPlaceEvent event) {
        Block placedBlock = event.getBlockPlaced();
        Material placedMaterial = placedBlock.getType();
        if (placedMaterial != NexusOperations.nexusBlockFactory().getType()) {
            return;
        }
        ItemStack placedItem = event.getItemInHand();

        if (!NexusOperations.isNexus(placedItem))
            return;
        NexusPlacedEvent nexusPlaced = new NexusPlacedEvent(event);
        Bukkit.getPluginManager().callEvent(nexusPlaced);
    }
}
