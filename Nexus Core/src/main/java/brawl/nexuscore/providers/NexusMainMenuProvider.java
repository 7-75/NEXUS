package brawl.nexuscore.providers;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class NexusMainMenuProvider implements InventoryProvider {

    @Override
    public void init(Player player, InventoryContents contents) {
        for(int i = 0; i < 9; i += 2)
            contents.fillColumn(i, ClickableItem.empty(new ItemStack(Material.BEDROCK)));

        contents.set(1, 1, ClickableItem.of(new ItemStack(Material.COMPASS), e -> {
            if(e.isLeftClick()) {
                player.sendMessage(ChatColor.GOLD + "You left clicked on my compass! "
                        + ChatColor.YELLOW + "Here is a carrot, you deserve it.");

                contents.set(1, 1, ClickableItem.empty(new ItemStack(Material.CARROT_ITEM)));
            }
            else if(e.isRightClick()) {
                player.sendMessage(ChatColor.DARK_RED + "Oh no! The right click was trapped! "
                        + ChatColor.RED + "You found a stick...");

                contents.set(1, 1, ClickableItem.empty(new ItemStack(Material.STICK)));
            }
        }));
    }


    @Override
    public void update(Player player, InventoryContents contents) {}

}