package ma.enset.quixam;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import ma.enset.quixam.model.Quiz;
import ma.enset.quixam.services.QuizAPICall;
import ma.enset.quixam.services.StudentAPICall;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class add_edit_quiz extends AppCompatActivity {
    // Permissions for accessing the storage
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_quiz);

        String idQuiz = getIntent().getStringExtra("idQuiz")!=null ?  getIntent().getStringExtra("idQuiz") : null;

        EditText quizName = findViewById(R.id.quizName);
        EditText deadline = findViewById(R.id.deadline);
        EditText timeLimit = findViewById(R.id.timeLimit);
        Button editQuestions = findViewById(R.id.questionsButton);
        Button uploadStudentsList = findViewById(R.id.uploadStudentList);
        Button saveButton = findViewById(R.id.saveButton);

        Retrofit retrofit = APIClient.getClient();

        if(idQuiz != null){

            QuizAPICall quizAPICall = retrofit.create(QuizAPICall.class);
            Call<Quiz> quizInfo = quizAPICall.getQuizInfo(idQuiz);

            quizInfo.enqueue(new Callback<Quiz>() {
                @Override
                public void onResponse(Call<Quiz> call, Response<Quiz> response) {
                    Quiz quiz = response.body();
                    System.out.println(quiz);
                    quizName.setText(quiz.getTitle());
                    deadline.setText("Tomorrow");
                    timeLimit.setText("30");
                }

                @Override
                public void onFailure(Call<Quiz> call, Throwable t) {
                    Toast.makeText(add_edit_quiz.this, "error", Toast.LENGTH_SHORT).show();
                }
            });
        }


        editQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), add_edit_questions.class);
                intent.putExtra("idQuiz", idQuiz);
                startActivity(intent);
            }
        });

        uploadStudentsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}