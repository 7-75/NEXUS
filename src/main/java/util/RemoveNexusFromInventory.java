package util;

import com.elmakers.mine.bukkit.api.magic.MagicAPI;
import org.bukkit.inventory.Inventory;

import java.util.Arrays;
import java.util.Objects;

public class RemoveNexusFromInventory {

    MagicAPI magicAPI;

    public RemoveNexusFromInventory(MagicAPI magicAPI)
    {
        this.magicAPI = magicAPI;
    }

    public void remove(Inventory inventory) {

        Arrays
                .stream(inventory.getContents())
                .filter(Objects::nonNull)
                .filter(magicAPI::isWand)
                .forEach(item -> item.setAmount(-1));

    }
}
