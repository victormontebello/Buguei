package montebello.buguei.core.Mappers;

import montebello.buguei.core.entities.Post;
import montebello.buguei.core.interfaces.IImuttableMapper;
import org.bson.Document;
import org.springframework.stereotype.Component;

@Component
public class PostMapper implements IImuttableMapper<Post> {
    public Post Map(Document doc) {
        var post = new Post();
        post.setId(doc.getObjectId("_id").toHexString());
        post.setTitle(doc.getString("title"));
        post.setContent(doc.getString("content"));
        post.setAuthor(doc.getString("author"));
        post.setCreatedAt(doc.getDate("createdAt"));
        post.setUpdatedAt(doc.getDate("updatedAt"));
        post.setTags(doc.getList("tags", String.class));
        return post;
    }
}
