package montebello.buguei.application.controllers;

import montebello.buguei.core.entities.News;
import montebello.buguei.core.interfaces.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {
    private INewsService newsService;

    @Autowired
    public NewsController(INewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    @CrossOrigin(origins = "*") // alter to specific domain in production
    public List<News> getAllNews() {
        return newsService.getAllNews();
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "*") // alter to specific domain in production
    public News getNewsById(@PathVariable String id) {
        return newsService.getNewsById(id);
    }

    @PatchMapping
    public HttpStatusCode updateNews() {
        newsService.updateNews();
        return HttpStatusCode.valueOf(HttpStatus.OK.value());
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "news controller healthy";
    }
}
