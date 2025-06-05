package montebello.buguei.infrastructure.services;

import com.kwabenaberko.newsapilib.models.Article;
import montebello.buguei.core.Mapper;
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
    private final HttpHelper httpHelper;

    public NewsService(ConfigMongoClient mongoClient, HttpHelper httpHelper) {
        this.mongoClient = mongoClient;
        this.httpHelper = httpHelper;
    }

    public void createNews(News news) {
        var collection = mongoClient.getCollection("news");
        if (collection != null) {
            Document document = new Document("title", news.getTitle())
                    .append("content", news.getContent())
                    .append("author", news.getAuthor())
                    .append("sourceUrl", news.getUrl());
            collection.insertOne(document);
        }
    }

    public void updateNews() {
        var articles = httpHelper.GetArticles();
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

    public List<News> getAllNews() {
        var collection = mongoClient.getCollection("news");
        if (collection != null) {
            var documents = collection.find().into(new ArrayList<>());
            return documents.stream()
                    .map(Mapper::MapNews)
                    .toList();
        }
        return List.of();
    }

    private void saveArticlesToMongo(List<Article> articles) {
        var collection = mongoClient.getCollection("news");
        if (collection != null) {
            List<Document> documents = articles.stream()
                    .map(article -> new Document("title", article.getTitle())
                            .append("content", article.getContent())
                            .append("author", article.getAuthor())
                            .append("urlToImage", article.getUrlToImage())
                            .append("description", article.getDescription())
                            .append("publishedAt", article.getPublishedAt())
                            .append("url", article.getUrl()))
                    .toList();

            collection.insertMany(documents);
        }
    }

    public News getNewsById(String id) {
        var collection = mongoClient.getCollection("news");
        if (collection != null) {
            return Mapper.MapNews(collection.find(new Document("_id", new ObjectId(id))).first());
        }
        return null;
    }
}
