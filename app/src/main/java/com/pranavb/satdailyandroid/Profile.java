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
        SharedPreferences pref = this.getSharedPreferences("scores", MODE_PRIVATE);
        int accur = ((Integer.parseInt((pref.getString("correct", null)))) * 100) / (Integer.parseInt((pref.getString("correct", null))) + Integer.parseInt((pref.getString("incorrect", null))));
        String predict = "";
        if (accur <= 20) {
            predict = "≤ 1000";
        } else if (accur <= 25) {
            predict = "≈ 1050";
        } else if (accur <= 30) {
            predict = "≈ 1100";
        } else if (accur <= 35) {
            predict = "≈ 1150";
        } else if (accur <= 40) {
            predict = "≈ 1200";
        } else if (accur <= 45) {
            predict = "≈ 1220";
        } else if (accur <= 50) {
            predict = "≈ 1250";
        } else if (accur <= 55) {
            predict = "≈ 1310";
        } else if (accur <= 60) {
            predict = "≈ 1330";
        } else if (accur <= 64) {
            predict = "≈ 1360";
        } else if (accur <= 70) {
            predict = "≈ 1420";
        } else if (accur <= 75) {
            predict = "≈ 1430";
        } else if (accur <= 80) {
            predict = "≈ 1460";
        } else if (accur <= 85) {
            predict = "≈ 1500";
        } else if (accur <= 90) {
            predict = "≈ 1550";
        } else if (accur <= 95) {
            predict = "≈ 1570";
        } else if (accur <= 100) {
            predict = "≈ 1600";
        }
        return predict;
    }
}