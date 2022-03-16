package ma.enset.studentsmanagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.ArrayList;

import ma.enset.studentsmanagement.model.Student;

public class AddStudentActivity extends AppCompatActivity {
    ArrayList<Student> listStudents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        Intent intent = getIntent();
        listStudents = (ArrayList<Student>) intent.getSerializableExtra("list");

        TextView nom= findViewById(R.id.nomField);
        TextView prenom= findViewById(R.id.prenomField);
        TextView email= findViewById(R.id.emailField);
        TextView tel = findViewById(R.id.telField);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.sexe, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        Button button = findViewById(R.id.addButton);
        //
        SharedPreferences sharedPreferences = getSharedPreferences("Data_students", MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        Gson gson = new Gson();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Student student = new Student(prenom.getText().toString(), nom.getText().toString(), email.getText().toString(), tel.getText().toString(), spinner.getSelectedItem().toString());
                listStudents.add(student);

               // save
                String data = gson.toJson(listStudents);
                edit.putString("list_students", data);
                edit.apply();
                // return to main_activity
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });


    }

}
