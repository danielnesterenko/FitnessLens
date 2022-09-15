package com.nesterenko.fitnesslens;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    List<Exercises> exercisesList;
    Context context;

    public RecyclerViewAdapter(List<Exercises> exercisesList, Context context) {
        this.exercisesList = exercisesList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_set_layout, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_set.setText(holder.tv_set.getText());
        holder.tv_rep.setText(holder.tv_rep.getText());
        holder.tv_weight.setText(holder.tv_weight.getText());
    }

    @Override
    public int getItemCount() {
        return exercisesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_set;
        TextView tv_rep;
        TextView tv_weight;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_set = itemView.findViewById(R.id.tv_set);
            tv_rep = itemView.findViewById(R.id.tv_rep);
            tv_weight = itemView.findViewById(R.id.tv_weight);
        }
    }
}
