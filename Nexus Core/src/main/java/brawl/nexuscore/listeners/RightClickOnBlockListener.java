package brawl.nexuscore.listeners;

import brawl.nexuscore.NexusController;
import brawl.nexuscore.inventories.NexusMainMenuInventory;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class RightClickOnBlockListener implements Listener {

    public void rightClick (PlayerInteractEvent event)
    {
        Block  clickedBlock = event.getClickedBlock();
        Player player       = event.getPlayer();

        if (clickedBlock.getType() != NexusController.nexusBlockMaterial)
            return;

        boolean foundBlock = NexusController.nexusBlocks
                .stream()
                .anyMatch(location -> location.equals(clickedBlock.getLocation()));

        if (!foundBlock)
            return;

        NexusMainMenuInventory.MainMenu.open(player);

    }

}
