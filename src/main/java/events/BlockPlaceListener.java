package events;

import brawl.factionsnexus.FactionsNexus;
import com.elmakers.mine.bukkit.api.magic.MagicAPI;
import com.elmakers.mine.bukkit.api.wand.Wand;
import com.google.gson.Gson;
import com.massivecraft.factions.*;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class BlockPlaceListener implements Listener {

    Wand            beaconWandTemplate;
    Material        beaconWandMaterial;
    String          beaconWandTemplateName;
    MagicAPI        magicAPI;

    Gson            gson;
    BufferedWriter  writer;

    String          youCannotPlaceWhileNotInAFactionError;
    String          youCannotPlaceInsideUnclaimedError;

        public BlockPlaceListener(JavaPlugin plugin, MagicAPI magicAPI) throws IOException {
        this.magicAPI                               = magicAPI;

        gson                                        = new Gson();
        writer                                      = new BufferedWriter(new FileWriter("Nexuses.json"));

        beaconWandTemplateName                      = plugin.getConfig().getString("NameOfTheBeaconWand");
        youCannotPlaceWhileNotInAFactionError       = plugin.getConfig().getString("youCannotPlaceWhileNotInAFactionError");
        youCannotPlaceInsideUnclaimedError          = plugin.getConfig().getString("youCannotPlaceInsideUnclaimedError");

        beaconWandTemplate                          = magicAPI.createWand(beaconWandTemplateName);
        beaconWandMaterial                          = Objects.requireNonNull(beaconWandTemplate.getItem()).getType();
    }

    @EventHandler
    public void BlockPlaceEvent(BlockPlaceEvent event) throws IOException {

        Block       placedBlock         = event.getBlockPlaced();
        Material    placedMaterial      = placedBlock.getBlockData().getMaterial();
        if (placedMaterial != beaconWandMaterial)
            return;

        ItemStack   placedItem              = event.getItemInHand();
        if (!magicAPI.isWand(placedItem))
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
            FactionsNexus.nexuses.put(faction, placedBlockLocation);
        }
        else
        {
            event.getPlayer().sendMessage(youCannotPlaceInsideUnclaimedError);
            event.setCancelled(true);
        }
    }
}
