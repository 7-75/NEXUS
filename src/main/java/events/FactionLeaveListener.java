package events;

import brawl.factionsnexus.FactionsNexus;
import com.elmakers.mine.bukkit.api.magic.MagicAPI;
import com.elmakers.mine.bukkit.api.wand.Wand;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.event.FPlayerLeaveEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import util.RemoveNexusFromInventory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

public class FactionLeaveListener implements Listener {

    HashMap                     nexuses;
    String                      lastPlayerInFactionError;
    BufferedReader              reader;
    Wand                        nexus;
    ItemStack                   nexusItemStack;
    String                      nameOfTheBeaconWand;
    RemoveNexusFromInventory    removeNexusFromInventory;

    public FactionLeaveListener(JavaPlugin plugin, MagicAPI magicAPI, HashMap nexuses) throws FileNotFoundException {
        lastPlayerInFactionError    = plugin.getConfig().getString("lastPlayerInFactionError");
        nameOfTheBeaconWand         = plugin.getConfig().getString("nameOfTheBeaconWand");
        nexus                       = magicAPI.createWand(nameOfTheBeaconWand);
        nexusItemStack              = nexus.getItem();
        reader                      = new BufferedReader(new FileReader("Nexuses.json"));
        removeNexusFromInventory    = new RemoveNexusFromInventory(magicAPI);
    }

    @EventHandler
    public void FactionLeave (FPlayerLeaveEvent event) {

        FPlayer     fPlayer             = event.getfPlayer();
        Faction     faction             = fPlayer.getFaction();
        Inventory   inventory           = fPlayer.getPlayer().getInventory();

        if (faction.getFPlayers().size() == 1)
        {
            removeNexusFromInventory.remove(inventory);
            nexuses.remove(faction);

        }

    }

}
