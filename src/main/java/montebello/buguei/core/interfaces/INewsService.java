package montebello.buguei.core.interfaces;

import montebello.buguei.core.entities.News;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.List;

public interface INewsService {
    void updateNews();

    void deleteNews(String id);

    List<News> getAllNews();

    News getNewsById(String id);
}
