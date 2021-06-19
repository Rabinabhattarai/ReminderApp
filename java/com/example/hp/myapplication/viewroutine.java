package com.example.hp.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.hp.myapplication.Adapter.RoutineRecycleadapter;
import com.example.hp.myapplication.database.DatabaseHelper;
import com.example.hp.myapplication.model.routine;

import java.util.ArrayList;

public class viewroutine extends AppCompatActivity {
    private String mEmail;
    RecyclerView rv;
    RoutineRecycleadapter adapter;
    ArrayList<routine> routines = new ArrayList<>();

    private DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewroutine);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            finish();
        }

        mEmail = extras.getString("EMAIL");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(), newroutine.class);
                intent.putExtra("EMAIL", mEmail);
                startActivity(intent);
            }
        });
        db = new DatabaseHelper(this);

        //recycler
        rv = (RecyclerView) findViewById(R.id.myRecyclerRoutine);

        //SET ITS PROPS
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());

        //ADAPTER
        adapter = new RoutineRecycleadapter(this);
        rv.setAdapter(adapter);

        loadData();

    }

    private void loadData() {
        routines = db.getroutineDetails(mEmail);
        adapter.setData(routines);
    }


}
