package com.pranavb.satdailyandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class Other extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        graphics();
        recommendedResources();
        other();
    }

    public void graphics() {
        getWindow().getDecorView().setBackgroundColor(Color.parseColor("#E1E2E1"));
    }

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void recommendedResources() {
        SharedPreferences sh = getSharedPreferences("dailyids", MODE_APPEND);
        LinearLayout layout = (LinearLayout) findViewById(R.id.recommendedResourcesVStack);
        for(int i = 0; i < Integer.parseInt(sh.getString("resourcesnumber", "2")); i++) {
            Button btnTag = new Button(this);
            btnTag.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            btnTag.setText(sh.getString(String.valueOf(i), "2"));
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
            btnTag.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(sh.getString((String.valueOf(finalI))+"i", "2")));
                    startActivity(browserIntent);
                }
            });
        }
    }

    public void other() {
        final Button contact = findViewById(R.id.contact);
        final Button discord = findViewById(R.id.discord);
        contact.setBackgroundResource(R.drawable.answerchoice);
        discord.setBackgroundResource(R.drawable.answerchoice);

        contact.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://satdailyapp.com/support"));
                startActivity(browserIntent);
            }
        });
        discord.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://satdailyapp.com/discord"));
                startActivity(browserIntent);
            }
        });
    }
}