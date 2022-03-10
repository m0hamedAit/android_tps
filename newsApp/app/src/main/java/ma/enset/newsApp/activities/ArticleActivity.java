package ma.enset.newsApp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URL;

import ma.enset.newsApp.R;
import ma.enset.newsApp.model.Article;

public class ArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        Intent intent=getIntent();
        Article article = (Article)intent.getSerializableExtra("article");

        ImageView image = findViewById(R.id.imageV);
        TextView source = findViewById(R.id.source);
        TextView title = findViewById(R.id.titleV);
        TextView authorAndDate = findViewById(R.id.authorEtDate);
        TextView content = findViewById(R.id.content);
        Button previousButton = findViewById(R.id.previous);
        Button moreButton = findViewById(R.id.moreButton);



        runOnUiThread(new Runnable() {

            @Override
            public void run() {

                Runnable thread= new Runnable(){
                    @Override
                    public void run() {
                        try {
                            URL url=new URL(article.getUrlToImage());
                            Bitmap bitmap= BitmapFactory.decodeStream(url.openStream());
                            image.setImageBitmap(bitmap);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };

                Thread t=new Thread(thread);
                t.start();

                source.setText(article.getSource().getName());
                title.setText(article.getTitle());
                authorAndDate.setText("By "+article.getAuthor() +"\n"+article.getPublishedAt());
                content.setText(article.getContent().split("\\[")[0]);

                moreButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(article.getUrl()));
                        startActivity(browserIntent);
                    }
                });

                previousButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                });

            }

        });









    }
}