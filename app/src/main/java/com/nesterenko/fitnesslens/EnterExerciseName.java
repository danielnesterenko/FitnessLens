package com.nesterenko.fitnesslens;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class EnterExerciseName extends AppCompatActivity {

/*    ActivityResultLauncher<Intent> getExerciseData = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getData() != null && result.getResultCode() == RESULT_OK && result.getData().getStringExtra("exerciseName") != null) {
                name.setText(result.getData().getStringExtra("name"));
            }
        }
    });*/


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
            if (!exerciseNameByEdit.getText().toString().isEmpty()) {
                Intent goToExercise = new Intent(EnterExerciseName.this, Exercise.class);
                goToExercise.putExtra("exerciseName", exerciseNameByEdit.getText().toString());
                startActivity(goToExercise);
            }
        });
    }
}