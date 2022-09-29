package com.nesterenko.fitnesslens.RecyclerViews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nesterenko.fitnesslens.R;

import java.util.List;

public class RecyclerViewAdapterExercise extends RecyclerView.Adapter<RecyclerViewAdapterExercise.MyViewHolder> {

    List<RV_ExerciseClass> RVExerciseClassList;
    Context context;

    public RecyclerViewAdapterExercise(List<RV_ExerciseClass> RVExerciseClassList, Context context) {
        this.RVExerciseClassList = RVExerciseClassList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_exercise_layout, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.exerciseNamePrefix.setText(holder.exerciseNamePrefix.getText());
        holder.exerciseName.setText(RVExerciseClassList.get(position).getExerciseName());
        holder.liftedPrefix.setText(holder.liftedPrefix.getText());
        holder.lifted.setText(RVExerciseClassList.get(position).getLifted());
        holder.liftedSuffix.setText(holder.liftedSuffix.getText());
    }

    @Override
    public int getItemCount() {
        return RVExerciseClassList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView exerciseNamePrefix;
        TextView exerciseName;
        TextView liftedPrefix;
        TextView lifted;
        TextView liftedSuffix;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            exerciseNamePrefix = itemView.findViewById(R.id.tv_exerciseNamePrefix);
            exerciseName = itemView.findViewById(R.id.tv_exerciseName);
            liftedPrefix = itemView.findViewById(R.id.tv_liftedPrefix);
            lifted = itemView.findViewById(R.id.tv_lifted);
            liftedSuffix = itemView.findViewById(R.id.tv_liftedSuffix);
        }
    }
}