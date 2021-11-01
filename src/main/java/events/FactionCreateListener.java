package events;

import com.elmakers.mine.bukkit.api.magic.MagicAPI;
import com.elmakers.mine.bukkit.api.wand.Wand;
import com.massivecraft.factions.event.FactionCreateEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class FactionCreateListener implements Listener {

    String      nameOfTheBeaconWand;
    Wand        beacon;

    public FactionCreateListener(JavaPlugin plugin, MagicAPI magicAPI)
    {

        nameOfTheBeaconWand             = plugin.getConfig().getString("NameOfTheBeaconWand");
        beacon                          = magicAPI.createWand(nameOfTheBeaconWand);
    }

    @EventHandler
    public void factionCreate(FactionCreateEvent event)
    {
        Player      player              = event.getFPlayer().getPlayer();
        Inventory   inventory           = player.getInventory();

        inventory.addItem(Objects.requireNonNull(beacon.getItem()));


    }

}
