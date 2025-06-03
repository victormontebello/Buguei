package montebello.buguei.infrastructure.services;

import montebello.buguei.core.entities.Post;
import montebello.buguei.core.interfaces.IPostService;
import montebello.buguei.infrastructure.ConfigMongoClient;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService implements IPostService {

    private final ConfigMongoClient mongoClient;

    @Autowired
    public PostService(ConfigMongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Override
    public void createPost(Post post) {
        var collection = mongoClient.getCollection("posts");
        if (collection != null) {
            Document document = new Document()
                    .append("title", post.getTitle())
                    .append("content", post.getContent())
                    .append("author", post.getAuthor())
                    .append("tags", post.getTags())
                    .append("createdAt", post.getCreatedAt());
            collection.insertOne(document);
        }
    }

    @Override
    public void updatePost(Post post) {

    }

    @Override
    public void deletePost(String id) {
        var collection = mongoClient.getCollection("posts");
        if (collection != null) {
            collection.deleteOne(new Document("_id", new ObjectId(id)));
        }
    }

    @Override
    public List<Document> getAllPosts() {
        var collection = mongoClient.getCollection("posts");
        if (collection != null) {
            return collection.find().into(new java.util.ArrayList<>());
        }
        return List.of();
    }

    @Override
    public Document getPostById(String id) {
        var collection = mongoClient.getCollection("posts");
        if (collection != null) {
            return collection.find(new Document("_id", new ObjectId(id))).first();
        }
        return null;
    }
}
