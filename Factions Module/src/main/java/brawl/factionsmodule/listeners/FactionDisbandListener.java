package brawl.factionsmodule.listeners;

import brawl.factionsmodule.FactionsModuleController;
import brawl.nexuscore.events.NexusBrokenEvent;
import brawl.nexuscore.util.NexusOperations;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.event.FactionDisbandEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;


public class FactionDisbandListener implements Listener {

    boolean autoCenterChunkMode;

    public FactionDisbandListener()
    {

        autoCenterChunkMode        = FactionsModuleController.plugin.getConfig().getBoolean("autoCenterChunkMode");
    }

    @EventHandler
    public void factionDisband(FactionDisbandEvent event)
    {

        Player      player                  = event.getPlayer();
        Faction     faction                 = event.getFaction();
        Location    location                = faction.getHome();

        if (!autoCenterChunkMode)
            NexusOperations.removeFromPlayer(player);
        else
        {
            NexusBrokenEvent nexusBrokenEvent = new NexusBrokenEvent(location);
            Bukkit.getPluginManager().callEvent(nexusBrokenEvent);
        }
    }
}
