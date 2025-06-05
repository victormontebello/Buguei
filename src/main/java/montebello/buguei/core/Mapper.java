package montebello.buguei.core;

import montebello.buguei.core.entities.News;
import montebello.buguei.core.entities.Post;
import org.bson.Document;

public class Mapper {
    public static News MapNews(Document doc) {
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

    public static Post MapPost(Document doc) {
        var post = new Post();
        post.setId(doc.getObjectId("_id").toHexString());
        post.setTitle(doc.getString("title"));
        post.setContent(doc.getString("content"));
        post.setAuthor(doc.getString("author"));
        post.setCreatedAt(doc.getString("createdAt"));
        post.setUpdatedAt(doc.getString("updatedAt"));
        post.setTags(doc.getList("tags", String.class));
        return post;
    }
}
