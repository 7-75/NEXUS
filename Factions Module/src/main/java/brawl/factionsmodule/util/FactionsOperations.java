package brawl.factionsmodule.util;

import brawl.factionsmodule.FactionsModuleController;
import brawl.nexuscore.events.NexusRemovedEvent;
import brawl.nexuscore.util.WorldOperations;
import com.massivecraft.factions.Board;
import com.massivecraft.factions.Faction;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class FactionsOperations {

    public static Faction getFactionByLocation(Location location)
    {

        return FactionsModuleController.factionsAPI.getAllFactions()
                .stream()
                .filter(Faction::hasHome)
                .filter(faction -> faction.getHome().getChunk().equals(location.getChunk()))
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

    public static void removeNexus(Faction faction)
    {
        Location location       = faction.getHome();

        Location nexusLocation  = WorldOperations.getNexusAtChunk(location.getChunk());

        NexusRemovedEvent nexusRemovedEvent = new NexusRemovedEvent(nexusLocation);
        Bukkit.getPluginManager().callEvent(nexusRemovedEvent);

    }

}
