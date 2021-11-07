package main.java.events;

import brawl.factionsnexus.FactionsNexusController;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.Objects;

public class FactionSetHomeListener implements Listener {

    String cannotSetHomeManuallyError;

    public FactionSetHomeListener()
    {
        cannotSetHomeManuallyError = FactionsNexusController.plugin.getConfig().getString("cannotSetHomeManuallyError");
    }

    @EventHandler
    public void FactionSetHome(PlayerCommandPreprocessEvent event)
    {
        String message          =   event.getMessage();
        String commandName      =   message.split(" ")[0];
        int commandLength       =   message.split(" ").length;

        if (!Objects.equals(commandName, "/f"))
            return;
        if (commandLength < 2)
            return;
        String commandArgument  =   message.split(" ")[1];
        if (Objects.equals(commandArgument, "sethome"))
        {
            event.getPlayer().sendMessage(cannotSetHomeManuallyError);
            event.setCancelled(true);
        }
    }
}
