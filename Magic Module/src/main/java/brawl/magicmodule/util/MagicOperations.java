package brawl.magicmodule.util;

import brawl.magicmodule.MagicModuleController;
import org.bukkit.Location;

public class MagicOperations {

    public static void addMagicBlockToMap(Location location)
    {

        MagicModuleController.magicAPI.getController().addMagicBlock
                (location, MagicModuleController.nexusMagicBlockTemplateKey, null, null, null);


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
