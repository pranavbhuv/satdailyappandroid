package com.pranavb.satdailyandroid;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TodaysQuestionChooser extends AppCompatActivity {

    Intent i;


    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat mainFormat = new SimpleDateFormat("MM/dd/yy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todays_question_chooser);

        final Button math = findViewById(R.id.todaysmath);
        final Button reading = findViewById(R.id.todaysreading);
        final Button writing = findViewById(R.id.todayswriting);

        math.setBackgroundResource(R.drawable.answerchoice);
        reading.setBackgroundResource(R.drawable.answerchoice);
        writing.setBackgroundResource(R.drawable.answerchoice);

        mathDate();
        readingDate();
        writingDate();

        getWindow().getDecorView().setBackgroundColor(Color.parseColor("#E1E2E1"));

        SharedPreferences sh = getSharedPreferences("dailyids", MODE_APPEND);
        SharedPreferences preferences = this.getSharedPreferences("dailyids", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("tempmath", sh.getString("mathid", "2"));
        editor.putString("tempreading", sh.getString("readingid", "2"));
        editor.putString("tempwriting", sh.getString("writingid", "2"));
        editor.commit();

        math.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                i = new Intent(TodaysQuestionChooser.this, MathWritingReadingQuestion.class);
                i.putExtra("whatami", "1");
                i.putExtra("questionid", sh.getString("mathid", "2"));
                startActivity(i);
            }
        });

        writing.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                i = new Intent(TodaysQuestionChooser.this, MathWritingReadingQuestion.class);
                i.putExtra("whatami", "2");
                i.putExtra("questionid", sh.getString("writingid", "2"));
                startActivity(i);
            }
        });

        reading.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                i = new Intent(TodaysQuestionChooser.this, MathWritingReadingQuestion.class);
                i.putExtra("whatami", "3");
                i.putExtra("questionid", sh.getString("readingid", "3"));
                startActivity(i);
            }
        });
    }

    public void mathDate() {
        SharedPreferences sh = getSharedPreferences("dailyids", MODE_APPEND);
        final Button reading = findViewById(R.id.todaysreading);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 18);
        cal.set(Calendar.MONTH, 5);
        cal.set(Calendar.YEAR, 2021);
        cal.add(Calendar.DAY_OF_MONTH, Integer.parseInt(sh.getString("mathid", "2")));
        String formatted = mainFormat.format(cal.getTime());
        reading.setText("Reading for " + formatted);
    }

    public void readingDate() {
        SharedPreferences sh = getSharedPreferences("dailyids", MODE_APPEND);
        final Button math = findViewById(R.id.todaysmath);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 18);
        cal.set(Calendar.MONTH, 5);
        cal.set(Calendar.YEAR, 2021);
        cal.add(Calendar.DAY_OF_MONTH, Integer.parseInt(sh.getString("readingid", "2")));
        String formatted = mainFormat.format(cal.getTime());
        math.setText("Math for " + formatted);
    }

    public void writingDate() {
        SharedPreferences sh = getSharedPreferences("dailyids", MODE_APPEND);
        final Button writing = findViewById(R.id.todayswriting);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 18);
        cal.set(Calendar.MONTH, 5);
        cal.set(Calendar.YEAR, 2021);
        cal.add(Calendar.DAY_OF_MONTH, Integer.parseInt(sh.getString("writingid", "2")));
        String formatted = mainFormat.format(cal.getTime());
        writing.setText("Writing for " + formatted);
    }
}