package com.nesterenko.fitnesslens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class EnterExerciseName extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_exercise_name);
        getSupportActionBar().hide();
        TextView exerciseNameByEdit = findViewById(R.id.edit_ExerciseName);

        findViewById(R.id.button_enterExerciseCancel).setOnClickListener(view -> {
            finish();
        });

        findViewById(R.id.button_enterExerciseAdd).setOnClickListener(view -> {
            Intent goToExercise = new Intent(EnterExerciseName.this, Exercise.class);
            goToExercise.putExtra("exerciseName", exerciseNameByEdit.getText().toString());
            startActivity(goToExercise);
        });
    }
}