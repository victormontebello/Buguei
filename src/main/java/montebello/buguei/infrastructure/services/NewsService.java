package montebello.buguei.infrastructure.services;

import montebello.buguei.core.entities.News;
import montebello.buguei.core.interfaces.INewsService;
import montebello.buguei.infrastructure.ConfigMongoClient;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService implements INewsService {

    private final ConfigMongoClient mongoClient;

    public NewsService(ConfigMongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public void createNews(News news) {
        var collection = mongoClient.getCollection("news");
        if (collection != null) {
            Document document = new Document("title", news.getTitle())
                    .append("content", news.getContent())
                    .append("author", news.getAuthor())
                    .append("sourceUrl", news.getSourceUrl());
            collection.insertOne(document);
        }
    }

    public void updateNews(News news) {
        var collection = mongoClient.getCollection("news");
        if (collection != null) {
            Document document = new Document("_id", news.getId())
                    .append("title", news.getTitle())
                    .append("content", news.getContent())
                    .append("author", news.getAuthor())
                    .append("sourceUrl", news.getSourceUrl());
            collection.updateOne(
                    new Document("_id", news.getId()),
                    new Document("$set", document)
            );
        }
    }

    public void deleteNews(String id) {
        var collection = mongoClient.getCollection("news");
        if (collection != null) {
            collection.deleteOne(new Document("_id", new ObjectId(id)));
        }
    }

    public List<Document> getAllNews() {
        var collection = mongoClient.getCollection("news");
        if (collection != null) {
            return collection.find().into(new java.util.ArrayList<>());
        }
        return List.of();
    }

    public Document getNewsById(String id) {
        var collection = mongoClient.getCollection("news");
        if (collection != null) {
            return collection.find(new Document("_id", new ObjectId(id))).first();
        }
        return null;
    }
}
