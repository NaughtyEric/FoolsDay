package net.trioxwater.foolsday.RandomEvents;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PhantomSurge implements RandomEvent {
    @Override
    public String getName() {
        return "幻影潮汐";
    }

    private Location getSafeGenerateLocation(Location initPos) {
        if (initPos == null) {
            return null;
        }
        Location pos = initPos.clone();
        pos.setY(pos.y() + 3);
        // 向上搜索5x10x5的区域，找不到安全生成点则返回null
        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 10; ++j) {
                for (int k = 0; k < 5; ++k) {
                    if (pos.getBlock().isEmpty()) {
                        return pos;
                    }
                    pos.setY(pos.y() + 1);
                }
                pos.setX(pos.x() + 1);
            }
            pos.setZ(pos.z() + 1);
        }
        return null;
    }

    @Override
    public void trigger(World world, Player player) {
        for (Player p : world.getPlayers()) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 1));
            if (player == null || p != player)
                p.sendMessage("天空中突然出现了一群幻翼！");
            else
                p.sendMessage("你感觉到一阵幻翼的风！");
            Location pos = getSafeGenerateLocation(p.getLocation());
            if (pos != null) {
                world.spawnEntity(pos, EntityType.PHANTOM);
            }
        }
    }
}
