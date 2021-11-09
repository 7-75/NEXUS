package brawl.nexuscore.util;

import brawl.nexuscore.events.NexusRemovedEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

public class WorldOperations {

    public static void removeFromMap(Location location)
    {

        if (location == null)
            return;

        location.getBlock()
                .setType(Material.AIR);

        NexusRemovedEvent nexusRemoved = new NexusRemovedEvent(location);
        Bukkit.getPluginManager().callEvent(nexusRemoved);
    }

}
