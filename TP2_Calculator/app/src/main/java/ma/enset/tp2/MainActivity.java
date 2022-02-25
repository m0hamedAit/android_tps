package ma.enset.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textView ;
    int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);

    }

    public void onClick(View view) {
        if(view.getId() == R.id.equalButton){
            equal();
        }
        else if(view.getId() == R.id.deleteButton){
            String t = textView.getText().toString();
            if(t.length()>1)
                textView.setText(t.substring(0, t.length() - 1));
            else
                textView.setText("");
        }
        else if(view.getId() == R.id.resetButton){
            textView.setText("");
        }
        else{
            Button b = (Button)view;
            textView.setText(textView.getText().toString() + b.getText().toString());
            if(textView.getText().toString().matches("[0-9]+[-+*\\/^][0-9]+")){
                equal();
            }
        }
    }

    int calculer(String op) {
        String[] operators = new String[]{"-", "+", "/", "*"};
        for (int i = 0; i < operators.length; i++) {
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
                }
            }
        }

        return 0;
    }

    void equal(){
        String operation = textView.getText().toString();
        if (operation.matches("[0-9]+[-+*\\/^][0-9]+")) {
            result = calculer(operation);

        } else {
            Toast.makeText(getApplicationContext(), "Error : Entrez une operation valide !!", Toast.LENGTH_SHORT).show();
        }
        textView.setText(String.valueOf(result));
    }

}