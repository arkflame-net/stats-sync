package com.statssync.listeners;

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
