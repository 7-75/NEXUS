package brawl.factionsmodule.listeners;

import brawl.factionsmodule.FactionsModuleController;
import brawl.factionsmodule.util.FactionsOperations;
import brawl.nexuscore.events.NexusCreatedEvent;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.Faction;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NexusCreatedListener implements Listener {

    public String youCannotPlaceWhileNotInAFactionError;
    public String youCannotPlaceInsideUnclaimedError;
    public String yourHomeWasSetMessage;

    public NexusCreatedListener() {
        youCannotPlaceInsideUnclaimedError = FactionsModuleController.plugin.getConfig().getString("youCannotPlaceInsideUnclaimedError");
        youCannotPlaceWhileNotInAFactionError = FactionsModuleController.plugin.getConfig().getString("youCannotPlaceWhileNotInAFactionError");
        yourHomeWasSetMessage = FactionsModuleController.plugin.getConfig().getString("yourHomeWasSetMessage");
    }

        @EventHandler
    public void created(NexusCreatedEvent event)
    {
        Player player                   = event.getPlayer();
        FPlayer fPlayer                 = FPlayers.getInstance().getByPlayer(player);
        Faction faction                 = fPlayer.getFaction();
        Location placedBlockLocation    = event.getBlock().getLocation();

        if (!fPlayer.hasFaction())
        {
            assert youCannotPlaceWhileNotInAFactionError != null;
            event.getPlayer().sendMessage(youCannotPlaceWhileNotInAFactionError);
            event.setCancelled(true);
        }
        else if (FactionsOperations.IsInsideClaim(placedBlockLocation,faction))
        {

            faction.setHome(placedBlockLocation);
            assert yourHomeWasSetMessage != null;
            player.sendMessage(yourHomeWasSetMessage);
        }
        else
        {
            assert youCannotPlaceInsideUnclaimedError != null;
            event.getPlayer().sendMessage(youCannotPlaceInsideUnclaimedError);
            event.setCancelled(true);
        }
    }
}
