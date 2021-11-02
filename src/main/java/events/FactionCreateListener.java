package events;

import brawl.factionsnexus.NexusController;
import com.massivecraft.factions.event.FactionCreateEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import util.NexusOperations;

public class FactionCreateListener implements Listener {

    NexusOperations nexusOperations;

    public FactionCreateListener(NexusController nexusController)
    {
        nexusOperations        = new NexusOperations(nexusController.plugin, nexusController.magicAPI);
    }

    @EventHandler
    public void factionCreate(FactionCreateEvent event) {
        Player      player              = event.getFPlayer().getPlayer();
        Inventory   inventory           = player.getInventory();

        nexusOperations.removeFromPlayer(inventory);

        try
        {
            nexusOperations.addToInventory(player);
        } catch (Exception e)
        {
            player.sendMessage(e.getMessage());
        }

    }

}
