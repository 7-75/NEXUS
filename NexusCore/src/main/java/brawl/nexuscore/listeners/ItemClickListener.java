package brawl.nexuscore.listeners;

import brawl.nexuscore.util.NexusOperations;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ItemClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory clicked = event.getClickedInventory();
        if (clicked != event.getWhoClicked().getInventory()) { // Note: !=
            // The cursor item is going into the top inventory
            ItemStack onCursor = event.getCursor();

            if (onCursor != null && NexusOperations.isNexus(onCursor)){
                event.setCancelled(true);
            }
        }
    }
}
