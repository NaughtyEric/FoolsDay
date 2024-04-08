package net.trioxwater.foolsday;

import net.trioxwater.foolsday.Command.ReloadMsgCommand;
import net.trioxwater.foolsday.Listener.BlockDestroyListener;
import net.trioxwater.foolsday.Listener.PlayerTeleportListener;
import net.trioxwater.foolsday.Listener.SleepListener;
import net.trioxwater.foolsday.RandomEvents.EventCountdown;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class FoolsDay extends JavaPlugin {

    public Configuration config;
    private BlockDestroyListener blockDestroyListener;
    private PlayerTeleportListener playerTeleportListener;
    private SleepListener sleepListener;
    private EventCountdown eventCountdown;
    final private ReloadMsgCommand reloadMsgCommand = new ReloadMsgCommand();


    public FoolConfig foolConfig;

    private void reload() {
        saveDefaultConfig();
        reloadConfig();
        config = getConfig();
        foolConfig = new FoolConfig(config);
    }
    @Override
    public void onEnable() {
        // Plugin startup logic
        reload();

        blockDestroyListener = new BlockDestroyListener(foolConfig);
        playerTeleportListener = new PlayerTeleportListener(foolConfig);
        sleepListener = new SleepListener(foolConfig);

        eventCountdown = new EventCountdown(this);

        getCommand("foolsday_reload").setExecutor(reloadMsgCommand);

        if (foolConfig.isEnabled()) { // 只在启用时注册
            getServer().getPluginManager().registerEvents(blockDestroyListener, this);
            getServer().getPluginManager().registerEvents(playerTeleportListener, this);
            eventCountdown.runTaskTimer(this, 0, 20);
            // print config
            getLogger().info("Load complete! Fool level: " + config.getString("fool_level"));
        } else {
            getLogger().info("Fool's Day is not enabled. Skip.");
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


}
