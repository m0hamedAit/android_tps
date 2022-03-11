package ma.enset.newsApp.service;

import java.util.List;

import ma.enset.newsApp.R;
import ma.enset.newsApp.model.Article;
import ma.enset.newsApp.model.ArticleListResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface RestApiService {
    @GET("/v2/everything")
    Call<ArticleListResponse> getArticlesbyKeyDate(@Query("q") String key, @Query("from") String date,@Query("apiKey") String apikey);
    @GET("/v2/top-headlines")
    Call<ArticleListResponse> getTopHeadlines(@Query("country") String key, @Query("apiKey") String apikey);
}
