package com.pranavb.satdailyandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Random;

public class UniversalCorrect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universal_correct);

        backFix();
        graphics();
        increment();
    }

    public void backFix() {
        Intent intent = getIntent();
        String whatami = intent.getStringExtra("whatami");
        SharedPreferences preferences = this.getSharedPreferences("dailyids", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("tempholder", whatami);
        editor.commit();
    }

    public void graphics() {
        getWindow().getDecorView().setBackgroundColor(Color.parseColor("#E1E2E1"));

        Intent intent = getIntent();
        final TextView explanationText = findViewById(R.id.correctExplanation);
        explanationText.setText(intent.getStringExtra("explanation"));
        final TextView explanationTitle = findViewById(R.id.correctTitle);
        explanationTitle.setText(generate());
    }

    public String generate() {
        Random rn = new Random();
        if((rn.nextInt(3 - 1 + 1) + 1) == 1) {
            return "Correct!";
        } else if ((rn.nextInt(3 - 1 + 1) + 1) == 2) {
            return "Alright!";
        } else if ((rn.nextInt(3 - 1 + 1) + 1) == 3) {
            return "Great Job!";
        }
        return "Correct!";
    }

    public void increment() {
        SharedPreferences pref = this.getSharedPreferences("scores", MODE_PRIVATE);
        int value = (Integer.parseInt(pref.getString("correct",null)));
        value++;
        SharedPreferences preferences = this.getSharedPreferences("scores", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("correct", String.valueOf(value));
        editor.commit();
    }
}