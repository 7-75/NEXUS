package util;

import brawl.factionsnexus.FactionsNexusController;
import com.massivecraft.factions.Faction;
import main.java.tasks.FactionRefreshBarrierTask;

public class SchedulerOperations {

    public static FactionRefreshBarrierTask addScheduler (Faction faction)
    {

        FactionRefreshBarrierTask task = new FactionRefreshBarrierTask(faction.getId());
        FactionsNexusController.factionRefreshBarrierTimers.add(task);
        task.taskId = FactionsNexusController.bukkitScheduler.scheduleSyncRepeatingTask(

                FactionsNexusController.plugin,
                task,
                0,
                FactionsNexusController.plugin.getConfig().getLong("BarrierRefreshRate")

        );
        return task;
    }

    public static FactionRefreshBarrierTask getTaskByFactionId(String factionId)
    {
        return FactionsNexusController
                .factionRefreshBarrierTimers
                .stream()
                .filter(factionRefreshBarrierTask -> factionRefreshBarrierTask.factionId.equals(factionId))
                .findFirst()
                .orElse(null);
    }

}
