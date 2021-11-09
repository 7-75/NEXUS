package brawl.nexuscore.util;

import brawl.nexuscore.NexusController;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class IOOperations {
    static ObjectMapper objectMapper = new ObjectMapper();

    public static void writeNexusesToFile() throws IOException {
        objectMapper.writeValue(new File(NexusController.plugin.getDataFolder().getPath().toString() + "/Nexuses.json"), NexusController.nexusBlocks);
    }

    public static void readNexusesFromFile() throws IOException {
        NexusController.nexusBlocks.addAll(objectMapper.readValue(new File(NexusController.plugin.getDataFolder().getPath().toString() + "/Nexuses.json"), ArrayList.class));
    }
}
