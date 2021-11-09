package brawl.nexuscore.listeners;


import brawl.nexuscore.util.NexusOperations;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ItemShiftClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getClick().isShiftClick()) {
            Inventory clicked = event.getClickedInventory();
            if (clicked == event.getWhoClicked().getInventory()) {
                ItemStack clickedOn = event.getCurrentItem();

                if (clickedOn != null && NexusOperations.isNexus(clickedOn)) {
                    event.setCancelled(true);
                }
            }
        }
    }
}

