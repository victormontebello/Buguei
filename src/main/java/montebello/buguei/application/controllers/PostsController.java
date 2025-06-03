package montebello.buguei.application.controllers;

import montebello.buguei.core.entities.Post;
import montebello.buguei.core.interfaces.IPostService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostsController {

    private IPostService postService;

    @Autowired
    public PostsController(IPostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Document> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public Document getPostById(String id) {
        return postService.getPostById(id);
    }

    @PostMapping
    public HttpStatusCode createPost(Post post) {
        postService.createPost(post);
        return HttpStatus.CREATED;
    }
}
