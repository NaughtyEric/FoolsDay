package net.trioxwater.foolsday.RandomEvents;

import net.trioxwater.foolsday.FoolsDay;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.entity.Rabbit;

import java.util.HashSet;
import java.util.Set;

public class BunnyBurst implements RandomEvent {

    private final FoolsDay plugin;

    public BunnyBurst(FoolsDay plugin) {
        this.plugin = plugin;
    }
    @Override
    public String getName() {
        return "兔子突至";
    }

    @Override
    public void trigger(World world, Player player) {
        HashSet<Player> players = new HashSet<>();
        HashSet<Player> players2 = new HashSet<>();
        int amount = world.getPlayers().size() * 2;
        double kp = plugin.foolConfig.getGenerateKillerBunnyProbability();
        if (amount > 10) amount = 10;
        for (int i = 0; i < amount; i++) {
            for (Player p : world.getPlayers()) {
                if (Math.random() < kp) {
                    Rabbit rabbit = (Rabbit) world.spawnEntity(p.getLocation(), org.bukkit.entity.EntityType.RABBIT);
                    rabbit.setRabbitType(Rabbit.Type.THE_KILLER_BUNNY);
                    players2.add(p);
                } else {
                    world.spawnEntity(p.getLocation(), org.bukkit.entity.EntityType.RABBIT);
                    players.add(p);
                }
            }
        }
        for (Player p : players2) {
            p.sendMessage("你发现周围突然多了几只兔子……但是其中有几只看起来很凶猛！");
            players.remove(p);
        }
        for (Player p : players) {
            p.sendMessage("你发现周围突然多了几只兔子！");
        }
    }
}
