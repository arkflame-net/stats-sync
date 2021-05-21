package com.arkflame.statssync.mongodb;

import java.util.logging.Logger;

import com.arkflame.statssync.StatsSync;
import com.mongodb.client.MongoClients;

class MongoDBConnectionTask implements Runnable {
    private MongoDBConnection mongoDBConnection;

    MongoDBConnectionTask(final MongoDBConnection mongoDBConnection) {
        this.mongoDBConnection = mongoDBConnection;
    }

    @Override
    public void run() {
        final Logger logger = StatsSync.getInstance().getLogger();

        while (!mongoDBConnection.closed) {
            logger.info("Established MongoDB connection to " + MongoDBConnection.URI);

            mongoDBConnection.setConnection(MongoClients.create(MongoDBConnection.URI));

            logger.info("Closed MongoDB connection to " + MongoDBConnection.URI);
        }
    }
}
