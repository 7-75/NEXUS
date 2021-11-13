package brawl.nexuscore.util;

import brawl.nexuscore.NexusController;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Objects;

public class NexusOperations {


    public static void addToInventory(Player player) throws Exception {
        Inventory inventory = player.getInventory();

        if (player.getInventory().firstEmpty() == -1)
            throw new Exception(NexusController.plugin.getConfig().getString("inventoryFullError"));
        else
        {
            inventory.addItem(nexusBlockFactory());
        }

    }

    public static void removeFromPlayer(Player player) {

        player.getInventory().remove(nexusBlockFactory());

    }

    public static ItemStack nexusBlockFactory()
    {
        ItemStack i = new ItemStack(Objects.requireNonNull(NexusController.nexusBlockMaterial));
        ItemMeta im = i.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        lore.add(NexusController.nexusItemLore);
        im.setLore(lore);
        i.setItemMeta(im);
        return i;
    }


    public static boolean isNexus(ItemStack itemStack)
    {
        if (!itemStack.getItemMeta().hasLore())
            return false;
        return Objects.requireNonNull(itemStack.getItemMeta().getLore()).contains(NexusController.nexusItemLore);
    }
}
