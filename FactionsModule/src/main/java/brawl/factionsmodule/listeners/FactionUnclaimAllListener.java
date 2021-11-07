package brawl.factionsmodule.listeners;

import brawl.factionsmodule.FactionsNexusController;
import brawl.factionsmodule.util.FactionAddonOperations;
import brawl.factionsmodule.util.SchedulerOperations;
import brawl.nexuscore.util.NexusOperations;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.event.LandUnclaimAllEvent;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class FactionUnclaimAllListener implements Listener {

    @EventHandler
    public void factionUnclaim(LandUnclaimAllEvent event) throws Exception {
        FPlayer fPlayer     = event.getfPlayer();
        Player player       = fPlayer.getPlayer();
        Faction faction     = fPlayer.getFaction();

        Location location                    = faction
                .getHome()
                .getBlock()
                .getLocation();

        try{
            NexusOperations.addToInventory(player);
        } catch (Exception e) {
            player.sendMessage(e.getMessage());
            event.setCancelled(true);
            return;
        }

        FactionAddonOperations.removeFromMap(faction);
        FactionAddonOperations.removeMagicBlockFromMap(location);
        NexusOperations.addToInventory(player);
        int taskId = SchedulerOperations.getTaskByFactionId(faction.getId()).taskId;
        FactionsNexusController.bukkitScheduler.cancelTask(taskId);
    }
}
