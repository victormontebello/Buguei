package montebello.buguei.infrastructure;

import com.mongodb.client.*;
import montebello.buguei.core.entities.News;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigMongoClient {

    @Value("${MONGODB_BUGUEI_CONN}")
    private String connectionString;

    private MongoClient mongoClient;

    public void connect() {
        if (mongoClient == null) {
            mongoClient = MongoClients.create(connectionString);
        }
    }

    public MongoClient getMongoClient() {
        if (mongoClient == null) {
            connect();
        }
        return mongoClient;
    }

    public MongoCollection<Document> getCollection(String collectionName) {
        MongoDatabase database = getMongoClient().getDatabase("buguei");
        return database.getCollection(collectionName, Document.class);
    }
}
