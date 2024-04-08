package net.trioxwater.foolsday.Listener;

import net.trioxwater.foolsday.FoolConfig;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;


public class BlockDestroyListener implements Listener {

    private final FoolConfig foolConfig;

    public BlockDestroyListener(FoolConfig foolConfig) {
        this.foolConfig = foolConfig;
    }

    @EventHandler
    public void onBlockDestroy(BlockBreakEvent event) {

        Location location = event.getBlock().getLocation();
        if (Math.random() < foolConfig.getExplosionProbability()) {
            location.getWorld().createExplosion(location, 2f, false, false);
        }
    }

}
