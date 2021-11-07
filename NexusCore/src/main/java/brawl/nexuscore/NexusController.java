package brawl.nexuscore;

import org.bukkit.plugin.java.JavaPlugin;
import com.elmakers.mine.bukkit.api.magic.MagicAPI;

public class NexusController {

    public MagicAPI magicAPI;
    public String   nexusWandTemplateKey;
    public String   nexusMagicBlockTemplateKey;
    public String   nexusPhysicalBlockTemplate;
    public String   parameterToBeProportionalToPower;
    public String   spellToCastFromTheNexus;
    public Long     barrierRefreshRate;

    public NexusController(JavaPlugin plugin, MagicAPI magicAPI)
    {
        this.magicAPI                       = magicAPI;

        nexusWandTemplateKey                = plugin.getConfig().getString("nexusWandTemplateKey");
        nexusMagicBlockTemplateKey          = plugin.getConfig().getString("nexusMagicBlockTemplateKey");
        nexusPhysicalBlockTemplate          = plugin.getConfig().getString("nexusPhysicalBlockTemplate");
        parameterToBeProportionalToPower    = plugin.getConfig().getString("parameterToBeProportionalToPower");
        spellToCastFromTheNexus             = plugin.getConfig().getString("spellToCastFromTheNexus");
        barrierRefreshRate                  = plugin.getConfig().getLong("barrierRefreshRate");
    }

    public NexusController getInstance()
    {
        return this;
    }

}
