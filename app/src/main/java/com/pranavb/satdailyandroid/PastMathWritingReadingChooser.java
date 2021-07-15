package com.pranavb.satdailyandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PastMathWritingReadingChooser extends AppCompatActivity {

    /**
     * 1: Math
     * 2: Writing
     * **/

    String whichone = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_math_writing_reading_chooser);

        getWindow().getDecorView().setBackgroundColor(Color.parseColor("#E1E2E1"));

        Intent intent = getIntent();
        whichone = intent.getStringExtra("whichone");

        if(whichone.equals("1")) {
            doMath();
        } else if (whichone.equals("2")) {
            doWriting();
        } else {
            doReading();
        }
    }

    public void doMath() {
        LinearLayout layout = (LinearLayout) findViewById(R.id.pastMathVStack);
        SharedPreferences sh = getSharedPreferences("dailyids", MODE_APPEND);
        for(int i = 1; i <= Integer.parseInt(sh.getString("mathid", "2")); i++) {
            Button btnTag = new Button(this);
            btnTag.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            btnTag.setText("Math Question For: " + dateCalc(i));
            btnTag.setId(i);
            btnTag.setBackgroundResource(R.drawable.answerchoice);
            btnTag.setTransformationMethod(null);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int width = displayMetrics.widthPixels;
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) btnTag.getLayoutParams();
            params.width = (int) (width * 0.9);
            btnTag.setLayoutParams(params);
            layout.addView(btnTag);
            int finalI = i;
            SharedPreferences preferences = this.getSharedPreferences("dailyids", MODE_PRIVATE);
            btnTag.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent;
                    intent = new Intent(PastMathWritingReadingChooser.this, MathWritingReadingQuestion.class);
                    intent.putExtra("whatami", "1");
                    intent.putExtra("questionid", String.valueOf(finalI));
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("tempmath", String.valueOf(finalI));
                    editor.commit();
                    startActivity(intent);
                }
            });
        }
    }

    public void doWriting() {
        LinearLayout layout = (LinearLayout) findViewById(R.id.pastMathVStack);
        SharedPreferences sh = getSharedPreferences("dailyids", MODE_APPEND);
        for(int i = 1; i <= Integer.parseInt(sh.getString("writingid", "2")); i++) {
            Button btnTag = new Button(this);
            btnTag.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            btnTag.setText("Writing Question For: " + dateCalc(i));
            btnTag.setId(i);
            btnTag.setBackgroundResource(R.drawable.answerchoice);
            btnTag.setTransformationMethod(null);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int width = displayMetrics.widthPixels;
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) btnTag.getLayoutParams();
            params.width = (int) (width * 0.9);
            btnTag.setLayoutParams(params);
            layout.addView(btnTag);
            int finalI = i;
            SharedPreferences preferences = this.getSharedPreferences("dailyids", MODE_PRIVATE);
            btnTag.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent;
                    intent = new Intent(PastMathWritingReadingChooser.this, MathWritingReadingQuestion.class);
                    intent.putExtra("whatami", "2");
                    intent.putExtra("questionid", String.valueOf(finalI));
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("tempwriting", String.valueOf(finalI));
                    editor.commit();
                    startActivity(intent);
                }
            });
        }
    }

    public void doReading() {
        LinearLayout layout = (LinearLayout) findViewById(R.id.pastMathVStack);
        SharedPreferences sh = getSharedPreferences("dailyids", MODE_APPEND);
        for(int i = 1; i <= Integer.parseInt(sh.getString("readingid", "2")); i++) {
            Button btnTag = new Button(this);
            btnTag.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            btnTag.setText("Reading Question For: " + dateCalc(i));
            btnTag.setId(i);
            btnTag.setBackgroundResource(R.drawable.answerchoice);
            btnTag.setTransformationMethod(null);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int width = displayMetrics.widthPixels;
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) btnTag.getLayoutParams();
            params.width = (int) (width * 0.9);
            btnTag.setLayoutParams(params);
            layout.addView(btnTag);
            int finalI = i;
            SharedPreferences preferences = this.getSharedPreferences("dailyids", MODE_PRIVATE);
            btnTag.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent;
                    intent = new Intent(PastMathWritingReadingChooser.this, MathWritingReadingQuestion.class);
                    intent.putExtra("whatami", "3");
                    intent.putExtra("questionid", String.valueOf(finalI));
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("tempreading", String.valueOf(finalI));
                    editor.commit();
                    startActivity(intent);
                }
            });
        }
    }

    public String dateCalc(int add) {
        SimpleDateFormat mainFormat = new SimpleDateFormat("MMM dd, yyyy");
        SharedPreferences sh = getSharedPreferences("dailyids", MODE_APPEND);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 18);
        cal.set(Calendar.MONTH, 5);
        cal.set(Calendar.YEAR, 2021);
        cal.add(Calendar.DAY_OF_MONTH, add);
        return mainFormat.format(cal.getTime());
    }
}