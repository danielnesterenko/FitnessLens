package com.nesterenko.fitnesslens;

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
            //Intent goToExerciseName  = new Intent(Workout.this, EnterExerciseName.class);
            //startActivity(goToExerciseName);

            if (exerciseList.isEmpty()) {
                recyclerView = findViewById(R.id.rv_exercise);
                recyclerView.setHasFixedSize(true);
                layoutManager = new LinearLayoutManager(Workout.this);
                recyclerView.setLayoutManager(layoutManager);
                mAdapter = new RecyclerViewAdapterExercise(exerciseList, this);
                recyclerView.setAdapter(mAdapter);
                exerciseList.add(new RV_ExerciseClass());
                mAdapter.notifyDataSetChanged();
            } else {
                exerciseList.add(new RV_ExerciseClass());
                mAdapter.notifyDataSetChanged();
            }
        });
    }
}