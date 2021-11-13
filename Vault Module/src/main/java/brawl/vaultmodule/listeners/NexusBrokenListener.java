package brawl.vaultmodule.listeners;

import brawl.nexuscore.events.NexusBrokenEvent;
import brawl.vaultmodule.VaultModuleController;
import brawl.vaultmodule.tasks.NexusTaxationTask;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NexusBrokenListener implements Listener {
    @EventHandler
    public void broken (NexusBrokenEvent event)
    {

        NexusTaxationTask task = VaultModuleController.tasks
                .stream()
                .filter(nexusTaxationTask -> nexusTaxationTask.nexusBlockLocation == event.getLocation())
                .findFirst()
                .orElse(null);

        assert task != null;

        int taskId = task.taskID;
        VaultModuleController.scheduler.cancelTask(taskId);
    }
}
