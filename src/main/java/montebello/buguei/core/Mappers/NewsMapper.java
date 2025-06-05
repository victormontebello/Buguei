package montebello.buguei.core.Mappers;

import montebello.buguei.core.entities.News;
import montebello.buguei.core.interfaces.IImuttableMapper;
import org.bson.Document;
import org.springframework.stereotype.Component;

@Component
public class NewsMapper implements IImuttableMapper<News> {
    public News Map(Document doc) {
        News news = new News();
        news.setId(doc.getObjectId("_id").toHexString());
        news.setTitle(doc.getString("title"));
        news.setContent(doc.getString("content"));
        news.setUrl(doc.getString("url"));
        news.setDescription(doc.getString("description"));
        news.setUrlToImage(doc.getString("urlToImage"));
        news.setPublishedAt(doc.getString("publishedAt"));
        news.setAuthor(doc.getString("author"));
        return news;
    }
}
