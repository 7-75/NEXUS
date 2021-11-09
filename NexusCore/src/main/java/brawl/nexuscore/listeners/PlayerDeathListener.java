package brawl.nexuscore.listeners;

import brawl.nexuscore.util.NexusOperations;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent e) {

        e.getDrops().removeIf(NexusOperations::isNexus);
    }
}
