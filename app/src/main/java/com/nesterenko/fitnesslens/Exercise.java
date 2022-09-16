package com.nesterenko.fitnesslens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


import java.util.ArrayList;

public class Exercise extends AppCompatActivity {

    ArrayList<RV_SetClass> exercises = new ArrayList<RV_SetClass>();

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        getSupportActionBar().hide();
        ImageButton finishExercise = findViewById(R.id.toolbar_workout_finishExercise);
        ImageButton addSet = findViewById(R.id.button_addSet);
        TextView exerciseHeader = findViewById(R.id.exerciseHeader);
        exerciseHeader.setText(getIntent().getStringExtra("exerciseName"));

        findViewById(R.id.toolbar_exercise_back).setOnClickListener(view -> {
            finish();
        });


        addSet.setOnClickListener(view -> {
            if (exercises.isEmpty()) {
                recyclerView = findViewById(R.id.rv_exercise);
                recyclerView.setHasFixedSize(true);
                layoutManager = new LinearLayoutManager(Exercise.this);
                recyclerView.setLayoutManager(layoutManager);
                mAdapter = new RecyclerViewAdapterSet(exercises, this);
                recyclerView.setAdapter(mAdapter);
                exercises.add(new RV_SetClass());
                mAdapter.notifyDataSetChanged();
            } else {
                exercises.add(new RV_SetClass());
                mAdapter.notifyDataSetChanged();
            }
        });


        finishExercise.setOnClickListener(view -> {
            String enteredName = exerciseHeader.getText().toString();


            TextView editRep = findViewById(R.id.edit_rep);
            TextView editWeight = findViewById(R.id.edit_weight);
            double liftedInTotal = 0;
            for (int i = 0; i < exercises.size(); i++) {
                exercises.get(i).setRep(Integer.parseInt(editRep.getText().toString()));
                exercises.get(i).setWeight(Double.parseDouble(editWeight.getText().toString()));
                double weight = exercises.get(i).getWeight();
                int rep = exercises.get(i).getRep();
                liftedInTotal += (weight * rep);
            }

            Intent intent = new Intent();
            intent.putExtra("name", enteredName);
            intent.putExtra("lifted", liftedInTotal);
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}