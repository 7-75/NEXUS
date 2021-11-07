package listeners;

import com.massivecraft.factions.event.FactionCreateEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import util.NexusOperations;

public class FactionCreateListener implements Listener {

    @EventHandler
    public void factionCreate(FactionCreateEvent event) {
        Player      player              = event.getFPlayer().getPlayer();
        Inventory   inventory           = player.getInventory();

        NexusOperations.removeFromPlayer(inventory);

        try
        {
            NexusOperations.addToInventory(player);
        } catch (Exception e)
        {
            player.sendMessage(e.getMessage());
        }

    }

}
