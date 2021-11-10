package brawl.griefpreventionmodule.util;

import brawl.nexuscore.NexusController;
import me.ryanhamshire.GriefPrevention.Claim;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class WorldOperations {

    static Material nexusMaterial = Material.getMaterial(NexusController.nexusBlockMaterial);

    public static Location addNexusToCenterOfClaim(Claim claim)
    {
        Location topLocation    = claim.getGreaterBoundaryCorner();
        Location bottomLocation = claim.getLesserBoundaryCorner();

        System.out.println(topLocation);
        System.out.println(bottomLocation);

        double x;
        double y;
        double z;

        x = Math.round((topLocation.getX()+bottomLocation.getX())/2);
        y = Math.round((topLocation.getY()+bottomLocation.getY())/2);
        z = Math.round((topLocation.getZ()+bottomLocation.getZ())/2);

        Location centerLocation = new Location(topLocation.getWorld(), x, y, z);

        centerLocation.getBlock().setType(Objects.requireNonNull(nexusMaterial));

        System.out.println(centerLocation);

        return centerLocation;
    }

    public static List<Location> removeFromClaim(Claim claim)
    {

        List<Location> locations = NexusController.nexusBlocks
                .stream()
                .filter(location -> claim.contains(location,true, true))
                .collect(Collectors.toList());

        locations.forEach(location -> location.getBlock().setType(Material.AIR));

        return locations;
    }
}
