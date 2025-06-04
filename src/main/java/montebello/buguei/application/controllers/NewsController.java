package montebello.buguei.application.controllers;

import montebello.buguei.core.interfaces.INewsService;
import org.bson.Document;
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
    public List<Document> getAllNews() {
        return newsService.getAllNews();
    }

    @GetMapping("/{id}")
    public Document getNewsById(@RequestParam String id) {
        return newsService.getNewsById(id);
    }

    @PatchMapping
    public HttpStatusCode updateNews() {
        newsService.updateNews();
        return HttpStatusCode.valueOf(HttpStatus.OK.value());
    }
}
