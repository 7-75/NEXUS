package main.java.events;

import brawl.factionsnexus.FactionsNexusController;
import com.elmakers.mine.bukkit.api.wand.Wand;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.event.FactionDisbandEvent;
import org.bukkit.Location;
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

    public FactionDisbandListener() {
        disbandFactionError = FactionsNexusController.plugin.getConfig().getString("disbandFactionError");
        nameOfTheBeaconWand = FactionsNexusController.plugin.getConfig().getString("nameOfTheBeaconWand");

        nexus               = FactionsNexusController.magicAPI.createWand(nameOfTheBeaconWand);
        nexusItemStack      = nexus.getItem();
    }

    @EventHandler
    public void factionDisband(FactionDisbandEvent event)
    {

        Inventory   inventory               = event.getFPlayer().getPlayer().getInventory();
        Faction     faction                 = event.getFaction();
        Location    fHome                   = faction.getHome();

        NexusOperations.removeMagicBlockFromMap(fHome);
        NexusOperations.removeFromPlayer(inventory);
        NexusOperations.removeFromMap(faction);

    }
}
