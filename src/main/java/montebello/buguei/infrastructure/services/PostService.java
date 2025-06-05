package montebello.buguei.infrastructure.services;

import montebello.buguei.core.Mapper;
import montebello.buguei.core.entities.Post;
import montebello.buguei.core.interfaces.IPostService;
import montebello.buguei.infrastructure.ConfigMongoClient;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService implements IPostService {

    private final ConfigMongoClient mongoClient;

    @Autowired
    public PostService(ConfigMongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public void createPost(Post post) {
        var collection = mongoClient.getCollection("posts");
        if (collection != null) {
            Document document = new Document("title", post.getTitle())
                    .append("content", post.getContent())
                    .append("author", post.getAuthor())
                    .append("tags", post.getTags())
                    .append("createdAt", post.getCreatedAt());
            collection.insertOne(document);
        }
    }

    public void updatePost(Post post) {
        var collection = mongoClient.getCollection("posts");
        if (collection != null) {
            Document document = new Document("title", post.getTitle())
                    .append("content", post.getContent())
                    .append("author", post.getAuthor())
                    .append("tags", post.getTags())
                    .append("updatedAt", post.getUpdatedAt());
            collection.updateOne(
                    new Document("_id", new ObjectId(post.getId())),
                    new Document("$set", document)
            );
        }
    }

    public void deletePost(String id) {
        var collection = mongoClient.getCollection("posts");
        if (collection != null) {
            collection.deleteOne(new Document("_id", new ObjectId(id)));
        }
    }

    public List<Post> getAllPosts() {
        var collection = mongoClient.getCollection("posts");
        if (collection != null) {
            var documents = collection.find().into(new ArrayList<>());
            return documents.stream()
                    .map(Mapper::MapPost)
                    .toList();
        }
        return List.of();
    }

    public Post getPostById(String id) {
        var collection = mongoClient.getCollection("posts");
        if (collection != null) {
            return Mapper.MapPost(collection.find(new Document("_id", new ObjectId(id))).first());
        }
        return null;
    }
}
