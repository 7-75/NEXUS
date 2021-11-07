package util;

import brawl.nexuscore.NexusController;
import com.elmakers.mine.bukkit.api.wand.Wand;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Arrays;
import java.util.Objects;

public class NexusOperations {

    private static  String inventoryFullError;
    private static  String nexusWandTemplateKey;
    private static  String nexusMagicBlockTemplateKey;
    private static  Wand nexus;


    public NexusOperations()
{
    inventoryFullError                  = NexusController.plugin.getConfig().getString("inventoryFullError");
    nexusWandTemplateKey                = NexusController.plugin.getConfig().getString("nexusWandTemplateKey");
    nexusMagicBlockTemplateKey          = NexusController.plugin.getConfig().getString("nexusMagicBlockTemplateKey");

    nexus                               = NexusController.magicAPI.createWand(nexusWandTemplateKey);

}



    public static void addToInventory(Player player) throws Exception {
        Inventory inventory = player.getInventory();

        if (player.getInventory().firstEmpty() == -1)
            throw new Exception(inventoryFullError);
        else
        {
            inventory.addItem(Objects.requireNonNull(nexus.getItem()));
        }

    }

    public static void removeFromPlayer(Inventory inventory) {

        Arrays
                .stream(inventory.getContents())
                .filter(Objects::nonNull)
                .filter(NexusController.magicAPI::isWand)
                .filter(itemStack -> itemStack.getType().equals(Material.BEACON))
                .forEach(item -> item.setAmount(-1));

    }


}
