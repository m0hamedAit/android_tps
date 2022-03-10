package ma.enset.newsApp.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.net.URL;
import java.util.List;

import ma.enset.newsApp.R;

public class ArticleCardAdapter extends ArrayAdapter<Article> {
    private int resource;

    public ArticleCardAdapter(@NonNull Context context, int resource, @NonNull List<Article> articles) {
        super(context, resource, articles);
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listView = convertView;
        if(listView == null){
            listView = LayoutInflater.from(getContext()).inflate(resource, parent, false);
        }

        TextView titleField = listView.findViewById(R.id.titleField);
        TextView descriptionField = listView.findViewById(R.id.descriptionField);
        ImageView image = listView.findViewById(R.id.imageView);

        titleField.setText(getItem(position).getTitle());
        descriptionField.setText(getItem(position).getDescription());
        Runnable thread = new Runnable(){
            @Override
            public void run() {
                try{
                    URL url = new URL(getItem(position).getUrlToImage());
                    Bitmap bitmap = BitmapFactory.decodeStream(url.openStream());
                    image.setImageBitmap(bitmap);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        };

        Thread t = new Thread(thread);
        t.start();

        return listView;
    }
}
