package util;

import brawl.factionsnexus.FactionsNexus;
import com.elmakers.mine.bukkit.api.magic.MagicAPI;
import com.massivecraft.factions.Faction;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

import java.util.Arrays;
import java.util.Objects;

public class RemoveNexus {

    MagicAPI magicAPI;

    public RemoveNexus(MagicAPI magicAPI)
    {
        this.magicAPI = magicAPI;
    }

    public void fromInventory(Inventory inventory) {

        Arrays
                .stream(inventory.getContents())
                .filter(Objects::nonNull)
                .filter(magicAPI::isWand)
                .forEach(item -> item.setAmount(-1));

    }

    public void fromMap(Faction faction)
    {
        Location location = (Location) FactionsNexus.nexuses.get(faction);

        location.getBlock()
                .setType(Material.AIR);
    }

}
