package net.trioxwater.foolsday.RandomEvents;

import org.bukkit.World;
import org.bukkit.entity.Player;

public class StrikingSound implements RandomEvent{
    @Override
    public String getName() {
        return "暴雨时节";
    }

    @Override
    public void trigger(World world, Player player) {
        for (Player p : world.getPlayers()) {
            p.playSound(p.getLocation(), "ambient.weather.thunder", 1, 1);
        }
        if (player != null) {
            player.sendMessage("你听到一声惊雷……");
        }

        // random a player to be struck by lightning if 3+ players online
        if (world.getPlayers().size() >= 2) {
            int randomIndex = (int) (Math.random() * world.getPlayers().size());
            Player randomPlayer = (Player) world.getPlayers().toArray()[randomIndex];
            world.strikeLightning(randomPlayer.getLocation());
            randomPlayer.sendMessage("一道闪电劈中了你！");
        }
    }
}
