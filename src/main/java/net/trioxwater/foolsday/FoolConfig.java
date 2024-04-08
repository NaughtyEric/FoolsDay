package net.trioxwater.foolsday;

import org.bukkit.configuration.Configuration;

public class FoolConfig {
    final private Configuration config;
    private int level;
    private int eventIntervalSec;
    public FoolConfig(Configuration config) {
        this.config = config;
        reload();
    }

    public void reload() {
        String s = config.getString("fool_level");
        if (s == null) {
            s = "normal";
        }
        switch (s) {
            case "normal":
                level = 1;
                break;
            case "hard":
                level = 2;
                break;
            case "insane":
                level = 3;
                break;
            default:
                level = 0;
                break;
        }
        eventIntervalSec = config.getInt("event_interval");
    }

    boolean isEnabled() {
        return level != 0;
    }

    public double getExplosionProbability() {
        if (level == 1) {
            return 0.02;
        } else if (level == 2) {
            return 0.05;
        } else if (level == 3) {
            return 0.1;
        } else {
            return 0;
        }
    }

    public double getTeleportOffset() {
        if (level == 1) {
            return 4 * Math.random();
        } else if (level == 2) {
            return 7 * Math.random();
        } else if (level == 3) {
            return 10 * Math.random();
        } else {
            return 0;
        }
    }

    public boolean isRandomEventEnabled() {
        return eventIntervalSec > 0;
    }

    public int getEventIntervalSec() {
        return eventIntervalSec;
    }

    public double getConsumptionEffectProbability() {
        if (level == 1) {
            return 0.1;
        } else if (level == 2) {
            return 0.25;
        } else if (level == 3) {
            return 0.5;
        } else {
            return 0;
        }
    }

    public double getGenerateKillerBunnyProbability() {
        if (level == 1) {
            return 0.01;
        } else if (level == 2) {
            return 0.02;
        } else if (level == 3) {
            return 0.03;
        } else {
            return 0;
        }
    }

    public double getSleepInterruptionProbability() {
        if (level == 1) {
            return 0.1;
        } else if (level == 2) {
            return 0.3;
        } else if (level == 3) {
            return 0.5;
        } else {
            return 0;
        }
    }
}
