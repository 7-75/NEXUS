package brawl.factionsnexus;

import com.elmakers.mine.bukkit.api.magic.MagicAPI;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.massivecraft.factions.Board;
import com.massivecraft.factions.Factions;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;

public class NexusController {

    public static JavaPlugin            plugin;
    public static MagicAPI              magicAPI;
    public static ObjectMapper          objectMapper;
    public static Factions              factions;
    public static Board                 board;
    public static File                  file;

    public NexusController(MagicAPI magicAPI, JavaPlugin plugin, Factions factions, Board board) {

        objectMapper                =   new ObjectMapper();
        file                        =   plugin.getDataFolder();

        NexusController.plugin      =   plugin;
        NexusController.magicAPI    =   magicAPI;
        NexusController.factions    =   factions;
        NexusController.board       =   board;

        saveDefaultMap();

    }

    private void saveDefaultMap() {


        try {
        if (!file.createNewFile())
            return;

        objectMapper.writeValue(file, new HashMap<String, Location>());

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
