package com.arkflame.statssync;

import com.arkflame.statssync.listeners.initializers.ListenerInitializer;

import org.bukkit.plugin.java.JavaPlugin;

public class StatsSync extends JavaPlugin {
    private static StatsSync instance;

    @Override
    public void onEnable() {
        StatsSync.instance = this;
        ListenerInitializer.initialize(this);
    }

    public static StatsSync getInstance() {
        return instance;
    }
}
