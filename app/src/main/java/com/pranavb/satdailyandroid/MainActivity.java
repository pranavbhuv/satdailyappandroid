package com.pranavb.satdailyandroid;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getWindow().getDecorView().setBackgroundColor(Color.parseColor("#112352"));

        if (checkScores()) {
            generateScores();
        }

        if(checkName()) {
            askName();
        }

        getSupportActionBar().hide();
        setDailyIds();

        final Button todays = findViewById(R.id.todays);
        todays.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TodaysQuestionChooser.class));
            }
        });

        final Button pastquestions = findViewById(R.id.pastquestions);
        pastquestions.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PastQuestionsTopicChooser.class));
            }
        });

        final Button profile = findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Profile.class));
            }
        });

        final Button other = findViewById(R.id.other);
        other.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Other.class));
            }
        });

        todays.setBackgroundResource(R.drawable.answerchoice);
        pastquestions.setBackgroundResource(R.drawable.answerchoice);
        profile.setBackgroundResource(R.drawable.answerchoice);
        other.setBackgroundResource(R.drawable.answerchoice);
    }

    public void setDailyIds() {
        SharedPreferences preferences = this.getSharedPreferences("dailyids", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        DocumentReference docRef = db.collection("dailyids").document("daily");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    assert document != null;
                    if (document.exists()) {
                        long resourceNumber = (long) document.get("resourcesnumber");
                        List<String> information = (List<String>) document.get("information");
                        assert information != null;
                        editor.putString("mathid", information.get(0));
                        editor.putString("readingid", information.get(1));
                        editor.putString("writingid", information.get(2));
                        editor.putString("zwodid", information.get(3));
                        editor.putString("resourcesnumber", String.valueOf(resourceNumber));

                        List<String> resourceNames = (List<String>) document.get("resourcesnames");
                        for (int i = 0; i < resourceNumber; i++) {
                            assert resourceNames != null;
                            editor.putString(String.valueOf(i), resourceNames.get(i));
                        }
                        List<String> resourceLinks = (List<String>) document.get("resourceslink");
                        for (int i = 0; i < resourceNumber; i++) {
                            assert resourceLinks != null;
                            editor.putString((String.valueOf(i))+"i", resourceLinks.get(i));
                        }
                        editor.commit();
                    }
                }
            }
        });
    }

    public void generateScores() {
        SharedPreferences preferences = this.getSharedPreferences("scores", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("correct", "0");
        editor.putString("incorrect", "1");
        editor.commit();
    }

    public boolean checkScores() {
        SharedPreferences pref = this.getSharedPreferences("scores", MODE_PRIVATE);
        String value = pref.getString("correct",null);
        return value == null;
    }

    public boolean checkName() {
        SharedPreferences pref = this.getSharedPreferences("settings", MODE_PRIVATE);
        String value = pref.getString("name",null);
        return value == null;
    }

    private String m_Text = "";

    public void askName() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("What is your name?");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        SharedPreferences preferences = this.getSharedPreferences("settings", MODE_PRIVATE);

        builder.setPositiveButton("Okay!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("name", m_Text);
                editor.commit();
            }
        });
        builder.show();
    }
}