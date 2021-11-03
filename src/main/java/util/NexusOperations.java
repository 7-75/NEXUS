package util;

import brawl.factionsnexus.NexusController;
import com.elmakers.mine.bukkit.api.wand.Wand;
import com.massivecraft.factions.Faction;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Objects;

public class NexusOperations {
    ItemStack       nexusItemStack;
    String          nameOfTheNexusWand;
    static          Wand                nexus;
    static          String              InventoryFullError;

    public NexusOperations()
    {
        InventoryFullError              = NexusController.plugin.getConfig().getString("InventoryFullError");
        nameOfTheNexusWand              = NexusController.plugin.getConfig().getString("NameOfTheBeaconWand");
        nexus                           = NexusController.magicAPI.createWand(nameOfTheNexusWand);
        nexusItemStack                  = nexus.getItem();
    }

    public static void removeFromPlayer(Inventory inventory) {

        Arrays
                .stream(inventory.getContents())
                .filter(Objects::nonNull)
                .filter(NexusController.magicAPI::isWand)
                .filter(itemStack -> itemStack.getType().equals(Material.BEACON))
                .forEach(item -> item.setAmount(-1));

    }

    public static void removeFromMap(Faction faction)
    {

        boolean nexusExistsOnMap = faction.hasHome();

        if (!nexusExistsOnMap)
            return;

        Location location = faction.getHome().getBlock().getLocation();

        location.getBlock()
                .setType(Material.AIR);
    }

    public static void addToInventory(Player player) throws Exception {
        Inventory inventory = player.getInventory();

        if (player.getInventory().firstEmpty() == -1)
            throw new Exception(InventoryFullError);
        else
        {
            inventory.addItem(Objects.requireNonNull(nexus.getItem()));
        }

    }

}
