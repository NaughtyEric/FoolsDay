package net.trioxwater.foolsday.RandomEvents;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Shinny implements RandomEvent {
    @Override
    public String getName() {
        return "撒币活动";
    }

    @Override
    public void trigger(World world, Player player) {
        for (Player p : world.getPlayers()) {
            double prob = 1;
            while (Math.random() < prob) {
                // 生成金锭
                world.dropItem(p.getLocation(), new ItemStack(org.bukkit.Material.GOLD_INGOT));
                prob *= 0.5;
            }
            p.sendMessage("你发现周围突然多了几个金锭……捡起来吧？");
        }
    }
}
