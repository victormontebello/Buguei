package montebello.buguei.infrastructure.services;

import com.kwabenaberko.newsapilib.models.Article;
import montebello.buguei.core.entities.News;
import montebello.buguei.core.interfaces.INewsService;
import montebello.buguei.infrastructure.ConfigMongoClient;
import montebello.buguei.infrastructure.HttpHelper;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
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

    public void updateNews() {
        var articles = HttpHelper.GetArticles();
        if (articles != null && !articles.isEmpty()) {
            saveArticlesToMongo(articles);
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
            return collection.find().into(new ArrayList<>());
        }
        return List.of();
    }

    private void saveArticlesToMongo(List<Article> articles) {
        var collection = mongoClient.getCollection("news");
        if (collection != null) {
            List<Document> documents = articles.stream()
                    .map(article -> new Document("title", article.getTitle())
                            .append("content", article.getDescription())
                            .append("author", article.getAuthor())
                            .append("imageUrl", article.getUrlToImage())
                            .append("sourceUrl", article.getUrl()))
                    .toList();

            collection.insertMany(documents);
        }
    }

    public Document getNewsById(String id) {
        var collection = mongoClient.getCollection("news");
        if (collection != null) {
            return collection.find(new Document("_id", new ObjectId(id))).first();
        }
        return null;
    }
}
