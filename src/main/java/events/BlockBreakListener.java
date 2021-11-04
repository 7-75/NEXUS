package events;

import brawl.factionsnexus.NexusController;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.Factions;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import util.NexusOperations;

public class BlockBreakListener implements Listener {

    String factionHasBeenDefeatedMessage;

    public BlockBreakListener()
    {
        factionHasBeenDefeatedMessage = NexusController.plugin.getConfig().getString("factionHasBeenDefeatedMessage");
    }

    @EventHandler
    public void blockBreak(BlockBreakEvent event) {
        Block       brokenBlock             = event.getBlock();
        Material    brokenMaterial          = event.getBlock().getType();
        Location    brokenBlockLocation     = brokenBlock.getLocation();

        if (!brokenMaterial.equals(Material.BEACON))
            return;

        Faction faction =

                NexusController.factions.getAllFactions()
                                .stream()
                                .filter(Faction::hasHome)
                                .filter(f -> f
                                        .getHome()
                                        .equals(brokenBlockLocation))
                                .findFirst()
                        .orElse(null);

        if (faction == null)
            return;

        String      factionId               = faction.getId();
        String      factionTag              = faction.getTag();

        if (!faction.getHome().equals(brokenBlockLocation))
            return;

        NexusOperations.removeMagicBlockFromMap(faction.getHome());
        Factions.getInstance().removeFaction(factionId);
        Bukkit.getOnlinePlayers()
                .forEach(player -> {
                    player.sendMessage( factionTag +" "+ factionHasBeenDefeatedMessage);
                });
        event.setDropItems(false);

    }
}