package events;

import brawl.factionsnexus.FactionsNexus;
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

    public BlockBreakListener(NexusController nexusController)
    {
        factionHasBeenDefeatedMessage = nexusController.plugin.getConfig().getString("factionHasBeenDefeatedMessage");
    }

    @EventHandler
    public void blockBreak(BlockBreakEvent event) {
        Block       brokenBlock            = event.getBlock();
        Material    brokenMaterial         = event.getBlock().getType();
        Location    brokenBlockLocation    = brokenBlock.getLocation();

        if (!brokenMaterial.equals(Material.BEACON))
            return;

        if (!FactionsNexus.nexuses.containsValue(brokenBlockLocation)) {
            return;
        }

        Faction faction                 = (Faction) util.KeyByValue.getKeyByValue(FactionsNexus.nexuses,brokenBlockLocation);
        FactionsNexus.nexuses.remove(faction);

        assert faction != null;
        String factionTag = faction.getTag();

        MemoryFactions.getInstance().removeFaction(factionTag);

        Bukkit.getOnlinePlayers()
                .forEach(player -> {
                    player.sendMessage( factionTag +" "+ factionHasBeenDefeatedMessage);
                });

        event.setDropItems(false);

    }
}
