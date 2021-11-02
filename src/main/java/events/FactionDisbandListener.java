package events;

import brawl.factionsnexus.FactionsNexus;
import brawl.factionsnexus.NexusController;
import com.elmakers.mine.bukkit.api.wand.Wand;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.event.FactionDisbandEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import util.NexusOperations;


public class FactionDisbandListener implements Listener {

    String                  disbandFactionError;
    Wand                    nexus;
    ItemStack               nexusItemStack;
    String                  nameOfTheBeaconWand;
    NexusOperations nexusOperations;

    public FactionDisbandListener(NexusController nexusController) {
        disbandFactionError = nexusController.plugin.getConfig().getString("disbandFactionError");
        nameOfTheBeaconWand = nexusController.plugin.getConfig().getString("nameOfTheBeaconWand");

        nexusOperations     = new NexusOperations(nexusController.plugin, nexusController.magicAPI);

        nexus               = nexusController.magicAPI.createWand(nameOfTheBeaconWand);
        nexusItemStack      = nexus.getItem();
    }

    @EventHandler
    public void factionDisband(FactionDisbandEvent event)
    {

        Inventory   inventory               = event.getFPlayer().getPlayer().getInventory();
        Faction     faction                 = event.getFaction();


        nexusOperations.removeFromPlayer(inventory);
        nexusOperations.removeFromMap(faction);

        FactionsNexus.nexuses.remove(faction);


    }
}
