package brawl.nexuscore.util;

import org.bukkit.Location;
import org.bukkit.Material;

public class WorldOperations {

    public static void removeFromMap(Location location)
    {

        if (location == null)
            return;

        location.getBlock()
                .setType(Material.AIR);
    }

}
