package com.nesterenko.fitnesslens;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class EnterWorkoutName extends AppCompatActivity {

    ActivityResultLauncher<Intent> getExerciseData = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getData() != null && result.getResultCode() == RESULT_OK && result.getData().getStringExtra("name") != null) {
                String enteredName = result.getData().getStringExtra("name");
                double liftedInTotal = result.getData().getDoubleExtra("lifted", 0);
                Intent intent = new Intent();
                intent.putExtra("name", enteredName);
                intent.putExtra("lifted", liftedInTotal);
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_workout_name);
        getSupportActionBar().hide();
        TextView workoutNameByEdit = findViewById(R.id.edit_workoutName);

        findViewById(R.id.button_enterWorkoutAdd).setOnClickListener(view -> {
            if (!workoutNameByEdit.getText().toString().isEmpty()){
                Intent goToWorkout = new Intent(EnterWorkoutName.this, Workout.class);
                goToWorkout.putExtra("workoutName", workoutNameByEdit.getText().toString());
                getExerciseData.launch(goToWorkout);
            }
        });

        findViewById(R.id.button_enterWorkoutCancel).setOnClickListener(view -> {
            finish();
        });
    }
}