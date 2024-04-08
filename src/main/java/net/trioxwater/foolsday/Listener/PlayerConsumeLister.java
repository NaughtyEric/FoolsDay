package net.trioxwater.foolsday.Listener;

import com.destroystokyo.paper.event.block.BlockDestroyEvent;
import net.trioxwater.foolsday.FoolConfig;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class PlayerConsumeLister implements Listener {
    private final FoolConfig foolConfig;

    public PlayerConsumeLister(FoolConfig foolConfig) {
        this.foolConfig = foolConfig;
    }

    @EventHandler
    public void onPlayerConsume(PlayerItemConsumeEvent event) {
        if (foolConfig.getConsumptionEffectProbability() > Math.random()) {
            applyRandomEffect(event.getPlayer());
        }
    }

    private void applyRandomEffect(Player player) {
        double random = Math.random();
        if (random < 0.2) {
            player.addPotionEffect(new org.bukkit.potion.PotionEffect(org.bukkit.potion.PotionEffectType.WEAKNESS, 200, 1));
        } else if (random < 0.4) {
            player.addPotionEffect(new org.bukkit.potion.PotionEffect(org.bukkit.potion.PotionEffectType.SLOW, 400, 1));
        } else if (random < 0.5) {
            player.addPotionEffect(new org.bukkit.potion.PotionEffect(org.bukkit.potion.PotionEffectType.WITHER, 200, 1));
        } else if (random < 0.6) {
            player.addPotionEffect(new org.bukkit.potion.PotionEffect(org.bukkit.potion.PotionEffectType.POISON, 100, 2));
        } else if (random < 0.63) {
            player.addPotionEffect(new org.bukkit.potion.PotionEffect(org.bukkit.potion.PotionEffectType.LUCK, 24000, 1));
        } else if (random < 0.83) {
            player.addPotionEffect(new org.bukkit.potion.PotionEffect(org.bukkit.potion.PotionEffectType.HUNGER, 400, 3));
        } else if (random < 0.93) {
            player.addPotionEffect(new org.bukkit.potion.PotionEffect(org.bukkit.potion.PotionEffectType.BLINDNESS, 200, 1));
        } else if (random < 0.98) {
            player.addPotionEffect(new org.bukkit.potion.PotionEffect(org.bukkit.potion.PotionEffectType.SATURATION, 200, 1));
        } else {
            player.addPotionEffect(new org.bukkit.potion.PotionEffect(org.bukkit.potion.PotionEffectType.HEAL, 1, 1));
        }
    }
}
