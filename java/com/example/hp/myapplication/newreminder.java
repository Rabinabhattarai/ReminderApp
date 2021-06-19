package com.example.hp.myapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.hp.myapplication.database.DatabaseHelper;

import java.util.Calendar;

public class newreminder extends AppCompatActivity implements
        View.OnClickListener {
    EditText txtDate, txtTime, txtcomment;
    Button datep, timep, save;
    private int mYear, mMonth, mDay, mHour, mMinute;
    Spinner spinneralarmtype;
    DatabaseHelper databaseHelper;
    private String mEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newreminder);

        databaseHelper = new DatabaseHelper(this);
        txtDate = (EditText) findViewById(R.id.remdate);
        txtTime = (EditText) findViewById(R.id.remtime);
        datep = (Button) findViewById(R.id.datepick);
        timep = (Button) findViewById(R.id.timepick);
        txtcomment = (EditText) findViewById(R.id.remcom);
        save = (Button) findViewById(R.id.savereminder);
        datep.setOnClickListener(this);
        timep.setOnClickListener(this);
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            finish();
        }

        mEmail = extras.getString("EMAIL");

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertreminer();

            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == datep) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (view == timep) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            txtTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }

    public void insertreminer() {
        String date = txtDate.getText().toString();
        Log.d("Date", date);
//        SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date dateval = null;
//        try {
//            dateval = newFormat.parse(date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        String time = txtTime.getText().toString();
//        DateFormat sdf = new SimpleDateFormat("hh:mm:ss");
//        Date timeval = null;
//        try {
//            timeval = sdf.parse(time);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }


        String comment = txtcomment.getText().toString();
        if (!date.equals("") && !time.equals("") && !comment.equals("") ) {
            databaseHelper.addreminder(date, time, comment, mEmail);
            Toast.makeText(getApplicationContext(), "Values saved succesfully", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(getApplicationContext(), "Please fill all the fields", Toast.LENGTH_LONG).show();

        }
    }

}



