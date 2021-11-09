package brawl.magicmodule.util;

import brawl.magicmodule.MagicModuleController;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.MemoryConfiguration;

public class MagicOperations {

    public static void addMagicBlockToMap(Location location)
    {

        ConfigurationSection config = new MemoryConfiguration();
        config.set("cast.spells", MagicModuleController.spellToCastFromTheNexus);

        MagicModuleController.magicAPI.getController().addMagicBlock
                (location, MagicModuleController.nexusMagicBlockTemplateKey, null, null, config);


    }

    public static  void removeMagicBlockFromMap(Location location)
    {

        if (!magicBlockExistsAtLocation(location))
            return;

        MagicModuleController.magicAPI.getController().removeMagicBlock(location);

    }

    public static boolean magicBlockExistsAtLocation (Location location)
    {

        return MagicModuleController.magicAPI.getController()
                .getMagicBlocks()
                .stream()
                .anyMatch(magicBlock -> magicBlock
                        .getLocation()
                        .equals(location));

    }
}
