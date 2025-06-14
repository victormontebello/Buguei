package montebello.buguei.application.controllers;

import montebello.buguei.core.entities.Post;
import montebello.buguei.core.interfaces.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostsController{

    private IPostService postService;

    @Autowired
    public PostsController(IPostService postService) {
        this.postService = postService;
    }

    @GetMapping
    @CrossOrigin(origins = "*") //alterar para dominio específico em produção
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "*")
    public Post getPostById(@PathVariable String id) {
        return postService.getPostById(id);
    }

    @PostMapping
    public HttpStatusCode createPost(@RequestBody Post post) {
        postService.createPost(post);
        return HttpStatus.CREATED;
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "posts controller healthy";
    }
}
