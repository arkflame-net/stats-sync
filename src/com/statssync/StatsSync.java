package com.statssync;

import com.statssync.listeners.initializers.ListenerInitializer;

import org.bukkit.plugin.java.JavaPlugin;

public class StatsSync extends JavaPlugin {
    @Override
    public void onEnable() {
        ListenerInitializer.initialize(this);
    }
}
