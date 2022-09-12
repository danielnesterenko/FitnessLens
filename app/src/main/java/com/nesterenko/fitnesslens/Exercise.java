package com.nesterenko.fitnesslens;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Exercise extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        getSupportActionBar().hide();
        TextView exerciseHeader = findViewById(R.id.exerciseHeader);
        exerciseHeader.setText(getIntent().getStringExtra("exerciseName"));

        findViewById(R.id.toolbar_exercise_back).setOnClickListener(view -> {
            finish();
        });
    }
}