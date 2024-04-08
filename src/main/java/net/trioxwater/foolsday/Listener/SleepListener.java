package net.trioxwater.foolsday.Listener;

import net.trioxwater.foolsday.FoolConfig;
import net.trioxwater.foolsday.FoolsDay;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.plugin.Plugin;

public class SleepListener implements Listener {

    private final FoolConfig config;

    private final String[] categories = {
            "你这个年纪怎么睡得着觉的？",
            "你躺上床……然后大脑开始自动为你播放 §o Invisible Frenzy §r。",
            "床突然晃了一下，它很抗拒你的接近。",
            "你躺上床，然后突然想起了你的作业。"
    };

    public SleepListener(FoolConfig pluginConfig) {
        this.config = pluginConfig;
    }

    @EventHandler
    public void onSleep(PlayerBedEnterEvent event) {
        if (event.getBedEnterResult() == PlayerBedEnterEvent.BedEnterResult.OK) {
            double p = Math.random();
            if (p < config.getSleepInterruptionProbability()) {
                event.setCancelled(true);

                event.getPlayer().sendMessage(categories[(int) (Math.random() * categories.length)]);
            }
        }
    }
}
