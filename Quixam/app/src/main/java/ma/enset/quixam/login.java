package ma.enset.quixam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ma.enset.quixam.model.PageStudents;
import ma.enset.quixam.model.PageTeachers;
import ma.enset.quixam.model.Quiz;
import ma.enset.quixam.model.Student;
import ma.enset.quixam.model.Teacher;
import ma.enset.quixam.services.StudentAPICall;
import ma.enset.quixam.services.TeacherAPICall;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class login extends AppCompatActivity {
    List<Student> studentsList = new ArrayList<>();
    List<Teacher> teachersList = new ArrayList<>();
    boolean found = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        String userType = getIntent().getStringExtra("userType");

        EditText emailField = findViewById(R.id.emailField);
        EditText passwordField = findViewById(R.id.passwordField);
        TextView warning = findViewById(R.id.warning_SignIn);
        TextView redirect_signUp = findViewById(R.id.signUpRedirect);
        Button loginButton = findViewById(R.id.loginButton);
        Retrofit retrofit = APIClient.getClient();

        if(userType.equals("Student")) {
            StudentAPICall studentCall = retrofit.create(StudentAPICall.class);
            Call<PageStudents> students = studentCall.getAllStudents();
            students.enqueue(new Callback<PageStudents>() {
                @Override
                public void onResponse(Call<PageStudents> call, Response<PageStudents> response) {
                    PageStudents Studens = response.body();
                    studentsList.addAll(Studens.getData());
                }

                @Override
                public void onFailure(Call<PageStudents> call, Throwable t) {
                    System.out.println(t);
                }
            });
        }
        else {
            TeacherAPICall teachersCall = retrofit.create(TeacherAPICall.class);
            Call<PageTeachers> teachers = teachersCall.getAllTeachers();
            teachers.enqueue(new Callback<PageTeachers>() {
                @Override
                public void onResponse(Call<PageTeachers> call, Response<PageTeachers> response) {
                    PageTeachers teaches = response.body();
                    teachersList.addAll(teaches.getData());
                }

                @Override
                public void onFailure(Call<PageTeachers> call, Throwable t) {
                    System.out.println(t);
                }
            });
        }
        /*redirect_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(emailField.getText().toString());
                System.out.println(passwordField.getText().toString());
                warning.setVisibility(View.INVISIBLE);
                for(Teacher teacher: teachersList){
                    if(teacher.getEmail().equals(emailField.getText().toString())
                            && teacher.getPassword().equals(passwordField.getText().toString())){
                        found = true;
                        Intent intent = new Intent(getBaseContext(), list_quizes_teacher.class);
                        intent.putExtra("userId", teacher.get_id());
                        System.out.println(teacher.get_id());
                        startActivity(intent);
                    }
                }
                for(Student student: studentsList){
                    if(student.getEmail().equals(emailField.getText().toString())
                            && student.getPassword().equals(passwordField.getText().toString())){
                        found = true;
                        Intent intent = new Intent(getBaseContext(),  Student_quizes.class);
                        intent.putExtra("userId", student.get_id());
                        startActivity(intent);
                    }
                }

                if(!found)
                    warning.setVisibility(View.VISIBLE);
            }
        });


    }

    public void redirectToSignUp(View view) {
        Intent intent = new Intent(getBaseContext(), signIn.class);
        startActivity(intent);
    }
}