package brawl.factionsmodule.util;

import brawl.factionsmodule.FactionsModuleController;
import com.massivecraft.factions.Board;
import com.massivecraft.factions.Faction;
import org.bukkit.Location;

public class FactionsOperations {

    public static Faction getFactionByLocation(Location location)
    {

        return FactionsModuleController.factionsAPI.getAllFactions()
                .stream()
                .filter(Faction::hasHome)
                .filter(faction1 -> faction1.getHome().equals(location))
                .findFirst()
                .orElse(null);

    }


    public static boolean isInsideClaim(Location location, Faction faction)
    { return Board
            .getInstance()
            .getAllClaims(faction)
            .stream()
            .anyMatch(fLocation -> fLocation.isInChunk(location));

    }
}
