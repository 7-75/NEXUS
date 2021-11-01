package events;

import com.elmakers.mine.bukkit.api.magic.MagicAPI;
import com.elmakers.mine.bukkit.api.wand.Wand;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.Faction;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class BlockPlaceListener implements Listener {

    Wand        beaconWandTemplate;
    Material    beaconWandMaterial;
    ItemStack   beaconWandItemStack;
    String      beaconWandTemplateName;

    String youCannotPlaceWhileNotInAFactionError;

    public BlockPlaceListener(JavaPlugin plugin, MagicAPI magicAPI)
    {
        beaconWandTemplateName                      = plugin.getConfig().getString("NameOfTheBeaconWand");
        youCannotPlaceWhileNotInAFactionError       = plugin.getConfig().getString("youCannotPlaceWhileInAFactionError");

        beaconWandTemplate                          = magicAPI.createWand(beaconWandTemplateName);
        beaconWandMaterial                          = Objects.requireNonNull(beaconWandTemplate.getItem()).getType();
        beaconWandItemStack                         = beaconWandTemplate.getItem();
    }

    @EventHandler
    public void BlockPlaceEvent(BlockPlaceEvent event) {
        Player      player              = event.getPlayer();
        FPlayer     fPlayer             = FPlayers.getInstance().getByPlayer(player);
        Faction     faction             = fPlayer.getFaction();
        ItemStack   placedItem          = event.getItemInHand();
        Block       placedBlock         = event.getBlockPlaced();
        Material    placedMaterial      = placedBlock.getBlockData().getMaterial();
        Location    placedBlockLocation = placedBlock.getLocation();

        if (placedMaterial != beaconWandMaterial)
            return;
        if (placedItem != beaconWandItemStack)
            return;

        if (!fPlayer.hasFaction())
        {
                event.setCancelled(true);
                event.getPlayer().sendMessage(youCannotPlaceWhileNotInAFactionError);
        }
        else
        {
                faction.setHome(placedBlockLocation);
        }
    }
}
