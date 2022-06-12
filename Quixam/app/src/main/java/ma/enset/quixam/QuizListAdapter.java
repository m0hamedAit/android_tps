package ma.enset.quixam;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import ma.enset.quixam.model.Question;
import ma.enset.quixam.model.Quiz;
import ma.enset.quixam.services.QuizAPICall;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QuizListAdapter extends ArrayAdapter<Quiz> {
    private int resource;

    public QuizListAdapter(@NonNull Context context, int resource, @NonNull List<Quiz> quizzes) {
        super(context, resource, quizzes);
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listView = convertView;
        if(listView == null){
            listView = LayoutInflater.from(getContext()).inflate(resource, parent, false);
        }

        TextView quizName = listView.findViewById(R.id.quiz_name);
        TextView quizInfo = listView.findViewById(R.id.quiz_info);
        TextView quizDeadline = listView.findViewById(R.id.quiz_deadline);

        quizName.setText(getItem(position).getTitle());
        quizDeadline.setText("Tomorrow");   ////
        //
        Retrofit retrofit = APIClient.getClient();

        QuizAPICall quizAPICall = retrofit.create(QuizAPICall.class);
        Call<List<Question>> quizQuestions = quizAPICall.getQuizQuestions(getItem(position).get_id());


        quizQuestions.enqueue(new Callback<List<Question>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                if(!response.isSuccessful()){
                    return;
                }
                List<Question> questions = response.body();
                int quizTime = questions.stream().mapToInt(Question::getDuration).sum();
                quizInfo.setText(questions.size()+" questions in "+ quizTime +" seconds");
            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {

            }
        });


        return listView;
    }


}
