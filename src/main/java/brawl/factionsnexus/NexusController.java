package brawl.factionsnexus;

import com.elmakers.mine.bukkit.api.magic.MagicAPI;
import com.massivecraft.factions.Board;
import com.massivecraft.factions.Factions;
import org.bukkit.plugin.java.JavaPlugin;

public class NexusController {

    public static JavaPlugin            plugin;
    public static MagicAPI              magicAPI;
    public static Factions              factions;
    public static Board                 board;

    public NexusController(MagicAPI magicAPI, JavaPlugin plugin, Factions factions, Board board) {
        NexusController.plugin      =   plugin;
        NexusController.magicAPI    =   magicAPI;
        NexusController.factions    =   factions;
        NexusController.board       =   board;
    }

}
