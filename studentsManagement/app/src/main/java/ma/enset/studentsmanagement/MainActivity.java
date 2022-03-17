package ma.enset.studentsmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import ma.enset.studentsmanagement.model.ListAdapter;
import ma.enset.studentsmanagement.model.Student;


public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab;
    ListView listView;
    ArrayList<Student> studentsList;
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentsList = new ArrayList<>();
        listView = findViewById(R.id.listView);
        adapter = new ListAdapter(this, R.layout.listv, studentsList);
        listView.setAdapter(adapter);

        //get student from file
        loadData();

        //TODO: add OnItemClickListener() to show students info in a popup window

        //floatting icon to get to activity_add_student
        fab = findViewById(R.id.floating_action_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),AddStudentActivity.class);
                intent.putExtra("list",studentsList);
                startActivity(intent);
            }
        });



    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("Data_students", MODE_PRIVATE);
        Gson gson = new Gson();
        String data = sharedPreferences.getString("list_students", null);
        Type type = new TypeToken<ArrayList<Student>>(){}.getType();
        ArrayList<Student> list = gson.fromJson(data, type);
        if(list!=null) {
            for (Student st : list) {
                studentsList.add(st);
                System.out.println(st.getTel().toString());
            }
        }
        if(studentsList == null){
            studentsList = new ArrayList<>();
        }

        adapter.notifyDataSetChanged();

    }


}
