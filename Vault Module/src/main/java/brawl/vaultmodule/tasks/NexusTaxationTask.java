package brawl.vaultmodule.tasks;

import org.bukkit.Location;

public class NexusTaxationTask implements Runnable {

    public Location nexusBlockLocation;
    public int taskID;

    public NexusTaxationTask(Location nexusBlockLocation)
    {
        this.nexusBlockLocation = nexusBlockLocation;
    }

    @Override
    public void run() {

    }


}
