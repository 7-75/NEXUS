package brawl.factionsmodule.listeners;

import brawl.factionsmodule.FactionsNexusController;
import brawl.nexuscore.NexusController;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.Factions;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import brawl.factionsmodule.util.FactionsOperations;
import brawl.factionsmodule.util.FactionAddonOperations;

public class BlockBreakListener implements Listener {

    String      factionHasBeenDefeatedMessage;

    public BlockBreakListener()
    {
        factionHasBeenDefeatedMessage = FactionsNexusController.plugin.getConfig().getString("factionHasBeenDefeatedMessage");


    }

    @EventHandler
    public void blockBreak(BlockBreakEvent event) {
        Block       brokenBlock             = event.getBlock();
        Material    brokenMaterial          = event.getBlock().getType();
        Location    brokenBlockLocation     = brokenBlock.getLocation();

        assert NexusController.nexusPhysicalBlockTemplate != null;
        Material    nexusBlockMaterial      = Material.getMaterial(NexusController.nexusPhysicalBlockTemplate);

        if (!brokenMaterial.equals(nexusBlockMaterial))
            return;

        Faction faction = FactionsOperations.getFactionByLocation(brokenBlockLocation);

        if (faction == null)
            return;

        String      factionId               = faction.getId();
        String      factionTag              = faction.getTag();

        if (!faction.getHome().equals(brokenBlockLocation))
            return;

        FactionAddonOperations.removeMagicBlockFromMap(faction.getHome());
        Factions.getInstance().removeFaction(factionId);

        Bukkit.getOnlinePlayers()
                .forEach(player -> {
                    player.sendMessage( factionTag +" "+ factionHasBeenDefeatedMessage);
                });
        event.setDropItems(false);

    }
}
