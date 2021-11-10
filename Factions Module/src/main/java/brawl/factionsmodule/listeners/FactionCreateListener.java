package brawl.factionsmodule.listeners;

import brawl.nexuscore.util.NexusOperations;
import com.massivecraft.factions.event.FactionCreateEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class FactionCreateListener implements Listener {

    @EventHandler
    public void factionCreate(FactionCreateEvent event) {
        Player      player              = event.getFPlayer().getPlayer();

        NexusOperations.removeFromPlayer(player);

        try
        {
            NexusOperations.addToInventory(player);
        } catch (Exception e)
        {
            player.sendMessage(e.getMessage());
        }

    }

}
