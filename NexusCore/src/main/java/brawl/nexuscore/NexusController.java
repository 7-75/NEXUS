package brawl.nexuscore;

import org.bukkit.plugin.java.JavaPlugin;
import com.elmakers.mine.bukkit.api.magic.MagicAPI;

public class NexusController {

    public static MagicAPI      magicAPI;
    public static JavaPlugin    plugin;
    public static String        nexusWandTemplateKey;
    public static String        nexusMagicBlockTemplateKey;
    public static String        nexusPhysicalBlockTemplate;
    public static String        parameterToBeProportionalToPower;
    public static String        spellToCastFromTheNexus;
    public static Long          barrierRefreshRate;

    public NexusController(JavaPlugin plugin, MagicAPI magicAPI)
    {
        NexusController.plugin              = plugin;
        NexusController.magicAPI            = magicAPI;
        nexusWandTemplateKey                = plugin.getConfig().getString("nexusWandTemplateKey");
        nexusMagicBlockTemplateKey          = plugin.getConfig().getString("nexusMagicBlockTemplateKey");
        nexusPhysicalBlockTemplate          = plugin.getConfig().getString("nexusPhysicalBlockTemplate");
        parameterToBeProportionalToPower    = plugin.getConfig().getString("parameterToBeProportionalToPower");
        spellToCastFromTheNexus             = plugin.getConfig().getString("spellToCastFromTheNexus");
        barrierRefreshRate                  = plugin.getConfig().getLong("barrierRefreshRate");
    }
}
