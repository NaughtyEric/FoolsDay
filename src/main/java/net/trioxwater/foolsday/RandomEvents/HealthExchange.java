package net.trioxwater.foolsday.RandomEvents;

import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class HealthExchange implements RandomEvent{
    @Override
    public String getName() {
        return "体力交换";
    }

    @Override
    public void trigger(World world, Player player) {
        if (player != null)
            if (world.getPlayers().size() <= 1) {
                player.sendMessage("无事发生……？");
                return;
            } else player.setHealth(0.5);
        if (world.getPlayers().size() <= 1) {
            return;
        }
        ArrayList<Double> Hp = new ArrayList<>();
        for (Player p : world.getPlayers()) {
            Hp.add(p.getHealth());
        }

        shuffle(Hp);


        for (int i = 0; i < Hp.size(); ++i) {
            world.getPlayers().get(i).setHealth(Hp.get(i));
            if (player != null) {
                player.sendMessage("你的生命值被交换了！");
            }
        }


    }

    private void shuffle(ArrayList<Double> Hp) {
        for(int i = Hp.size() - 1; i >= 0; --i) {
            int j = (int)(Math.random() * (i + 1));
            double temp = Hp.get(i);
            Hp.set(i, Hp.get(j));
            Hp.set(j, temp);
        }
    }
}
