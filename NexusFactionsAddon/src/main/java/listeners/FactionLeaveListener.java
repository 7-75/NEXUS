package listeners;

import brawl.factionsnexus.FactionsNexusController;
import brawl.nexuscore.NexusController;
import com.elmakers.mine.bukkit.api.wand.Wand;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.event.FPlayerLeaveEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import util.NexusOperations;

public class FactionLeaveListener implements Listener {

    String                      lastPlayerInFactionError;
    Wand                        nexus;
    ItemStack                   nexusItemStack;
    String                      nameOfTheBeaconWand;

    public FactionLeaveListener() {
        lastPlayerInFactionError    = FactionsNexusController.plugin.getConfig().getString("lastPlayerInFactionError");
        nameOfTheBeaconWand         = FactionsNexusController.plugin.getConfig().getString("nameOfTheBeaconWand");
        nexus                       = NexusController.magicAPI.createWand(nameOfTheBeaconWand);
        nexusItemStack              = nexus.getItem();
    }

    @EventHandler
    public void factionLeave (FPlayerLeaveEvent event) {

        FPlayer     fPlayer             = event.getfPlayer();
        Faction     faction             = fPlayer.getFaction();
        Inventory   inventory           = fPlayer.getPlayer().getInventory();

        if (faction.getFPlayers().size() == 1)
        {
            NexusOperations.removeFromPlayer(inventory);

        }

    }

}
