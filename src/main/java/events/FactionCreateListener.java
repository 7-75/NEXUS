package events;

import com.elmakers.mine.bukkit.api.magic.MagicAPI;
import com.elmakers.mine.bukkit.api.wand.Wand;
import com.massivecraft.factions.event.FactionCreateEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import util.RemoveNexus;

import java.util.Objects;

public class FactionCreateListener implements Listener {

    String                      nameOfTheBeaconWand;
    Wand                        nexus;
    ItemStack                   nexusItemStack;
    RemoveNexus removeNexusFromInventory;

    public FactionCreateListener(JavaPlugin plugin, MagicAPI magicAPI)
    {

        nameOfTheBeaconWand             = plugin.getConfig().getString("NameOfTheBeaconWand");
        nexus                           = magicAPI.createWand(nameOfTheBeaconWand);
        nexusItemStack                  = nexus.getItem();
        removeNexusFromInventory        = new RemoveNexus(magicAPI);
    }

    @EventHandler
    public void factionCreate(FactionCreateEvent event)
    {
        Player      player              = event.getFPlayer().getPlayer();
        Inventory   inventory           = player.getInventory();

        removeNexusFromInventory.fromInventory(inventory);
        inventory.addItem(Objects.requireNonNull(nexus.getItem()));


    }

}
