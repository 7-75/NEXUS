package brawl.factionsmodule.listeners;

import brawl.factionsmodule.util.FactionsOperations;
import com.massivecraft.factions.Faction;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class EntityExplodeListener implements Listener {

    @EventHandler
    public void EntityExplodeEvent(EntityExplodeEvent event) {
        for (Block block : event.blockList()
        ) {
            Faction faction = FactionsOperations.getFactionByLocation(block.getLocation());
            if (faction != null)
                faction.remove();
        }

    }
}