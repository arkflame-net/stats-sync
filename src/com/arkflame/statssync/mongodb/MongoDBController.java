package com.arkflame.statssync.mongodb;

import java.util.UUID;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;

// Cointains all the logic for the SQLConnection
public class MongoDBController extends MongoDBConnection {
    private final String statsTable;
    private final String databaseName;

    public MongoDBController(final String databaseName) {
        super(databaseName);
        this.statsTable = "players_" + Bukkit.getServerName();
        this.databaseName = databaseName;
    }

    Document findOneAndUpdate(final String collectionName, final UUID uuid, final Document replacement) {
        final MongoDatabase database = client.getDatabase(databaseName);
        final MongoCollection<Document> collection = database.getCollection(collectionName);
        final Document filter = new Document("uuid", uuid.toString());
        final Document document = collection.findOneAndUpdate(filter, replacement);

        return document;
    }

    Document find(final String collectionName, final UUID uuid) {
        final MongoDatabase database = client.getDatabase(databaseName);
        final MongoCollection<Document> collection = database.getCollection(collectionName);
        final Document filter = new Document("uuid", uuid.toString());
        final Document document = collection.find(filter).first();

        if (document != null) {
            return document;
        } else {
            return new Document("uuid", uuid.toString());
        }
    }

    public void setStat(final UUID uuid, final Statistic statistic, final int value) {
        final Document document = find(statsTable, uuid);

        document.put(statistic.toString(), value);

        findOneAndUpdate(statsTable, uuid, document);
    }

    public int getStat(final UUID uuid, final Statistic statistic) {
        final Document document = find(statsTable, uuid);

        return document.getInteger(statistic.toString());
    }

    public Document getPlayer(final UUID uuid) {
        return find(statsTable, uuid);
    }

    public void setPlayer(String string, UUID uuid, Document document) {
        findOneAndUpdate(statsTable, uuid, document);
    }
}
