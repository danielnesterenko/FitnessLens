package com.nesterenko.fitnesslens.Fragments;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com.nesterenko.fitnesslens.EnterWorkoutName;
import com.nesterenko.fitnesslens.R;
import com.nesterenko.fitnesslens.RecyclerViews.RV_WorkoutClass;
import com.nesterenko.fitnesslens.RecyclerViews.RecyclerViewAdapterWorkout;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class WorkoutFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList<RV_WorkoutClass> workoutList = new ArrayList<>();

    public WorkoutFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_workout, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toolbar toolbar_workout = (Toolbar) view.findViewById(R.id.toolbar_workout);
        File workoutRecords = new File(getContext().getFilesDir(), "workoutRecords");
        Menu menu = toolbar_workout.getMenu();

        //Codeblock used for developing when in need to clear workoutRecords.txt
/*        try {
            PrintWriter temp = new PrintWriter(workoutRecords);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/

        recyclerView = view.findViewById(R.id.rv_workout);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new RecyclerViewAdapterWorkout(workoutList, getContext());
        recyclerView.setAdapter(mAdapter);

        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(workoutRecords));
            RV_WorkoutClass p1 = (RV_WorkoutClass) in.readObject();
            workoutList.add(new RV_WorkoutClass(p1.getWorkoutName(), p1.getLifted()));
            mAdapter.notifyDataSetChanged();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        ActivityResultLauncher<Intent> getWorkoutData = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getData() != null && result.getResultCode() == RESULT_OK && result.getData().getStringExtra("name") != null) {
                    String exerciseName = result.getData().getStringExtra("name");
                    double liftedInTotal = result.getData().getDoubleExtra("lifted", 0.2);
                    if (workoutList.isEmpty()) {
                        recyclerView = view.findViewById(R.id.rv_workout);
                        recyclerView.setHasFixedSize(true);
                        layoutManager = new LinearLayoutManager(getContext());
                        recyclerView.setLayoutManager(layoutManager);
                        mAdapter = new RecyclerViewAdapterWorkout(workoutList, getContext());
                        recyclerView.setAdapter(mAdapter);
                        workoutList.add(new RV_WorkoutClass(exerciseName, Double.toString(liftedInTotal)));
                        mAdapter.notifyDataSetChanged();
                        try {
                            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(workoutRecords));
                            for (RV_WorkoutClass object : workoutList) {
                                out.writeObject(object);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        workoutList.add(new RV_WorkoutClass(exerciseName, Double.toString(liftedInTotal)));
                        mAdapter.notifyDataSetChanged();
                        try {
                            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(workoutRecords));
                            for (RV_WorkoutClass object : workoutList) {
                                out.writeObject(object);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });


        view.findViewById(R.id.toolbar_workout_addWorkout).setOnClickListener(btnView -> {
            Intent goToWorkoutName  = new Intent(getActivity(), EnterWorkoutName.class);
            getWorkoutData.launch(goToWorkoutName);
        });

    }

}