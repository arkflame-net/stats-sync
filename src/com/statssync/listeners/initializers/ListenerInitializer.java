package com.statssync.listeners.initializers;

import com.statssync.listeners.PlayerDeathListener;
import com.statssync.listeners.PlayerJoinListener;
import com.statssync.listeners.PlayerQuitListener;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class ListenerInitializer {
    public static void initialize(final Plugin plugin) {
        final PluginManager pluginManager = plugin.getServer().getPluginManager();

        pluginManager.registerEvents(new PlayerDeathListener(), plugin);
        pluginManager.registerEvents(new PlayerJoinListener(), plugin);
        pluginManager.registerEvents(new PlayerQuitListener(), plugin);
    }
}
