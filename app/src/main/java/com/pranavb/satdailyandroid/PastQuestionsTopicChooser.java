package com.pranavb.satdailyandroid;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class PastQuestionsTopicChooser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_questions_topic_chooser);

        getWindow().getDecorView().setBackgroundColor(Color.parseColor("#E1E2E1"));

        final Button math = findViewById(R.id.pastmath);
        final Button reading = findViewById(R.id.pastreading);
        final Button writing = findViewById(R.id.pastwriting);

        math.setBackgroundResource(R.drawable.answerchoice);
        reading.setBackgroundResource(R.drawable.answerchoice);
        writing.setBackgroundResource(R.drawable.answerchoice);

        math.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i;
                i = new Intent(PastQuestionsTopicChooser.this, PastMathWritingReadingChooser.class);
                i.putExtra("whichone", "1");
                startActivity(i);
            }
        });

        writing.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i;
                i = new Intent(PastQuestionsTopicChooser.this, PastMathWritingReadingChooser.class);
                i.putExtra("whichone", "2");
                startActivity(i);
            }
        });

        reading.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i;
                i = new Intent(PastQuestionsTopicChooser.this, PastMathWritingReadingChooser.class);
                i.putExtra("whichone", "3");
                startActivity(i);
            }
        });
    }
}