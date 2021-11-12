package brawl.magicmodule.util;

import brawl.magicmodule.MagicModuleController;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.MemoryConfiguration;

public class MagicOperations {

    private static final String spellTemplateKey           = MagicModuleController.plugin.getConfig().getString("spellTemplateKey");
    private static final String parameterToAlterKey        = MagicModuleController.plugin.getConfig().getString("parameterToAlterKey");

    public static void addMagicBlockToMap(Location location)
    {
        addMagicBlockToMap(location, null);
    }

    public static void addMagicBlockToMap(Location location, int parameterValue)
    {
        ConfigurationSection config;
        config = new MemoryConfiguration();
        config.set("cast.spells", spellTemplateKey + " " + parameterToAlterKey + " " + parameterValue);
        addMagicBlockToMap(location, config);
    }

    public static void addMagicBlockToMap(Location location, ConfigurationSection config)
    {
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
