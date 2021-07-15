package com.pranavb.satdailyandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Random;

public class UniversalIncorrect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universal_incorrect);

        backFix();
        graphics();
        decrement();
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

        final TextView explanationText = findViewById(R.id.incorrectExplanation);
        explanationText.setText(generateExplanation());
        final TextView explanationTitle = findViewById(R.id.incorrectTitle);
        explanationTitle.setText(generateTitle());
    }

    public String generateExplanation() {
        Random rn = new Random();
        if((rn.nextInt(4 - 1 + 1) + 1) == 1) {
            return "So close... are you sure you understands this concept?";
        } else if ((rn.nextInt(4 - 1 + 1) + 1) == 2) {
            return "Look on the bright side: you are getting better!";
        } else if ((rn.nextInt(4 - 1 + 1) + 1) == 3) {
            return "You are almost there! Make sure you are clicking the right choice before you submit as well!";
        } else if ((rn.nextInt(4 - 1 + 1) + 1) == 4) {
            return "Double check your thinking! Try recalling the rules or patterns pertaining to questions like these!";
        }
        return "Look on the bright side: you are getting better!";
    }

    public String generateTitle() {
        Random rn = new Random();
        if((rn.nextInt(3 - 1 + 1) + 1) == 1) {
            return "Nope!";
        } else if ((rn.nextInt(3 - 1 + 1) + 1) == 2) {
            return "Incorrect!";
        } else if ((rn.nextInt(3 - 1 + 1) + 1) == 3) {
            return "Not Quite!";
        }
        return "Nope!";
    }

    public void decrement() {
        SharedPreferences pref = this.getSharedPreferences("scores", MODE_PRIVATE);
        int value = (Integer.parseInt(pref.getString("incorrect",null)));
        value++;
        SharedPreferences preferences = this.getSharedPreferences("scores", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("incorrect", String.valueOf(value));
        editor.commit();
    }
}