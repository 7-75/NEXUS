package brawl.factionsmodule.util;

import brawl.factionsmodule.FactionsNexusController;
import com.massivecraft.factions.Faction;
import org.bukkit.Location;

public class FactionsOperations {

    public static Faction getFactionByLocation(Location location)
    {

        return FactionsNexusController.factionsAPI.getAllFactions()
                .stream()
                .filter(Faction::hasHome)
                .filter(faction1 -> faction1.getHome().equals(location))
                .findFirst()
                .orElse(null);

    }

}
