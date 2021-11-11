package brawl.griefpreventionmodule.utils;

import brawl.nexuscore.NexusController;
import me.ryanhamshire.GriefPrevention.Claim;
import org.bukkit.Location;

import java.util.List;
import java.util.stream.Collectors;

public class GriefPreventionOperations {

    public static List<Location> getNexusLocationsFromClaim(Claim claim)
    {
        return NexusController.nexusBlocks
            .stream()
            .filter(location -> claim.contains(location,true, true))
            .collect(Collectors.toList());

    }

}
