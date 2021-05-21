package com.arkflame.statssync.listeners;

import java.util.UUID;

import com.arkflame.statssync.mongodb.MongoDBController;
import com.arkflame.statssync.utils.DocumentUtil;

import org.bson.Document;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    private MongoDBController mongoDBController;

    public PlayerJoinListener(final MongoDBController mongoDBController) {
        this.mongoDBController = mongoDBController;
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onPlayerJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        final UUID uuid = player.getUniqueId();
        final Document document = mongoDBController.getPlayer(uuid);

        if (document != null) {
            DocumentUtil.readStatistic(player, Statistic.PLAYER_KILLS, document);
            DocumentUtil.readStatistic(player, Statistic.DEATHS, document);
        }
    }
}
