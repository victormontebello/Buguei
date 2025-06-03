package montebello.buguei.core.interfaces;

import montebello.buguei.core.entities.News;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.List;

public interface INewsService {
    void createNews(News news);

    void updateNews(News news);

    void deleteNews(ObjectId id);

    List<Document> getAllNews();

    Document getNewsById(ObjectId id);
}
