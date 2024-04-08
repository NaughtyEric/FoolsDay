package net.trioxwater.foolsday.RandomEvents;

import net.trioxwater.foolsday.FoolsDay;
import org.bukkit.World;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.logging.Logger;

public class EventCountdown extends BukkitRunnable {
    private final BossBar countdownBar;
    private final boolean activated;

    private final int maxCountdown;
    private int currentCountdown;
    private final Plugin plugin;
    private final RandomEvent[] events;

    private void refreshBar() {
        countdownBar.setProgress(1.0);
    }
    private void updateBar() {
        countdownBar.setProgress((double) currentCountdown / maxCountdown);
        countdownBar.setTitle("Event Countdown: " + currentCountdown);
//        countdownBar.setProgress(1);
        // display the boss bar
        if (activated) {
            for (Player player : plugin.getServer().getOnlinePlayers()) {
                if (!countdownBar.getPlayers().contains(player))
                    countdownBar.addPlayer(player);
            }
        }
    }


    public EventCountdown(FoolsDay plugin) {
        this.plugin = plugin;
        activated = plugin.foolConfig.isRandomEventEnabled();
        countdownBar = plugin.getServer().createBossBar("Event Countdown",
                BarColor.BLUE, BarStyle.SOLID);
        maxCountdown = plugin.foolConfig.getEventIntervalSec();
        currentCountdown = maxCountdown;
        refreshBar();

        if (activated) countdownBar.setVisible(true);

        events = new RandomEvent[] {
                new StrikingSound(),
                new GravityLost(),
                new BunnyBurst(plugin),
                new HealthExchange(),
                new FastMove()
        };
    }

    @Override
    public void run() {
        currentCountdown--;

        if (currentCountdown <= 0) {
            currentCountdown = maxCountdown;
            RandomEvent event = events[(int) (Math.random() * events.length)];
            Logger.getLogger("Fools Day").info("Random event " + event.getName() + " triggered.");
            event.trigger(plugin.getServer().getWorld("world"), null);
        }
        updateBar();
    }
}

interface RandomEvent {
    String getName();
    void trigger(World world, Player player);

}
