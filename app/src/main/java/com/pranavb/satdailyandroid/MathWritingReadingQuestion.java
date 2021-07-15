package com.pranavb.satdailyandroid;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class MathWritingReadingQuestion extends AppCompatActivity {

    /**
     * 1: Math
     * 2: Writing
     * 3: Reading
     * **/

    String darkColor = "#112352";
    String answerChoice = "null";
    String correctAnswer = "";
    String explanation = "";
    String whatami = "";
    String questionID = "";

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        SharedPreferences sh = getSharedPreferences("dailyids", MODE_APPEND);
        questionID = intent.getStringExtra("questionid");
        whatami = intent.getStringExtra("whatami");
        if(whatami == null) {
            if(sh.getString("tempholder", "2").equals("1")) {
                whatami = "1";
                questionID = sh.getString("tempmath", "2");
                firebaseCallsAndSetsMath();
            } else if(sh.getString("tempholder", "2").equals("2")) {
                whatami = "2";
                questionID = sh.getString("tempwriting", "2");
                firebaseCallsAndSetsWriting();
            } else {
                whatami = "3";
                questionID = sh.getString("tempreading", "2");
                firebaseCallsAndSetsReading();
            }
        } else {
            if (whatami.equals("1")) {
                firebaseCallsAndSetsMath();
            } else if(whatami.equals("2")) {
                firebaseCallsAndSetsWriting();
            } else {
                firebaseCallsAndSetsReading();
            }
        }
        setContentView(R.layout.activity_math_writing_reading_question);
        ActionBar actionBar = getActionBar();

        final Button mopta = findViewById(R.id.mathoptiona);
        final Button moptb = findViewById(R.id.mathoptionb);
        final Button moptc = findViewById(R.id.mathoptionc);
        final Button moptd = findViewById(R.id.mathoptiond);
        final Button submit = findViewById(R.id.submit);

        getWindow().getDecorView().setBackgroundColor(Color.parseColor("#E1E2E1"));

        mopta.setBackgroundResource(R.drawable.answerchoice);
        moptb.setBackgroundResource(R.drawable.answerchoice);
        moptc.setBackgroundResource(R.drawable.answerchoice);
        moptd.setBackgroundResource(R.drawable.answerchoice);

        submit.setBackgroundResource(R.drawable.submit);
        submit.setTextColor(Color.parseColor("#FFFFFF"));

        mopta.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                erase();
                mopta.setBackgroundResource(R.drawable.answerchoiceselected);
                mopta.setTextColor(Color.parseColor("#FFFFFF"));
                answerChoice = "optiona";
            }
        });

        moptb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                erase();
                moptb.setBackgroundResource(R.drawable.answerchoiceselected);
                moptb.setTextColor(Color.parseColor("#FFFFFF"));
                answerChoice = "optionb";
            }
        });

        moptc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                erase();
                moptc.setBackgroundResource(R.drawable.answerchoiceselected);
                moptc.setTextColor(Color.parseColor("#FFFFFF"));
                answerChoice = "optionc";
            }
        });

        moptd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                erase();
                moptd.setBackgroundResource(R.drawable.answerchoiceselected);
                moptd.setTextColor(Color.parseColor("#FFFFFF"));
                answerChoice = "optiond";
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!answerChoice.equals("null")) {
                    Intent i;
                    if (answerChoice.equals(correctAnswer)) {
                        i = new Intent(MathWritingReadingQuestion.this, UniversalCorrect.class);
                        i.putExtra("explanation", explanation);
                        i.putExtra("whatami", whatami);
                    } else {
                        i = new Intent(MathWritingReadingQuestion.this, UniversalIncorrect.class);
                        i.putExtra("whatami", whatami);
                    }
                    startActivity(i);
                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(MathWritingReadingQuestion.this).create();
                    alertDialog.setTitle("SAT Daily!");
                    alertDialog.setMessage("Please select an answer choice before clicking submit again.");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Okay!",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
            }
        });
    }

    public void backFix() {

    }

    public void erase() {

        answerChoice = "null";

        final Button mopta = findViewById(R.id.mathoptiona);
        final Button moptb = findViewById(R.id.mathoptionb);
        final Button moptc = findViewById(R.id.mathoptionc);
        final Button moptd = findViewById(R.id.mathoptiond);
        final Button submit = findViewById(R.id.submit);

        mopta.setBackgroundResource(R.drawable.answerchoice);
        mopta.setTextColor(Color.BLACK);
        moptb.setBackgroundResource(R.drawable.answerchoice);
        moptb.setTextColor(Color.BLACK);
        moptc.setBackgroundResource(R.drawable.answerchoice);
        moptc.setTextColor(Color.BLACK);
        moptd.setBackgroundResource(R.drawable.answerchoice);
        moptd.setTextColor(Color.BLACK);
    }

    public void firebaseCallsAndSetsMath() {
        DocumentReference docRef = db.collection("math").document(questionID);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    assert document != null;
                    if (document.exists()) {
                        List<String> question = (List<String>) document.get("information");
                        assert question != null;
                        final TextView mquestion = findViewById(R.id.mathquestion);
                        final Button mopta = findViewById(R.id.mathoptiona);
                        final Button moptb = findViewById(R.id.mathoptionb);
                        final Button moptc = findViewById(R.id.mathoptionc);
                        final Button moptd = findViewById(R.id.mathoptiond);
                        mquestion.setText(question.get(0));
                        mopta.setText(question.get(1));
                        moptb.setText(question.get(2));
                        moptc.setText(question.get(3));
                        moptd.setText(question.get(4));
                        correctAnswer = (String)question.get(5);
                        explanation = (String)question.get(6);
                    }
                }
            }
        });
    }

    public void firebaseCallsAndSetsWriting() {
        DocumentReference docRef = db.collection("writing").document(questionID);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    assert document != null;
                    if (document.exists()) {
                        List<String> question = (List<String>) document.get("information");
                        assert question != null;
                        final TextView mquestion = findViewById(R.id.mathquestion);
                        final Button mopta = findViewById(R.id.mathoptiona);
                        final Button moptb = findViewById(R.id.mathoptionb);
                        final Button moptc = findViewById(R.id.mathoptionc);
                        final Button moptd = findViewById(R.id.mathoptiond);

                        mquestion.setText((question.get(0) + " [" + question.get(1) + "] " + question.get(2) + "\n\nWhat could replace the phrase in between the brackets?"));
                        mopta.setText(question.get(3));
                        moptb.setText(question.get(4));
                        moptc.setText(question.get(5));
                        moptd.setText(question.get(6));
                        correctAnswer = (String)question.get(7);
                        explanation = (String)question.get(8);
                    }
                }
            }
        });
    }

    public void firebaseCallsAndSetsReading() {
        DocumentReference docRef = db.collection("reading").document(questionID);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    assert document != null;
                    if (document.exists()) {
                        List<String> question = (List<String>) document.get("information");
                        assert question != null;
                        final TextView mquestion = findViewById(R.id.mathquestion);
                        final Button mopta = findViewById(R.id.mathoptiona);
                        final Button moptb = findViewById(R.id.mathoptionb);
                        final Button moptc = findViewById(R.id.mathoptionc);
                        final Button moptd = findViewById(R.id.mathoptiond);

                        mquestion.setText(question.get(3) + "\n\n" + question.get(0) + "\nby " + question.get(1) + "\n\n" + question.get(2));
                        mopta.setText(question.get(4));
                        moptb.setText(question.get(5));
                        moptc.setText(question.get(6));
                        moptd.setText(question.get(7));
                        correctAnswer = (String)question.get(8);
                        explanation = (String)question.get(9);
                    }
                }
            }
        });
    }

}