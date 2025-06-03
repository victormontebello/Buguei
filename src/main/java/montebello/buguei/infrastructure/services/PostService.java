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

    }

    @Override
    public void updatePost(Post post) {

    }

    @Override
    public void deletePost(ObjectId id) {

    }

    @Override
    public List<Document> getAllPosts() {
        return List.of();
    }

    @Override
    public Document getPostById(ObjectId id) {
        return null;
    }
}
