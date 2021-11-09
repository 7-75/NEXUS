package brawl.magicmodule;

import com.elmakers.mine.bukkit.api.magic.MagicAPI;
import org.bukkit.plugin.java.JavaPlugin;

public class MagicModuleController {

    public static MagicAPI      magicAPI;
    public static JavaPlugin    plugin;
    public static String        spellToCastFromTheNexus;
    public static String        nexusMagicBlockTemplateKey;
    public MagicModuleController(MagicModule magicModule, MagicAPI magicAPI) {
     MagicModuleController.plugin           = magicModule;
     MagicModuleController.magicAPI         = magicAPI;
     spellToCastFromTheNexus                = plugin.getConfig().getString("spellToCastFromTheNexus");
     nexusMagicBlockTemplateKey             = plugin.getConfig().getString("nexusMagicBlockTemplateKey");
    }
}
