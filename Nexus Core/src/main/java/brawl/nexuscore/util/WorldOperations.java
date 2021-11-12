package brawl.nexuscore.util;

import brawl.nexuscore.NexusController;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import java.util.List;
import java.util.Objects;

public class WorldOperations {

    public static void removeFromLocation(Location location)
    {

        if (location == null)
            return;

        location.getBlock()
                .setType(Material.AIR);
    }

    public static void addNexusToLocation(Location location)
    {
        location.getBlock().setType(Objects.requireNonNull(NexusController.nexusBlockMaterial));
    }


    public static Location getCenterLocation(Location topLocation, Location bottomLocation)
    {

        World world = topLocation.getWorld();

        int x;
        int y;
        int z;

        x = (int) (topLocation.getX()+bottomLocation.getX())/2;
        z = (int) (topLocation.getZ()+bottomLocation.getZ())/2;
        y = getHighestY(world,x,z)+1;

        return new Location(world, x, y, z);
    }

    public static Location getCenterLocation(Chunk c)
    {
        Location center = new Location(c.getWorld(), c.getX() << 4, 64, c.getZ() << 4).add(8, 0, 8);
        center.setY(getHighestY(c.getWorld(),center.getBlockX(),center.getBlockZ()) + 1);

        return center;
    }

    public static int getHighestY(World world, int x, int z){
        int i = 255;
        while(i>0){
            Location tempLocation = new Location(world, x, i, z);
            if(tempLocation.getBlock().getType()!=Material.AIR)
                return i;
            i--;
        }
        return 0;
    }

    public static Location getNexusAtChunk(Chunk c)
    {
        Location center     = new Location(c.getWorld(), c.getX() << 4, 64, c.getZ() << 4).add(8, 0, 8);

        int      centerY    = getNexusY(c.getWorld(),center.getBlockX(),center.getBlockZ());

        if (centerY == -1)
            return null;

        center.setY(centerY);
        return center;
    }

    public static int getNexusY(World world, int x, int z)
    {
        int i = 255;
        while (i>0){
            Location tempLocation = new Location(world, x, i, z);
            if(NexusController.nexusBlocks.stream().anyMatch(location -> location.equals(tempLocation)))
                return i;
            i--;
        }
        return -1;
    }

    public static List<Location> removeFromClaim(List<Location> locations)
    {

        locations.forEach(location -> location.getBlock().setType(Material.AIR));

        return locations;
    }

    public static int getRadius(Location lesserBoundaryCorner, Location greaterBoundaryCorner)
    {

        int firstDistance   = Math.abs(lesserBoundaryCorner.getBlockX() - greaterBoundaryCorner.getBlockX());
        int secondDistance  = Math.abs(lesserBoundaryCorner.getBlockZ() - greaterBoundaryCorner.getBlockZ());


        if (firstDistance > secondDistance)
            return secondDistance/2;
        return firstDistance/2;

    }

}
