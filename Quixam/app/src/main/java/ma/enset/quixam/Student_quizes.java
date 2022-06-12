package ma.enset.quixam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ma.enset.quixam.model.Quiz;
import ma.enset.quixam.services.StudentAPICall;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Student_quizes extends AppCompatActivity {
    ListView listQuizes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_quizes);

        List<Quiz> list = new ArrayList<>();

        listQuizes = findViewById(R.id.list_quizes);
        String idStudent = getIntent().getStringExtra("userId");
        QuizListAdapter adapter = new QuizListAdapter(this,  R.layout.quiz_component, list);
        listQuizes.setAdapter(adapter);

        Retrofit retrofit = APIClient.getClient();

        StudentAPICall studentCall = retrofit.create(StudentAPICall.class);
        Call<List<Quiz>> studentQuizes = studentCall.getStudentQuizes(idStudent);   //idStudent

        listQuizes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getBaseContext(), before_start_quiz.class);
                intent.putExtra("idQuiz", list.get(i).get_id());
                intent.putExtra("studentId", idStudent);
                intent.putExtra("quizName", list.get(i).getTitle());
                intent.putExtra("question", 0);
                startActivity(intent);
            }
        });

        list.clear();
        studentQuizes.enqueue(new Callback<List<Quiz>>() {
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
                Toast.makeText(Student_quizes.this, "error", Toast.LENGTH_SHORT).show();
            }
        });

    }
}