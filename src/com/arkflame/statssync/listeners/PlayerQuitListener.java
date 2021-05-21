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
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {
    private MongoDBController mongoDBController;

    public PlayerQuitListener(final MongoDBController mongoDBController) {
        this.mongoDBController = mongoDBController;
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onPlayerQuit(final PlayerQuitEvent event) {
        final Player player = event.getPlayer();
        final UUID uuid = player.getUniqueId();
        final Document document = mongoDBController.getPlayer(uuid);

        if (document != null) {
            DocumentUtil.writeStatistic(player, Statistic.PLAYER_KILLS, document);
            DocumentUtil.writeStatistic(player, Statistic.PLAYER_KILLS, document);
        }

        mongoDBController.setPlayer("players", uuid, document);
    }
}