package montebello.buguei.infrastructure;

import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.Article;
import com.kwabenaberko.newsapilib.models.request.EverythingRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HttpHelper {

    @Value("${NEWS_API_KEY}")
    private String API_KEY;

    private List<Article> articleResponse;
    private final Object lock = new Object();

    private void RequestNewsApi(String query, String language, int pageSize) {
        NewsApiClient newsApiClient = new NewsApiClient(API_KEY);
        newsApiClient.getEverything(
                new EverythingRequest.Builder()
                        .q(query)
                        .sortBy("publishedAt")
                        .language(language)
                        .pageSize(pageSize)
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {
                        synchronized (lock) {
                            articleResponse = response.getArticles();
                            lock.notifyAll();
                        }
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        synchronized (lock) {
                            articleResponse = null;
                            lock.notifyAll();
                            throwable.printStackTrace();
                        }
                    }
                });
    }

    public List<Article> GetArticles() {
        synchronized (lock) {
            RequestNewsApi("(software OR development OR AI)", "pt", 10);
            while (articleResponse == null) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    System.err.println("Error while waiting for articles: " + e.getMessage());
                }
            }
        }
        return articleResponse;
    }
}
