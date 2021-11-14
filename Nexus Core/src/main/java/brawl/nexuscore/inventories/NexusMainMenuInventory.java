package brawl.nexuscore.inventories;

import brawl.nexuscore.providers.NexusMainMenuProvider;
import fr.minuskube.inv.SmartInventory;

public class NexusMainMenuInventory {

    public static final SmartInventory MainMenu = SmartInventory
            .builder()
            .id("NexusUpgrade-MainMenu")
            .provider(new NexusMainMenuProvider())
            .size(4,9)
            .build();
}
