package com.nesterenko.fitnesslens;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class Workout extends AppCompatActivity {

    ArrayList<RV_ExerciseClass> exerciseList = new ArrayList<RV_ExerciseClass>();

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    ActivityResultLauncher<Intent> getExerciseData = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getData() != null && result.getResultCode() == RESULT_OK && result.getData().getStringExtra("name") != null) {
                String exerciseName = result.getData().getStringExtra("name");
                double liftedInTotal = result.getData().getDoubleExtra("lifted", 0.2);
                if (exerciseList.isEmpty()) {
                    recyclerView = findViewById(R.id.rv_exercise);
                    recyclerView.setHasFixedSize(true);
                    layoutManager = new LinearLayoutManager(Workout.this);
                    recyclerView.setLayoutManager(layoutManager);
                    mAdapter = new RecyclerViewAdapterExercise(exerciseList, Workout.this);
                    recyclerView.setAdapter(mAdapter);
                    exerciseList.add(new RV_ExerciseClass(exerciseName, Double.toString(liftedInTotal)));
                    mAdapter.notifyDataSetChanged();
                } else {
                    exerciseList.add(new RV_ExerciseClass(exerciseName, Double.toString(liftedInTotal)));
                    mAdapter.notifyDataSetChanged();
                }
            }
        }
    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        getSupportActionBar().hide();
        TextView workoutHeader = findViewById(R.id.workoutHeader);
        workoutHeader.setText(getIntent().getStringExtra("workoutName"));


        recyclerView = findViewById(R.id.rv_exercise);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(Workout.this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new RecyclerViewAdapterExercise(exerciseList, this);
        recyclerView.setAdapter(mAdapter);


        findViewById(R.id.toolbar_workout_back).setOnClickListener(view -> {
            finish();
        });

        findViewById(R.id.button_exerciseAdd).setOnClickListener(view -> {
            Intent goToExerciseName  = new Intent(Workout.this, EnterExerciseName.class);
            getExerciseData.launch(goToExerciseName);

        });
    }
}