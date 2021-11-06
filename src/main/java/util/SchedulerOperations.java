package util;

import brawl.factionsnexus.NexusController;
import com.massivecraft.factions.Faction;
import tasks.FactionRefreshBarrierTask;

public class SchedulerOperations {

    public static FactionRefreshBarrierTask addScheduler (Faction faction)
    {

        FactionRefreshBarrierTask task = new FactionRefreshBarrierTask(faction.getId());
        NexusController.factionRefreshBarrierTimers.add(task);
        task.taskId = NexusController.bukkitScheduler.scheduleSyncRepeatingTask(

                NexusController.plugin,
                task,
                0,
                NexusController.plugin.getConfig().getLong("BarrierRefreshRate")

        );
        return task;
    }

    public static FactionRefreshBarrierTask getTaskByFactionId(String factionId)
    {
        return NexusController
                .factionRefreshBarrierTimers
                .stream()
                .filter(factionRefreshBarrierTask -> factionRefreshBarrierTask.factionId.equals(factionId))
                .findFirst()
                .orElse(null);
    }

}
