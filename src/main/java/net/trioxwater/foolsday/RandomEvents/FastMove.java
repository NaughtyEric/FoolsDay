package net.trioxwater.foolsday.RandomEvents;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FastMove implements RandomEvent {
    @Override
    public String getName() {
        return "fast move";
    }

    @Override
    public void trigger(World world, Player player) {
        for (Mob mob : world.getEntitiesByClass(Mob.class)) {
            mob.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 10, false, false));
        }

        for (Player p : world.getPlayers()) {
            p.sendMessage("所有生物开始飞快移动！");
            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 600, 2, false, false));
        }
    }
}
