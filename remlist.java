package com.example.hp.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.hp.myapplication.Adapter.RemindeRecycleAdapter;
import com.example.hp.myapplication.database.DatabaseHelper;
import com.example.hp.myapplication.model.Reminder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class remlist extends AppCompatActivity {
    RecyclerView rv;
    RemindeRecycleAdapter adapter;
    ArrayList<Reminder> reminders = new ArrayList<>();
    BroadcastReceiver broadcastReceiver;
    private DatabaseHelper db;
    private String sEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remlist);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        boolean alarm = (PendingIntent.getBroadcast(this, 0, new Intent("ALARM"), PendingIntent.FLAG_NO_CREATE) == null);
        db = new DatabaseHelper(this);
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            finish();
        }

        sEmail = extras.getString("EMAIL");
        //notifyme();
        //showtest();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), newreminder.class);
                intent.putExtra("EMAIL", sEmail);
                startActivity(intent);
            }
        });


        //recycler
        rv = (RecyclerView) findViewById(R.id.myRecyclerReminder);

        //SET ITS PROPS
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());

        //ADAPTER
        adapter = new RemindeRecycleAdapter(this);
        rv.setAdapter(adapter);

        loadData();

        if(alarm){
            Intent itAlarm = new Intent("ALARM");
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,itAlarm,0);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.add(Calendar.SECOND, 3);
            AlarmManager alarme = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarme.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),60000, pendingIntent);

        }
    }

    private void loadData() {
        reminders = db.getreminderDetails(sEmail);
        adapter.setData(reminders);
    }


    public String uidfech() {
          db = new DatabaseHelper(this);
        Cursor fechuid = db.checkUseragain(sEmail);
        fechuid.moveToFirst();

        while (!fechuid.isAfterLast()) {

            String getuid = fechuid.getString(0);
            fechuid.moveToNext();
            return getuid;
        }
        return null;
    }

    public void showtest() {
      Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        SimpleDateFormat df1 = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate1 = df1.format(c.getTime());

        Integer hour = c.get(Calendar.HOUR_OF_DAY);
        String hours = String.valueOf(hour);

        Integer minute = c.get(Calendar.MINUTE);
        String min = String.valueOf(minute);

        String timereal = hours + ":" + min;

        String show = String.valueOf(uidfech());
        Cursor fetch = db.checkUserfornotify(show);
        fetch.moveToFirst();

        while (!fetch.isAfterLast()) {

            String getdate = fetch.getString(0);
            String gettime = fetch.getString(1);
            String getcomment = fetch.getString(2);
            fetch.moveToNext();
             if (getdate.equals(formattedDate1) && gettime.equals(timereal))
             {
                 //scheduleNotification(getNotification(getcomment));

             }
        }
    }
}



