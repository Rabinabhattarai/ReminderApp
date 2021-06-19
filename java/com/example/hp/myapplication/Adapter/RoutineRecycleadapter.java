package com.example.hp.myapplication.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hp.myapplication.Holder.routineholder;
import com.example.hp.myapplication.R;
import com.example.hp.myapplication.model.routine;

import java.util.ArrayList;

public class RoutineRecycleadapter extends RecyclerView.Adapter<routineholder> {

    Context c;
    ArrayList<routine> routines;

    public RoutineRecycleadapter(Context ctx) {
        //ASSIGN THEM LOCALLY
        this.c = ctx;
        this.routines = new ArrayList<>();
    }
    @Override
    public routineholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_routine_model, null);

        //holder
        routineholder holder = new routineholder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull routineholder routineholder, int position) {
        routineholder.getRid().setText(String.valueOf(routines.get(position).getRoutineid()));
        routineholder.getRdate().setText(String.valueOf(routines.get(position).getRoutinedate()));
        routineholder.getRname().setText(String.valueOf(routines.get(position).getRoutinename()));
        routineholder.getRtask().setText(String.valueOf(routines.get(position).getRoutinetasktodo()));
    }


    @Override
    public int getItemCount() {
        return routines.size();
    }
    public void setData(ArrayList<routine> routines) {
        this.routines.clear();
        this.routines.addAll(routines);
        notifyDataSetChanged();
    }

}
