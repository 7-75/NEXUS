package events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.Objects;

public class FactionSetHomeListener implements Listener {
    @EventHandler
    public void FactionSetHome(PlayerCommandPreprocessEvent event)
    {
        String message          =   event.getMessage();
        String commandName      =   message.split(" ")[0];
        String commandArgument  =   message.split(" ")[1];

        if (!Objects.equals(commandName, "f"))
            return;

        if (Objects.equals(commandArgument, "sethome"))
            event.setCancelled(true);

    }
}
