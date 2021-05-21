package com.arkflame.statssync.utils;

import org.bson.Document;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

public class DocumentUtil {
    public static void readStatistic(final Player player, final Statistic statistic, final Document document) {
        player.setStatistic(statistic, document.getInteger(statistic.toString(), player.getStatistic(statistic)));
    }

    public static void writeStatistic(final Player player, final Statistic statistic, final Document document) {
        document.put(statistic.toString(), player.getStatistic(statistic));
    }
}
