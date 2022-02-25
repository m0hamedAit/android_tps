package ma.enset.pr12;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonAdd = findViewById(R.id.button);
        TextView textView = findViewById(R.id.textView);
        ListView listView = findViewById(R.id.listView);
        List<String> operations = new ArrayList<>();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, operations );
        listView.setAdapter(adapter);

        buttonAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String operation = textView.getText().toString();
                if(operation.matches("[0-9]+[-+*\\/^][0-9]+")){
                    int result = calculer(operation);
                    String op = operation + " = " + String.valueOf(result);
                    operations.add(op);
                    adapter.notifyDataSetChanged();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Error !!", Toast.LENGTH_SHORT).show();
                }
                textView.setText("");
            }
        });

    }

    int calculer(String op){
        String[] operators = new String[] {"-","+","/","*","^"};
        for(int i=0; i<operators.length;i++) {
            String operator = operators[i];
            if (op.contains(operator)) {
                    switch (operator) {
                        case "+":
                            return Integer.parseInt(op.split("\\+")[0]) + Integer.parseInt(op.split("\\+")[1]);
                        case "-":
                            return Integer.parseInt(op.split(operator)[0]) - Integer.parseInt(op.split(operator)[1]);
                        case "/":
                            return Integer.parseInt(op.split(operator)[0]) / Integer.parseInt(op.split(operator)[1]);
                        case "*":
                            return Integer.parseInt(op.split("\\*")[0]) * Integer.parseInt(op.split("\\*")[1]);
                        case "^":
                            return Integer.parseInt(op.split("\\^")[0]) ^ Integer.parseInt(op.split("\\^")[1]);
                    }
            }
        }

        return 0;
    }


}