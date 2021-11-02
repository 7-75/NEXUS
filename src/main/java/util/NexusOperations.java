package util;

import brawl.factionsnexus.FactionsNexus;
import com.elmakers.mine.bukkit.api.magic.MagicAPI;
import com.elmakers.mine.bukkit.api.wand.Wand;
import com.massivecraft.factions.Faction;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.Objects;

public class NexusOperations {

    MagicAPI        magicAPI;
    ItemStack       nexusItemStack;
    Wand            nexus;

    String          InventoryFullError;
    String          nameOfTheNexusWand;

    public NexusOperations(JavaPlugin plugin, MagicAPI magicAPI)
    {
        this.magicAPI                   = magicAPI;
        InventoryFullError              = plugin.getConfig().getString("InventoryFullError");
        nameOfTheNexusWand              = plugin.getConfig().getString("NameOfTheBeaconWand");
        nexus                           = magicAPI.createWand(nameOfTheNexusWand);
        nexusItemStack                  = nexus.getItem();
    }

    public void removeFromPlayer(Inventory inventory) {

        Arrays
                .stream(inventory.getContents())
                .filter(Objects::nonNull)
                .filter(magicAPI::isWand)
                .filter(itemStack -> itemStack.getType().equals(Material.BEACON))
                .forEach(item -> item.setAmount(-1));

    }

    public void removeFromMap(Faction faction)
    {
        Location location = (Location) FactionsNexus.nexuses.get(faction);

        location.getBlock()
                .setType(Material.AIR);
    }

    public void addToInventory(Player player) throws Exception {
        Inventory inventory = player.getInventory();

        if (player.getInventory().firstEmpty() == -1)
            throw new Exception(InventoryFullError);
        else
        {
            inventory.addItem(Objects.requireNonNull(nexus.getItem()));
        }

    }

}
