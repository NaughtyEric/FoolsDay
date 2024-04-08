package net.trioxwater.foolsday.RandomEvents;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GravityLost implements RandomEvent {
    public String getName() {
        return "骤然失重";
    }

    @Override
    public void trigger(World world, Player player) {
        if (player != null) {
            player.sendMessage("你感觉到一阵失重，所有人开始飘了起来！");
        }

        for(Player p : world.getPlayers()) {
            if (p != player) {
                p.sendMessage("你突然飞了起来！");
            }
            var velocity = p.getVelocity();
            velocity.setY(velocity.getY() + 5);
            p.setVelocity(velocity);
        }
    }


}
