package com.example.hp.myapplication;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hp.myapplication.database.DatabaseHelper;

import java.util.Calendar;


public class newroutine extends AppCompatActivity implements View.OnClickListener {
    EditText mname, mdate, mtask;
    Button datepickerbutton, svae;
    private int rYear, rMonth, rDay, rdayofweek;
    DatabaseHelper databaseHelper;
    private String memail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseHelper= new DatabaseHelper(this);
        setContentView(R.layout.activity_newroutine);
        mname = (EditText) findViewById(R.id.namenr);
        mdate = (EditText) findViewById(R.id.dateroutine);
        mtask = (EditText) findViewById(R.id.routinetask);
        datepickerbutton = (Button) findViewById(R.id.datepickroutine);
        svae = (Button) findViewById(R.id.saveroutine);
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            finish();
        }

        memail = extras.getString("EMAIL");
        svae.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addroutine();
            }
        });
        datepickerbutton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        final Calendar c = Calendar.getInstance();
        rYear = c.get(Calendar.YEAR);
        rMonth = c.get(Calendar.MONTH);
        rDay = c.get(Calendar.DAY_OF_MONTH);
        rdayofweek = c.get(Calendar.DAY_OF_WEEK);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        mdate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, rYear, rMonth, rDay);
        datePickerDialog.show();

    }
    public void addroutine()
    {
        databaseHelper= new DatabaseHelper(this);
     String name = mname.getText().toString();
     String datev = mdate.getText().toString();
//        SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date dateval = null;
//        try {
//            dateval = newFormat.parse(datev);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }


     String taskv = mtask.getText().toString();

        if (!name.equals("") && !datev.equals("") && !taskv.equals("") ) {
            databaseHelper.addroutine(name, datev, taskv, memail);
            Toast.makeText(getApplicationContext(), "Values saved sucessfully", Toast.LENGTH_LONG).show();

        }
        else {
            Toast.makeText(getApplicationContext(), "Please fill all the fields", Toast.LENGTH_LONG).show();

        }


    }

}
