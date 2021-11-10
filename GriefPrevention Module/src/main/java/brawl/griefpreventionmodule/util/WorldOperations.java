package brawl.griefpreventionmodule.util;

import brawl.nexuscore.NexusController;
import me.ryanhamshire.GriefPrevention.Claim;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.Objects;

public class WorldOperations {

    static Material nexusMaterial = Material.getMaterial(NexusController.nexusBlockMaterial);

    public static Location addBlockToCenterOfClaim(Claim claim)
    {
        Location topLocation    = claim.getGreaterBoundaryCorner();
        Location bottomLocation = claim.getLesserBoundaryCorner();

        double x;
        double y;
        double z;

        x = Math.round(topLocation.getX()+bottomLocation.getX()/2);
        y = Math.round(topLocation.getY()+bottomLocation.getY()/2);
        z = Math.round(topLocation.getZ()+bottomLocation.getZ()/2);

        Location centerLocation = new Location(topLocation.getWorld(), x, y, z);

        centerLocation.getBlock().setType(Objects.requireNonNull(nexusMaterial));

        return centerLocation;
    }

    public static void removeFromClaim(Claim claim)
    {

        NexusController.nexusBlocks
                .stream()
                .filter(location -> claim.contains(location,true, true))
                .forEach(location -> location.getBlock().setType(Material.AIR));

    }
}
