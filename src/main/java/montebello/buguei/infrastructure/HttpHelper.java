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
    private static String API_KEY;

    private static List<Article> articleResponse;
    private static void RequestNewsApi(String query, String language, int pageSize) {
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
                        articleResponse = response.getArticles();
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        articleResponse = null;
                    }
                });
    }

    public static List<Article> GetArticles() {
        RequestNewsApi("(software OR development OR AI)", "pt", 10);
        return articleResponse;
    }
}
