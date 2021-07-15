package com.pranavb.satdailyandroid;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        graphics();
    }

    public void graphics() {
        getWindow().getDecorView().setBackgroundColor(Color.parseColor("#E1E2E1"));
        final TextView username = findViewById(R.id.usernameTitle);
        username.setText(getUsername());
        final TextView information = findViewById(R.id.informationText);
        information.setText(generate());
    }

    public String getUsername() {
        SharedPreferences pref = this.getSharedPreferences("settings", MODE_PRIVATE);
        String value = pref.getString("name", null);
        return value;
    }

    public String generate() {
        SharedPreferences pref = this.getSharedPreferences("scores", MODE_PRIVATE);
        return "Correct Answers: " + pref.getString("correct", null) + "\nIncorrect Answers: " + pref.getString("incorrect", null)
                + "\nTotal Questions: " + String.valueOf((Integer.parseInt((pref.getString("correct", null))) + Integer.parseInt((pref.getString("incorrect", null)))))
                + "\nAccuracy: " + ((Integer.parseInt((pref.getString("correct", null)))) * 100) / (Integer.parseInt((pref.getString("correct", null))) + Integer.parseInt((pref.getString("incorrect", null)))) + "%"
                + "\n\nPredicted Score: " + predictScore();
    }

    public String predictScore() {
        // Redacted
        return predict;
    }
}
