package events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class FactionSetHomeListener implements Listener {

    String cannotSetHomeManuallyError;

    public FactionSetHomeListener(JavaPlugin plugin)
    {
        cannotSetHomeManuallyError = plugin.getConfig().getString("cannotSetHomeManuallyError");
    }

    @EventHandler
    public void FactionSetHome(PlayerCommandPreprocessEvent event)
    {
        String message          =   event.getMessage();
        String commandName      =   message.split(" ")[0];
        if (!Objects.equals(commandName, "/f"))
            return;
        String commandArgument  =   message.split(" ")[1];
        if (Objects.equals(commandArgument, "sethome"))
        {
            event.getPlayer().sendMessage(cannotSetHomeManuallyError);
            event.setCancelled(true);
        }
    }
}
