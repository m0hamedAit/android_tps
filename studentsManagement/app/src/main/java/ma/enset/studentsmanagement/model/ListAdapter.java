package ma.enset.studentsmanagement.model;

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

import java.util.List;

import ma.enset.studentsmanagement.R;

public class ListAdapter extends ArrayAdapter<Student> {
    private int resource;

    public ListAdapter(@NonNull Context context, int resource, @NonNull List<Student> student) {
        super(context, resource, student);
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listView = convertView;
        if(listView == null){
            listView = LayoutInflater.from(getContext()).inflate(resource, parent, false);
        }

        TextView nom = listView.findViewById(R.id.nom);
        TextView email = listView.findViewById(R.id.email);
        TextView telephone = listView.findViewById(R.id.telephone);

        ImageView image = listView.findViewById(R.id.image);

        nom.setText(getItem(position).getNom()+" "+getItem(position).getPrenom());
        telephone.setText(getItem(position).getTel());
        email.setText(getItem(position).getEmail());

        if(getItem(position).getSexe().equals("M")){
            Bitmap icon = BitmapFactory.decodeResource(getContext().getResources(),
                    R.drawable.man);
            image.setImageBitmap(icon);
        }

        else if(getItem(position).getSexe().equals("F")){
            Bitmap icon = BitmapFactory.decodeResource(getContext().getResources(),
                    R.drawable.woman);
            image.setImageBitmap(icon);
        }


        return listView;
    }
}
