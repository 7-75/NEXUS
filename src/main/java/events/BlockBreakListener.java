package events;

import brawl.factionsnexus.NexusController;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.data.MemoryFactions;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

    String factionHasBeenDefeatedMessage;

    public BlockBreakListener()
    {
        factionHasBeenDefeatedMessage = NexusController.plugin.getConfig().getString("factionHasBeenDefeatedMessage");
    }

    @EventHandler
    public void blockBreak(BlockBreakEvent event) {
        Block       brokenBlock            = event.getBlock();
        Material    brokenMaterial         = event.getBlock().getType();
        Location    brokenBlockLocation    = brokenBlock.getLocation();

        if (!brokenMaterial.equals(Material.BEACON))
            return;

        if (!NexusController.nexuses.containsValue(brokenBlockLocation)) {
            return;
        }

        Faction faction                 = (Faction) util.KeyByValue.getKeyByValue(NexusController.nexuses,brokenBlockLocation);
        NexusController.nexuses.remove(faction);

        String factionId = faction.getId();
        String factionTag = faction.getTag();

        MemoryFactions.getInstance().removeFaction(factionId);

        Bukkit.getOnlinePlayers()
                .forEach(player -> {
                    player.sendMessage( factionTag +" "+ factionHasBeenDefeatedMessage);
                });

        event.setDropItems(false);

    }
}
