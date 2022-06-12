package ma.enset.quixam;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.List;

import ma.enset.quixam.model.Answers;
import ma.enset.quixam.model.Question;
import ma.enset.quixam.model.Quiz;
import ma.enset.quixam.services.QuestionAPICall;
import ma.enset.quixam.services.QuizAPICall;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ResponsesListAdapter extends ArrayAdapter<Answers> {
    private int resource;

    public ResponsesListAdapter(@NonNull Context context, int resource, @NonNull List<Answers> answers) {
        super(context, resource, answers);
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listView = convertView;
        if(listView == null){
            listView = LayoutInflater.from(getContext()).inflate(resource, parent, false);
        }

        RadioButton proposition = listView.findViewById(R.id.proposition);
        System.out.println(getItem(position).getContent());
        proposition.setText(getItem(position).getContent());

        return listView;
    }


}
