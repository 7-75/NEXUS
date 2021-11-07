package brawl.factionsmodule.listeners;

import brawl.factionsmodule.FactionsNexusController;
import brawl.factionsmodule.util.SchedulerOperations;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.event.FactionDisbandEvent;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import brawl.factionsmodule.util.FactionAddonOperations;
import brawl.nexuscore.util.NexusOperations;


public class FactionDisbandListener implements Listener {

    @EventHandler
    public void factionDisband(FactionDisbandEvent event)
    {

        Inventory   inventory               = event.getFPlayer().getPlayer().getInventory();
        Faction     faction                 = event.getFaction();
        Location    fHome                   = faction.getHome();

        FactionAddonOperations.removeMagicBlockFromMap(fHome);
        NexusOperations.removeFromPlayer(inventory);
        FactionAddonOperations.removeFromMap(faction);
        int taskId = SchedulerOperations.getTaskByFactionId(faction.getId()).taskId;
        FactionsNexusController.bukkitScheduler.cancelTask(taskId);
    }
}
