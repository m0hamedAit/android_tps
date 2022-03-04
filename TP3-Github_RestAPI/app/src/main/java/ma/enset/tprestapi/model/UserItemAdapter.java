package ma.enset.tprestapi.model;

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

import ma.enset.tprestapi.R;

public class UserItemAdapter extends ArrayAdapter<UserGithub> {
    private int resource;

    public UserItemAdapter(@NonNull Context context, int resource, @NonNull List<UserGithub> users){
        super(context, resource, users);
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listView = convertView;
        if(listView == null){
            listView = LayoutInflater.from(getContext()).inflate(resource, parent, false);
        }
        TextView textLogin = listView.findViewById(R.id.textViewLogin);
        TextView textScore = listView.findViewById(R.id.textViewName);
        ImageView imageView = listView.findViewById(R.id.imageView);

        textLogin.setText(getItem(position).getLogin());
        textScore.setText(getItem(position).getType());
        Runnable thread = new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(getItem(position).getAvatarUrl());
                    Bitmap bitmap = BitmapFactory.decodeStream(url.openStream());
                    imageView.setImageBitmap(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Thread t=new Thread(thread);
        t.start();


        return listView;
    }

}
