package events;

import com.elmakers.mine.bukkit.api.magic.MagicAPI;
import com.elmakers.mine.bukkit.api.wand.Wand;
import com.massivecraft.factions.event.FactionCreateEvent;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.Objects;

public class FactionCreateListener implements Listener {

    String      nameOfTheBeaconWand;
    Wand        nexus;
    ItemStack   nexusItemStack;

    public FactionCreateListener(JavaPlugin plugin, MagicAPI magicAPI)
    {

        nameOfTheBeaconWand             = plugin.getConfig().getString("NameOfTheBeaconWand");
        nexus                           = magicAPI.createWand(nameOfTheBeaconWand);
        nexusItemStack                  = nexus.getItem();
    }

    @EventHandler
    public void factionCreate(FactionCreateEvent event)
    {
        Player      player              = event.getFPlayer().getPlayer();
        Inventory   inventory           = player.getInventory();
        ItemStack[] inventoryItemStacks = inventory.getContents();

        Arrays
                .stream(inventoryItemStacks)
                .filter(Objects::nonNull)
                .filter(item -> item.equals(nexusItemStack))
                .forEach(item -> item.setAmount(-1));

        inventory.addItem(Objects.requireNonNull(nexus.getItem()));


    }

}
