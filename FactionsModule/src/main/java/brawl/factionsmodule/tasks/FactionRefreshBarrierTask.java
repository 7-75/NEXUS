package main.java.tasks;

import brawl.factionsmodule.FactionsNexusController;
import org.bukkit.Location;
import brawl.factionsmodule.util.FactionOperations;

public class FactionRefreshBarrierTask implements Runnable {

    public int      taskId;
    public String   factionId;

    public FactionRefreshBarrierTask(String factionId)
    {
        this.factionId = factionId;
    }


    @Override
    public void run() {

        Location    location    = FactionsNexusController.factionsAPI.getFactionById(factionId).getHome();

        FactionOperations.removeMagicBlockFromMap(location);

        //TODO give knockback to all players within the area backwards

        FactionOperations.addMagicBlockToMap(location);



    }
}
