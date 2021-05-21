package com.arkflame.statssync.mongodb;

import java.util.UUID;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.bukkit.Statistic;

// Cointains all the logic for the SQLConnection
public class MongoDBController extends MongoDBConnection {
    private final String databaseName;

    public MongoDBController(final String databaseName) {
        super(databaseName);
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

    public void setStat(final String collectionName, final UUID uuid, final Statistic statistic, final int value) {
        final Document document = find(collectionName, uuid);

        document.put(statistic.toString(), value);

        findOneAndUpdate(collectionName, uuid, document);
    }

    public int getStat(final String collectionName, final UUID uuid, final Statistic statistic) {
        final Document document = find(collectionName, uuid);

        return document.getInteger(statistic.toString());
    }
}
