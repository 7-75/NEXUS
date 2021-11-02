package brawl.factionsnexus;

import com.elmakers.mine.bukkit.api.magic.MagicAPI;
import org.bukkit.plugin.java.JavaPlugin;
public class NexusController {

    public JavaPlugin   plugin;
    public MagicAPI     magicAPI;



    public NexusController(MagicAPI magicAPI, JavaPlugin plugin)
    {

        this.plugin =   plugin;
        this.magicAPI =   magicAPI;

    }
}
