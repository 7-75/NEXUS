package brawl.vaultmodule.listeners;

import brawl.vaultmodule.tasks.NexusTaxationTask;
import brawl.nexuscore.events.NexusCreatedEvent;
import brawl.vaultmodule.VaultModuleController;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NexusCreatedListener implements Listener {

    long runTaxationEveryXTicks;

    public NexusCreatedListener()
    {
        runTaxationEveryXTicks = VaultModuleController.plugin.getConfig().getLong("runTaxationEveryXTicks");
    }

    @EventHandler
    public void created(NexusCreatedEvent event)
    {

        NexusTaxationTask nexusTaxationTask = new NexusTaxationTask(event.getLocation());
        nexusTaxationTask.taskID =
                VaultModuleController
                        .scheduler
                        .scheduleSyncRepeatingTask
                                (VaultModuleController.plugin,
                                        nexusTaxationTask,
                                        0,
                                        runTaxationEveryXTicks );
    }
}
