package util;

import brawl.factionsnexus.FactionsNexusController;
import brawl.factionsnexus.SchedulerOperations;
import brawl.nexuscore.NexusController;
import com.elmakers.mine.bukkit.api.wand.Wand;
import com.massivecraft.factions.Faction;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.MemoryConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import main.java.tasks.FactionRefreshBarrierTask;

import java.util.Arrays;
import java.util.Objects;

public class NexusOperations {
    ItemStack       nexusItemStack;
    String          nexusWandTemplateKey;
    static          String              parameterToBeProportionalToPower;
    static          String              spellToCastFromTheNexus;
    static          String              nexusMagicBlockTemplateKey;
    static          String              inventoryFullError;

    public NexusOperations()
    {
        nexusWandTemplateKey                = FactionsNexusController.plugin.getConfig().getString("nexusWandTemplateKey");
        nexusMagicBlockTemplateKey          = FactionsNexusController.plugin.getConfig().getString("nexusMagicBlockTemplateKey");

        inventoryFullError                  = FactionsNexusController.plugin.getConfig().getString("inventoryFullError");

        parameterToBeProportionalToPower    = FactionsNexusController.plugin.getConfig().getString("parameterToBeProportionalToPower");
        spellToCastFromTheNexus             = FactionsNexusController.plugin.getConfig().getString("spellToCastFromTheNexus");

        nexusItemStack                      = nexus.getItem();
    }

    public static void removeFromPlayer(Inventory inventory) {

        Arrays
                .stream(inventory.getContents())
                .filter(Objects::nonNull)
                .filter(NexusController.magicAPI::isWand)
                .filter(itemStack -> itemStack.getType().equals(Material.BEACON))
                .forEach(item -> item.setAmount(-1));

    }

    public static void removeFromMap(Faction faction)
    {

        boolean nexusExistsOnMap = faction.hasHome();

        if (!nexusExistsOnMap)
            return;

        Location location = faction.getHome().getBlock().getLocation();

        location.getBlock()
                .setType(Material.AIR);
    }

    public static void addToInventory(Player player) throws Exception {
        Inventory inventory = player.getInventory();

        if (player.getInventory().firstEmpty() == -1)
            throw new Exception(inventoryFullError);
        else
        {
            inventory.addItem(Objects.requireNonNull(nexus.getItem()));
        }

    }

    public static void addMagicBlockToMap(Location location)
    {

        Faction faction = FactionsOperations.getFactionByLocation(location);

        if (faction == null)
            return;

        double     power       = faction.getPower();


        ConfigurationSection config = new MemoryConfiguration();
        config.set("cast.spells", spellToCastFromTheNexus + " " + parameterToBeProportionalToPower + " " + power);

        FactionsNexusController.magicAPI.getController().addMagicBlock
                (location, nexusMagicBlockTemplateKey, null, null, config);
    }

    public static  void removeMagicBlockFromMap(Location location)
    {

        if (!magicBlockExistsAtLocation(location))
            return;

        FactionsNexusController.magicAPI.getController().removeMagicBlock(location);

        Faction faction = FactionsOperations.getFactionByLocation(location);

        FactionRefreshBarrierTask task = SchedulerOperations.getTaskByFactionId(faction.getId());

        assert task != null;
        FactionsNexusController.bukkitScheduler.cancelTask(task.taskId);


    }

    public static boolean magicBlockExistsAtLocation (Location location)
    {

        return FactionsNexusController.magicAPI.getController()
        .getMagicBlocks()
        .stream()
        .anyMatch(magicBlock -> magicBlock
                .getLocation()
                .equals(location));

    }

}
