package montebello.buguei.core.interfaces;

import montebello.buguei.core.entities.Post;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.List;

public interface IPostService {
    void createPost(Post post);

    void updatePost(Post post);

    void deletePost(String id);

    List<Post> getAllPosts();

    Post getPostById(String id);
}
