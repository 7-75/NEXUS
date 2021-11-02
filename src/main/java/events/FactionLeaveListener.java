package events;

import brawl.factionsnexus.FactionsNexus;
import brawl.factionsnexus.NexusController;
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
    NexusOperations removeNexusFromInventory;

    public FactionLeaveListener(NexusController nexusController) {
        lastPlayerInFactionError    = nexusController.plugin.getConfig().getString("lastPlayerInFactionError");
        nameOfTheBeaconWand         = nexusController.plugin.getConfig().getString("nameOfTheBeaconWand");
        nexus                       = nexusController.magicAPI.createWand(nameOfTheBeaconWand);
        nexusItemStack              = nexus.getItem();
        removeNexusFromInventory    = new NexusOperations(nexusController.plugin, nexusController.magicAPI);
    }

    @EventHandler
    public void factionLeave (FPlayerLeaveEvent event) {

        FPlayer     fPlayer             = event.getfPlayer();
        Faction     faction             = fPlayer.getFaction();
        Inventory   inventory           = fPlayer.getPlayer().getInventory();

        if (faction.getFPlayers().size() == 1)
        {
            removeNexusFromInventory.removeFromPlayer(inventory);
            FactionsNexus.nexuses.remove(faction);

        }

    }

}
