package ma.enset.quixam;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import ma.enset.quixam.model.Question;
import ma.enset.quixam.services.QuizAPICall;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class before_start_quiz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_start_quiz);

        String idQuiz = getIntent().getStringExtra("idQuiz");
        String quizN = getIntent().getStringExtra("quizName");
        String studentId = getIntent().getStringExtra("studentId");
        TextView quizInfo = findViewById(R.id.quiz_info_begun);
        TextView quizName = findViewById(R.id.quiz_name_start);
        Button startButton = findViewById(R.id.begin_button);

        quizName.setText(quizN);

        Retrofit retrofit = APIClient.getClient();

        QuizAPICall quizAPICall = retrofit.create(QuizAPICall.class);
        Call<List<Question>> quizQuestions = quizAPICall.getQuizQuestions(idQuiz);


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

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), taking_quiz.class);
                intent.putExtra("idQuiz", idQuiz);
                intent.putExtra("studentId", studentId);
                intent.putExtra("quizName", quizN);
                startActivity(intent);
            }
        });
    }
}