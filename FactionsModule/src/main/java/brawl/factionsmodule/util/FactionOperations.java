package brawl.factionsmodule.util;

import brawl.factionsmodule.FactionsNexusController;
import brawl.nexuscore.NexusController;
import com.massivecraft.factions.Faction;
import main.java.tasks.FactionRefreshBarrierTask;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.MemoryConfiguration;

public class FactionOperations {

    public static void removeFromMap(Faction faction)
    {

        boolean nexusExistsOnMap = faction.hasHome();

        if (!nexusExistsOnMap)
            return;

        Location location = faction.getHome().getBlock().getLocation();

        location.getBlock()
                .setType(Material.AIR);
    }


    public static void addMagicBlockToMap(Location location)
    {

        Faction faction = FactionsOperations.getFactionByLocation(location);

        if (faction == null)
            return;

        double     power       = faction.getPower();


        ConfigurationSection config = new MemoryConfiguration();
        config.set("cast.spells", NexusController.spellToCastFromTheNexus + " " + NexusController.parameterToBeProportionalToPower + " " + power);

        NexusController.magicAPI.getController().addMagicBlock
                (location, NexusController.nexusMagicBlockTemplateKey, null, null, config);
    }

    public static  void removeMagicBlockFromMap(Location location)
    {

        if (!magicBlockExistsAtLocation(location))
            return;

        NexusController.magicAPI.getController().removeMagicBlock(location);

        Faction faction = FactionsOperations.getFactionByLocation(location);

        FactionRefreshBarrierTask task = SchedulerOperations.getTaskByFactionId(faction.getId());

        assert task != null;
        FactionsNexusController.bukkitScheduler.cancelTask(task.taskId);


    }

    public static boolean magicBlockExistsAtLocation (Location location)
    {

        return NexusController.magicAPI.getController()
        .getMagicBlocks()
        .stream()
        .anyMatch(magicBlock -> magicBlock
                .getLocation()
                .equals(location));

    }

}
