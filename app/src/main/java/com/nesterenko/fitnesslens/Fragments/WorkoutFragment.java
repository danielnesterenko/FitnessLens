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

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WorkoutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WorkoutFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList<RV_WorkoutClass> workoutList = new ArrayList<>();


    public WorkoutFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WorkoutFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WorkoutFragment newInstance(String param1, String param2) {
        WorkoutFragment fragment = new WorkoutFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workout, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toolbar toolbar_workout = (Toolbar) view.findViewById(R.id.toolbar_workout);
        Menu menu = toolbar_workout.getMenu();


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
                    } else {
                        workoutList.add(new RV_WorkoutClass(exerciseName, Double.toString(liftedInTotal)));
                        mAdapter.notifyDataSetChanged();
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