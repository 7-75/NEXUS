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
import util.RemoveNexus;

public class FactionLeaveListener implements Listener {

    String                      lastPlayerInFactionError;
    Wand                        nexus;
    ItemStack                   nexusItemStack;
    String                      nameOfTheBeaconWand;
    RemoveNexus removeNexusFromInventory;

    public FactionLeaveListener(JavaPlugin plugin, MagicAPI magicAPI) {
        lastPlayerInFactionError    = plugin.getConfig().getString("lastPlayerInFactionError");
        nameOfTheBeaconWand         = plugin.getConfig().getString("nameOfTheBeaconWand");
        nexus                       = magicAPI.createWand(nameOfTheBeaconWand);
        nexusItemStack              = nexus.getItem();
        removeNexusFromInventory    = new RemoveNexus(magicAPI);
    }

    @EventHandler
    public void FactionLeave (FPlayerLeaveEvent event) {

        FPlayer     fPlayer             = event.getfPlayer();
        Faction     faction             = fPlayer.getFaction();
        Inventory   inventory           = fPlayer.getPlayer().getInventory();

        if (faction.getFPlayers().size() == 1)
        {
            removeNexusFromInventory.fromInventory(inventory);
            FactionsNexus.nexuses.remove(faction);

        }

    }

}
