package events;

import brawl.factionsnexus.FactionsNexus;
import com.elmakers.mine.bukkit.api.magic.MagicAPI;
import com.elmakers.mine.bukkit.api.wand.Wand;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.event.FactionDisbandEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import util.RemoveNexusFromInventory;


public class FactionDisbandListener implements Listener {

    String                  disbandFactionError;
    Wand                    nexus;
    ItemStack               nexusItemStack;
    String                  nameOfTheBeaconWand;
    RemoveNexusFromInventory removeNexusFromInventory;

    public FactionDisbandListener(JavaPlugin plugin, MagicAPI magicAPI) {
        disbandFactionError = plugin.getConfig().getString("disbandFactionError");
        nameOfTheBeaconWand = plugin.getConfig().getString("nameOfTheBeaconWand");

        removeNexusFromInventory = new RemoveNexusFromInventory(magicAPI);

        nexus               = magicAPI.createWand(nameOfTheBeaconWand);
        nexusItemStack      = nexus.getItem();
    }

    @EventHandler
    public void factionDisband(FactionDisbandEvent event)
    {

        Inventory   inventory               = event.getFPlayer().getPlayer().getInventory();
        Faction     faction                 = event.getFaction();

        removeNexusFromInventory.remove(inventory);
        FactionsNexus.nexuses.remove(faction);


    }
}
