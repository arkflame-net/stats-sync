package com.arkflame.statssync.listeners.initializers;

import com.arkflame.statssync.listeners.PlayerDeathListener;
import com.arkflame.statssync.listeners.PlayerJoinListener;
import com.arkflame.statssync.listeners.PlayerQuitListener;
import com.arkflame.statssync.mongodb.MongoDBController;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class ListenerInitializer {
    public static void initialize(final Plugin plugin, final MongoDBController mongoDBController) {
        final PluginManager pluginManager = plugin.getServer().getPluginManager();

        pluginManager.registerEvents(new PlayerDeathListener(mongoDBController), plugin);
        pluginManager.registerEvents(new PlayerJoinListener(mongoDBController), plugin);
        pluginManager.registerEvents(new PlayerQuitListener(mongoDBController), plugin);
    }
}
