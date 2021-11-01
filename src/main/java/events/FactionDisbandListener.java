package events;

import com.massivecraft.factions.event.FactionDisbandEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class FactionDisbandListener implements Listener {
    @EventHandler
    public void factionDisband(FactionDisbandEvent event)
    {
        event.setCancelled(true);
    }
}
