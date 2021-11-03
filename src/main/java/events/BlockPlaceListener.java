package events;

import brawl.factionsnexus.NexusController;
import com.elmakers.mine.bukkit.api.wand.Wand;
import com.massivecraft.factions.Board;
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

import java.util.Objects;

public class BlockPlaceListener implements Listener {

    Wand                        beaconWandTemplate;
    Material                    beaconWandMaterial;
    String                      beaconWandTemplateName;
    String                      youCannotPlaceWhileNotInAFactionError;
    String                      youCannotPlaceInsideUnclaimedError;
    String                      yourHomeWasSetMessage;

        public BlockPlaceListener() {

            youCannotPlaceInsideUnclaimedError          = NexusController.plugin.getConfig().getString("youCannotPlaceInsideUnclaimedError");
            youCannotPlaceWhileNotInAFactionError       = NexusController.plugin.getConfig().getString("youCannotPlaceWhileNotInAFactionError");
            yourHomeWasSetMessage                       = NexusController.plugin.getConfig().getString("yourHomeWasSetMessage");
            beaconWandTemplateName                      = NexusController.plugin.getConfig().getString("NameOfTheBeaconWand");
            beaconWandTemplate                          = NexusController.magicAPI.createWand(beaconWandTemplateName);
            beaconWandMaterial                          = Objects.requireNonNull(beaconWandTemplate.getItem()).getType();
    }

    @EventHandler
    public void blockPlace(BlockPlaceEvent event) {
        Block       placedBlock         = event.getBlockPlaced();
        Material    placedMaterial      = placedBlock.getBlockData().getMaterial();
        if (placedMaterial != beaconWandMaterial)
            return;

        ItemStack   placedItem              = event.getItemInHand();
        if (!NexusController.magicAPI.isWand(placedItem))
            return;

        Player      player              = event.getPlayer();
        FPlayer     fPlayer             = FPlayers.getInstance().getByPlayer(player);
        Faction     faction             = fPlayer.getFaction();
        Location    placedBlockLocation = placedBlock.getLocation();

        boolean beaconIsInsideClaim = Board
                .getInstance()
                .getAllClaims(fPlayer.getFaction())
                .stream()
                .anyMatch(fLocation -> fLocation.isInChunk(placedBlockLocation));

        if (!fPlayer.hasFaction())
        {
            event.getPlayer().sendMessage(youCannotPlaceWhileNotInAFactionError);
            event.setCancelled(true);
        }
        else if (beaconIsInsideClaim)
        {
            faction.setHome(placedBlockLocation);
            NexusController.nexuses.put(faction, placedBlockLocation);
            player.sendMessage(yourHomeWasSetMessage);

        }
        else
        {
            event.getPlayer().sendMessage(youCannotPlaceInsideUnclaimedError);
            event.setCancelled(true);
        }
    }
}
