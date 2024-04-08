package net.trioxwater.foolsday.Listener;

import net.trioxwater.foolsday.FoolConfig;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class PlayerTeleportListener implements Listener {

    private final FoolConfig foolConfig;

    public PlayerTeleportListener(FoolConfig foolConfig) {
        this.foolConfig = foolConfig;
    }
    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        // CHORUS_FRUIT ENDER_PEARL PLUGIN
        if (event.getCause() == PlayerTeleportEvent.TeleportCause.CHORUS_FRUIT ||
                event.getCause() == PlayerTeleportEvent.TeleportCause.ENDER_PEARL ||
                event.getCause() == PlayerTeleportEvent.TeleportCause.PLUGIN) {
            double offset = foolConfig.getTeleportOffset();
            // do 10 tries
            for (int i = 0; i < 10; i++) {
                Location location = event.getTo().clone();
                double azimuth = Math.random() * Math.PI * 2;
                double elevation = Math.random() * Math.PI / 2;
                location.add(
                        Math.cos(azimuth) * Math.cos(elevation) * offset,
                        Math.sin(elevation) * offset,
                        Math.sin(azimuth) * Math.cos(elevation) * offset
                );
                boolean safeCheck = location.getBlock().isEmpty();
                // safety check
                for(int j = 0; j < 10 && safeCheck; j++) {
                    Location tmp = location.clone();
                    tmp.add(0, -j, 0);
                    if (!location.getBlock().isPassable()) {
                        event.setTo(location);
                        return;
                    }
                }
            }
        }
    }
}
