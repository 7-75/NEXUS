package brawl.factionsmodule.listeners;

import brawl.factionsmodule.FactionsModuleController;
import brawl.factionsmodule.util.FactionsOperations;
import brawl.nexuscore.events.NexusCreatedEvent;
import brawl.nexuscore.util.WorldOperations;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.perms.Role;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.Objects;

public class FactionSetHomeListener implements Listener {

    String cannotSetHomeManuallyError;
    boolean autoCenterChunkMode;

    public FactionSetHomeListener()
    {
        cannotSetHomeManuallyError = FactionsModuleController.plugin.getConfig().getString("cannotSetHomeManuallyError");
        autoCenterChunkMode        = FactionsModuleController.plugin.getConfig().getBoolean("autoCenterChunkMode");
    }

    @EventHandler
    public void FactionSetHome(PlayerCommandPreprocessEvent event) {
        String message = event.getMessage();
        String commandName = message.split(" ")[0];
        int commandLength = message.split(" ").length;
        Player player = event.getPlayer();

        if (!Objects.equals(commandName, "/f")) {
            return;
        }

        if (commandLength < 2) {
            return;
        }

        String commandArgument = message.split(" ")[1];

        if (!Objects.equals(commandArgument, "sethome"))
        {
            return;
        }

        if (!autoCenterChunkMode)
        {
            player.sendMessage(cannotSetHomeManuallyError);
            return;
        }

        Location playerLocation = event.getPlayer().getLocation();
        FPlayer fPlayer         = FPlayers.getInstance().getByPlayer(player);
        Faction faction         = fPlayer.getFaction();
        Role    role            = fPlayer.getRole();

        if(!FactionsOperations.isInsideClaim(playerLocation, faction))
            return;
        if(!role.isAtLeast(Role.MODERATOR))
            return;

        if(faction.hasHome())
        {
            FactionsOperations.removeNexus(faction);
        }
        Location chunkCenterLocation = WorldOperations.getChunkCenter(playerLocation.getChunk());
        NexusCreatedEvent nexusCreatedEvent = new NexusCreatedEvent(chunkCenterLocation, 0.0);
        Bukkit.getPluginManager().callEvent(nexusCreatedEvent);
    }
}
