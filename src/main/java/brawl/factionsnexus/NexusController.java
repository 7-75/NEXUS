package brawl.factionsnexus;

import com.elmakers.mine.bukkit.api.magic.MagicAPI;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.massivecraft.factions.Factions;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;

public class NexusController {

    public static JavaPlugin            plugin;
    public static MagicAPI              magicAPI;
    public static HashMap               nexuses;
    public static ObjectMapper          objectMapper;
    public static String                path;
    public static Factions              factions;

    public NexusController(MagicAPI magicAPI, JavaPlugin plugin, Factions factions)
    {

        path                        =   "plugins/FactionsNexus/nexuses.json";
        objectMapper                =   new ObjectMapper();
        NexusController.plugin      =   plugin;
        NexusController.magicAPI    =   magicAPI;
        NexusController.factions    =   factions;
    }

    public static void readNexuses()
    {
        try {
            nexuses = objectMapper.readValue(Paths.get(path).toFile(), new TypeReference<HashMap>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeNexuses()
    {
        System.out.println(nexuses);

        try {
            objectMapper.writeValue(Paths.get(path).toFile(), nexuses);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
