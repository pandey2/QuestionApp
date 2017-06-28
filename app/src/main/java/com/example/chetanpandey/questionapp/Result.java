package com.example.chetanpandey.questionapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Result extends AppCompatActivity {

    TextView score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        score= (TextView) findViewById(R.id.score);
        Bundle extras = getIntent().getExtras();
        String right_count;
        String wrong_count;
        right_count = extras.getString("right_count");
        wrong_count = extras.getString("wrong_count");
       score.setText(right_count)
       ;}
    }

