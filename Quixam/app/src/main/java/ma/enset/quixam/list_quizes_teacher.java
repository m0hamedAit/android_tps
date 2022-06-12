package ma.enset.quixam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ma.enset.quixam.model.Quiz;
import ma.enset.quixam.services.TeacherAPICall;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class list_quizes_teacher extends AppCompatActivity {
    ListView listQuizes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_quizes_teacher);

        String idTeacher = getIntent().getStringExtra("userId").toString();
        System.out.println(idTeacher);
        List<Quiz> list = new ArrayList<>();

        listQuizes = findViewById(R.id.questions_list);
        Button addQuiz = findViewById(R.id.add_quiz);

        QuizListAdapter adapter = new QuizListAdapter(this,  R.layout.quiz_component, list);
        listQuizes.setAdapter(adapter);

        Retrofit retrofit = APIClient.getClient();

        TeacherAPICall teacherCall = retrofit.create(TeacherAPICall.class);
        Call<List<Quiz>> teacherQuizes = teacherCall.getTeacherQuizes(idTeacher);   // change to idTeacher

        listQuizes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getBaseContext(), add_edit_quiz.class);
                intent.putExtra("idQuiz", list.get(i).get_id());
                startActivity(intent);
            }
        });

        list.clear();
        teacherQuizes.enqueue(new Callback<List<Quiz>>() {
            @Override
            public void onResponse(Call<List<Quiz>> call, Response<List<Quiz>> response) {
                List<Quiz> quizes = response.body();
                System.out.println(quizes);
                for(Quiz quiz:quizes){
                    list.add(quiz);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Quiz>> call, Throwable t) {
                Toast.makeText(list_quizes_teacher.this, "error", Toast.LENGTH_SHORT).show();
            }
        });

        addQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), add_edit_quiz.class);
                startActivity(intent);
            }
        });

    }
}