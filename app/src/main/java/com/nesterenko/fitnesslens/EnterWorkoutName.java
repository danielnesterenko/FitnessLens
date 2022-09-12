package com.nesterenko.fitnesslens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class EnterWorkoutName extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_workout_name);
        getSupportActionBar().hide();
        TextView workoutNameByEdit = findViewById(R.id.edit_workoutName);

        findViewById(R.id.button_enterWorkoutAdd).setOnClickListener(view -> {
            Intent goToWorkout = new Intent(EnterWorkoutName.this, Workout.class);
            goToWorkout.putExtra("workoutName", workoutNameByEdit.getText().toString());
            startActivity(goToWorkout);
        });

        findViewById(R.id.button_enterWorkoutCancel).setOnClickListener(view -> {
            finish();
        });
    }
}