package ma.enset.newsApp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ma.enset.newsApp.R;
import ma.enset.newsApp.model.Article;
import ma.enset.newsApp.model.ArticleCardAdapter;
import ma.enset.newsApp.model.ArticleListResponse;
import ma.enset.newsApp.service.RestApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    String url = "https://newsapi.org";
    String regex = "^[0-9]{4}-(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9])$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);
        EditText keyfield = findViewById(R.id.keyField);
        EditText datefield = findViewById(R.id.dateField);
        Button button = findViewById(R.id.submitButton);

        List<Article> articles = new ArrayList<>();
        ArticleCardAdapter adapter = new ArticleCardAdapter(this, R.layout.newscard, articles);
        listView.setAdapter(adapter);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create()).build();
        RestApiService restApiService = retrofit.create(RestApiService.class);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getApplicationContext(),ArticleActivity.class);
                intent.putExtra("article",articles.get(i));
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                articles.clear();
                String key = keyfield.getText().toString();
                String date = datefield.getText().toString();
                if (key.length() > 0 & date.length() > 0 & date.matches(regex)) {
                    Call<ArticleListResponse> callArticles = restApiService.getArticlesbyKeyDate(key, date, getString(R.string.ApiKey));
                    callArticles.enqueue(new Callback<ArticleListResponse>() {
                        @Override
                        public void onResponse(Call<ArticleListResponse> call, Response<ArticleListResponse> response) {
                            ArticleListResponse listArticles = response.body();
                            for (Article article : listArticles.getArticles()) {
                                articles.add(article);
                            }
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onFailure(Call<ArticleListResponse> call, Throwable t) {
                            Log.e("Failure", "Erreur");
                        }
                    });
                }
            }
        });





    }
}