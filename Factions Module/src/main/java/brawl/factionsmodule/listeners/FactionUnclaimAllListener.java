package brawl.factionsmodule.listeners;

import brawl.factionsmodule.FactionsModuleController;
import brawl.factionsmodule.util.FactionsOperations;
import brawl.nexuscore.util.NexusOperations;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.event.LandUnclaimAllEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class FactionUnclaimAllListener implements Listener {

    boolean autoCenterChunkMode;

    public FactionUnclaimAllListener()
    {
        autoCenterChunkMode     = FactionsModuleController.plugin.getConfig().getBoolean("autoCenterChunkMode");
    }

    @EventHandler
    public void factionUnclaim(LandUnclaimAllEvent event) {
        FPlayer fPlayer     = event.getfPlayer();
        Player player       = fPlayer.getPlayer();
        Faction faction     = fPlayer.getFaction();

        if(!faction.hasHome())
            return;

        FactionsOperations.removeNexus(faction);

        if (autoCenterChunkMode)
            return;

        try{
            NexusOperations.addToInventory(player);
        } catch (Exception e) {
            player.sendMessage(e.getMessage());
            event.setCancelled(true);
        }

    }
}
