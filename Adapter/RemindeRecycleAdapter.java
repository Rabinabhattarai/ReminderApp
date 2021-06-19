package com.example.hp.myapplication.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hp.myapplication.Holder.Myholder;
import com.example.hp.myapplication.R;
import com.example.hp.myapplication.model.Reminder;

import java.util.ArrayList;

public class RemindeRecycleAdapter extends RecyclerView.Adapter<Myholder> {
    Context c;
    ArrayList<Reminder> reminenders;

    public RemindeRecycleAdapter(Context ctx) {
        //ASSIGN THEM LOCALLY
        this.c = ctx;
        this.reminenders = new ArrayList<>();
    }

    @Override
    public Myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        //VIEW OBJ FROM XML
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.reminder_viewmodel_layout, null);

        //holder
        Myholder holder = new Myholder(v);

        return holder;
    }

    //BIND DATA TO VIEWS
    @Override
    public void onBindViewHolder(Myholder myholder, int position) {

        myholder.getRemid().setText(String.valueOf(reminenders.get(position).getRemid()));
        myholder.getMdate().setText(String.valueOf(reminenders.get(position).getDate()));
        myholder.getMtime().setText(String.valueOf(reminenders.get(position).getTme()));
        myholder.getMcommnet().setText(reminenders.get(position).getComment());

    }

    @Override
    public int getItemCount() {
        return reminenders.size();
    }

    public void setData(ArrayList<Reminder> reminders) {
        this.reminenders.clear();
        this.reminenders.addAll(reminders);
        notifyDataSetChanged();
    }

}













































