package brawl.factionsmodule.listeners;

import brawl.nexuscore.util.NexusOperations;
import brawl.nexuscore.util.WorldOperations;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.event.FactionDisbandEvent;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;


public class FactionDisbandListener implements Listener {

    @EventHandler
    public void factionDisband(FactionDisbandEvent event)
    {

        Inventory   inventory               = event.getFPlayer().getPlayer().getInventory();
        Faction     faction                 = event.getFaction();
        Location    location                = faction.getHome();

        NexusOperations.removeFromPlayer(inventory);
        WorldOperations.removeFromMap(location);
    }
}
