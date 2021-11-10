package brawl.factionsmodule.listeners;

import brawl.factionsmodule.FactionsModuleController;
import brawl.factionsmodule.util.FactionsOperations;
import brawl.nexuscore.events.NexusBrokenEvent;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.Factions;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NexusDestroyedListener implements Listener {
    @EventHandler
    public void destroyed(NexusBrokenEvent event) {
        Location brokenBlockLocation = event.getLocation();

        Faction faction = FactionsOperations.getFactionByLocation(brokenBlockLocation);

        if (faction == null)
            return;

        String factionId = faction.getId();
        String factionTag = faction.getTag();

        if (!faction.getHome().equals(brokenBlockLocation))
            return;
        Factions.getInstance().removeFaction(factionId);
        String factionNexusWasBrokenMessage =
                FactionsModuleController.plugin.getConfig().getString("factionNexusWasBrokenMessage");

        Bukkit.getServer().broadcast(Component.text(factionTag + factionNexusWasBrokenMessage));

    }
}
