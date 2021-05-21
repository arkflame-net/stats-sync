package com.arkflame.statssync.listeners;

import com.arkflame.statssync.mongodb.MongoDBController;

import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {
    private MongoDBController mongoDBController;

    public PlayerDeathListener(final MongoDBController mongoDBController) {
        this.mongoDBController = mongoDBController;
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onPlayerDeath(final PlayerDeathEvent event) {
        final Player killed = event.getEntity();
        final Player killer = killed.getKiller();
        final int killedDeaths = killed.getStatistic(Statistic.DEATHS);
        final int killerKills = killed.getStatistic(Statistic.PLAYER_KILLS);

        mongoDBController.setStat(killed.getUniqueId(), Statistic.DEATHS, killedDeaths);
        mongoDBController.setStat(killer.getUniqueId(), Statistic.PLAYER_KILLS, killerKills);
    }
}
