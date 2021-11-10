package brawl.griefpreventionmodule.util;

import brawl.nexuscore.NexusController;
import me.ryanhamshire.GriefPrevention.Claim;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class WorldOperations {

    static Material nexusMaterial = Material.getMaterial(NexusController.nexusBlockMaterial);

    public static Location addNexusToCenterOfClaim(Claim claim)
    {
        Location topLocation    = claim.getGreaterBoundaryCorner();
        Location bottomLocation = claim.getLesserBoundaryCorner();

        World world = topLocation.getWorld();

        int x;
        int y;
        int z;

        x = (int) (topLocation.getX()+bottomLocation.getX())/2;
        z = (int) (topLocation.getZ()+bottomLocation.getZ())/2;
        y = getHighestY(world,x,z);

        Location centerLocation = new Location(world, x, y, z);

        centerLocation.getBlock().setType(Objects.requireNonNull(nexusMaterial));

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

    public static int getHighestY(World world, int x, int z){
        int i = 255;
        while(i>0){
            if(new Location(world, x, i, z).getBlock().getType()!=Material.AIR)
                return i;
            i--;
        }
        return 0;
    }

}
