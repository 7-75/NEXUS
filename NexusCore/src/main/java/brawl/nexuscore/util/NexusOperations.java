package brawl.nexuscore.util;

import brawl.nexuscore.NexusController;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NexusOperations {

    private static  String      inventoryFullError;
    public  static  ItemStack   nexusItemStack;

    public NexusOperations()
    {
        inventoryFullError              = NexusController.plugin.getConfig().getString("inventoryFullError");
        nexusItemStack                  = NexusOperations.nexusBlockFactory();
    }



    public static void addToInventory(Player player) throws Exception {
        Inventory inventory = player.getInventory();

        if (player.getInventory().firstEmpty() == -1)
            throw new Exception(inventoryFullError);
        else
        {
            inventory.addItem(nexusBlockFactory());
        }

    }

    public static void removeFromPlayer(Inventory inventory) {

        inventory.remove(nexusBlockFactory());


    }

    public static ItemStack nexusBlockFactory()
    {
        ItemStack i = new ItemStack(Material.getMaterial(NexusController.nexusBlockMaterial));
        ItemMeta im = i.getItemMeta();
        List<Component> lore = new ArrayList<Component>();
        lore.add(Component.text(NexusController.nexusItemLore));
        im.lore(lore);
        i.setItemMeta(im);
        return i;
    }


    public static boolean isNexus(ItemStack itemStack)
    {
        if (!itemStack.getItemMeta().hasLore())
            return false;
        boolean hasLore = Objects.requireNonNull(itemStack.lore()).contains(Component.text(NexusController.nexusItemLore));
        return hasLore;
    }
}
