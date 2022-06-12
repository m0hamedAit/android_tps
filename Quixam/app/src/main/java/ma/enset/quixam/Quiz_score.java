package ma.enset.quixam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class Quiz_score extends AppCompatActivity {
    private Handler mWaitHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_score);

        String quizName = getIntent().getStringExtra("quizName");
        String score = getIntent().getStringExtra("score");
        String studentId = getIntent().getStringExtra("studentId");

        TextView quizn = findViewById(R.id.quiz_name4);
        TextView scoren = findViewById(R.id.score);

        quizn.setText(quizName);
        scoren.setText(score);

        mWaitHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                //The following code will execute after the 5 seconds.
                try {
                    Intent intent = new Intent(getApplicationContext(), Student_quizes.class);
                    intent.putExtra("userId", studentId);
                    startActivity(intent);
                    finish();
                } catch (Exception ignored) {
                    ignored.printStackTrace();
                }
            }
        }, 5000);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mWaitHandler.removeCallbacksAndMessages(null);

    }
}