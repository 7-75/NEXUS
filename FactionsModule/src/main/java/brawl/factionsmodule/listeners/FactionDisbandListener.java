package brawl.factionsmodule.listeners;

import com.massivecraft.factions.Faction;
import com.massivecraft.factions.event.FactionDisbandEvent;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import brawl.factionsmodule.util.FactionOperations;
import brawl.nexuscore.util.NexusOperations;


public class FactionDisbandListener implements Listener {

    @EventHandler
    public void factionDisband(FactionDisbandEvent event)
    {

        Inventory   inventory               = event.getFPlayer().getPlayer().getInventory();
        Faction     faction                 = event.getFaction();
        Location    fHome                   = faction.getHome();

        FactionOperations.removeMagicBlockFromMap(fHome);
        NexusOperations.removeFromPlayer(inventory);
        FactionOperations.removeFromMap(faction);

    }
}
