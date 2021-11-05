package tasks;

import brawl.factionsnexus.NexusController;
import org.bukkit.Location;
import util.NexusOperations;

public class FactionRefreshBarrierTask implements Runnable {

    public int      taskId;
    public String   factionId;

    public FactionRefreshBarrierTask(String factionId)
    {
        this.factionId = factionId;
    }


    @Override
    public void run() {

        Location    location    = NexusController.factionsAPI.getFactionById(factionId).getHome();

        NexusOperations.removeMagicBlockFromMap(location);

        //TODO give knockback to all players within the area backwards

        NexusOperations.addMagicBlockToMap(location);



    }
}
