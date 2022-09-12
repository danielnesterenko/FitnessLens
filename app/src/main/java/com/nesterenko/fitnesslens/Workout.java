package com.nesterenko.fitnesslens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Workout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        getSupportActionBar().hide();
        TextView workoutHeader = findViewById(R.id.workoutHeader);
        workoutHeader.setText(getIntent().getStringExtra("workoutName"));

        findViewById(R.id.toolbar_workout_back).setOnClickListener(view -> {
            finish();
        });

        findViewById(R.id.button_exerciseAdd).setOnClickListener(view -> {
            Intent goToExerciseName  = new Intent(Workout.this, EnterExerciseName.class);
            startActivity(goToExerciseName);
        });
    }
}