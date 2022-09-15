package com.nesterenko.fitnesslens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;

public class Exercise extends AppCompatActivity {

    ArrayList<Exercises> exercises = new ArrayList<Exercises>();

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

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


        findViewById(R.id.button_addSet).setOnClickListener(view -> {
            if (exercises.isEmpty()) {
                recyclerView = findViewById(R.id.rv_exercise);
                recyclerView.setHasFixedSize(true);
                layoutManager = new LinearLayoutManager(Exercise.this);
                recyclerView.setLayoutManager(layoutManager);
                mAdapter = new RecyclerViewAdapter(exercises, this);
                recyclerView.setAdapter(mAdapter);
                exercises.add(new Exercises());
                mAdapter.notifyDataSetChanged();
            } else {
                exercises.add(new Exercises());
                mAdapter.notifyDataSetChanged();
            }
        });
    }
}