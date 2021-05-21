package com.arkflame.statssync;

import com.arkflame.statssync.listeners.initializers.ListenerInitializer;
import com.arkflame.statssync.mongodb.MongoDBController;

import org.bukkit.plugin.java.JavaPlugin;

public class StatsSync extends JavaPlugin {
    private static StatsSync instance;

    @Override
    public void onEnable() {
        StatsSync.instance = this;

        final MongoDBController mongoDBController = new MongoDBController("arkflame");

        ListenerInitializer.initialize(this, mongoDBController);
    }

    public static StatsSync getInstance() {
        return instance;
    }
}
