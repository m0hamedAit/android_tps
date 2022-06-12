               package ma.enset.quixam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ma.enset.quixam.model.Answers;
import ma.enset.quixam.model.Question;
import ma.enset.quixam.model.Quiz;
import ma.enset.quixam.services.QuestionAPICall;
import ma.enset.quixam.services.QuizAPICall;
import ma.enset.quixam.services.StudentAPICall;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class taking_quiz extends AppCompatActivity {
    ListView listPropositions;
    List<Question> questionss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taking_quiz);

        List<Answers> list = new ArrayList<>();
        ListView listProps = findViewById(R.id.list_propositions);

        String idQuiz = getIntent().getStringExtra("idQuiz");
        String quizN = getIntent().getStringExtra("quizName");
        String studentId = getIntent().getStringExtra("studentId");
        int question_ = getIntent().getIntExtra("question",0);


        TextView quizName = findViewById(R.id.quiz_name1);
        TextView question = findViewById(R.id.question);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        Button nextButton = findViewById(R.id.button_next);

        quizName.setText(quizN);

        ResponsesListAdapter adapter = new ResponsesListAdapter(this,  R.layout.response_component, list);
        listProps.setAdapter(adapter);

        Retrofit retrofit = APIClient.getClient();

        QuizAPICall quizAPICall = retrofit.create(QuizAPICall.class);
        System.out.println(idQuiz);
        Call<List<Question>> questions = quizAPICall.getQuizQuestions(idQuiz);

        questions.enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                questionss = response.body();
                question.setText(questionss.get(question_).getContent());
                QuestionAPICall questionCall = retrofit.create(QuestionAPICall.class);
                Call<List<Answers>> questionAnswers = questionCall.getQuestionPropositions(questionss.get(question_).get_id());

                list.clear();
                questionAnswers.enqueue(new Callback<List<Answers>>() {
                    @Override
                    public void onResponse(Call<List<Answers>> call, Response<List<Answers>> response) {
                        List<Answers> answers = response.body();
                        System.out.println(answers);
                        list.addAll(answers);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<List<Answers>> call, Throwable t) {
                        Toast.makeText(taking_quiz.this, "error", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {
                Toast.makeText(taking_quiz.this, "error", Toast.LENGTH_SHORT).show();
            }
        });




        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // calcul score
                if(questionss.size()>question_+1){
                    Intent intent = new Intent(getBaseContext(), taking_quiz.class);
                    intent.putExtra("idQuiz", idQuiz);
                    intent.putExtra("quizName", quizN);
                    intent.putExtra("studentId", studentId);
                    intent.putExtra("question", question_+1);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(getBaseContext(), Quiz_score.class);
                    intent.putExtra("idQuiz", idQuiz);
                    intent.putExtra("quizName", quizN);
                    intent.putExtra("studentId", studentId);
                    intent.putExtra("score", "100 %");
                    startActivity(intent);
                }
            }
        });

    }
}