package com.nesterenko.fitnesslens;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapterWorkout extends RecyclerView.Adapter<RecyclerViewAdapterWorkout.MyViewHolder> {

    List<RV_WorkoutClass> RVWorkoutClassList;
    Context context;

    public RecyclerViewAdapterWorkout(List<RV_WorkoutClass> RVWorkoutClassList, Context context) {
        this.RVWorkoutClassList = RVWorkoutClassList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_workout_layout, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.workoutName.setText(RVWorkoutClassList.get(position).getWorkoutName());
        holder.lifted.setText(RVWorkoutClassList.get(position).getLifted());
        holder.staticKG.setText(holder.staticKG.getText());
    }

    @Override
    public int getItemCount() {
        return RVWorkoutClassList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView workoutName;
        TextView lifted;
        TextView staticKG;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            workoutName = itemView.findViewById(R.id.tv_workoutName);
            lifted = itemView.findViewById(R.id.tv_workoutLifted);
            staticKG = itemView.findViewById(R.id.tv_staticKG);
        }
    }
}