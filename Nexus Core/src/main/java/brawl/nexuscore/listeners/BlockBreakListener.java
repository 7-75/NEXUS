package brawl.nexuscore.listeners;

import brawl.nexuscore.NexusController;
import brawl.nexuscore.events.NexusBrokenEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener{

    @EventHandler
    public void blockBreak(BlockBreakEvent event) {
        Block brokenBlock = event.getBlock();
        Material brokenMaterial = event.getBlock().getType();
        Location brokenBlockLocation = brokenBlock.getLocation();

        assert NexusController.nexusBlockMaterial != null;

        if (!brokenMaterial.equals(NexusController.nexusBlockMaterial))
            return;

        if (!NexusController.nexusBlocks.contains(brokenBlockLocation))
            return;

        NexusBrokenEvent nexusBroken = new NexusBrokenEvent(brokenBlockLocation);
        Bukkit.getPluginManager().callEvent(nexusBroken);
        event.setDropItems(false);
    }
}
