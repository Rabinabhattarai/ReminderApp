package com.example.hp.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.myapplication.database.DatabaseHelper;
import com.example.hp.myapplication.model.Bmi;


public class healthfrstpage extends AppCompatActivity {
    Button calculatem, save, htips, showdiet;
    TextView showbmi;
    EditText heightm, weightm;
    DatabaseHelper databaseHelper;
    Double bmval;
    public Bmi bmi;


    private String mEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthfrstpage);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            finish();
        }

        mEmail = extras.getString("EMAIL");

        calculatem = (Button) findViewById(R.id.calculate);
        databaseHelper = new DatabaseHelper(this);
        calculatem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculatebmi();

            }
        });
        save = (Button) findViewById(R.id.savebmi);
        showdiet= (Button) findViewById(R.id.seediet);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savebmi();
            }
        });
        showdiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdietinfo();
            }
        });
    }

    public void calculatebmi() {
        heightm = (EditText) findViewById(R.id.height);
        weightm = (EditText) findViewById(R.id.weight);
        if (!heightm.getText().toString().equals("") || !weightm.getText().toString().equals("")) {
            double gh = Double.parseDouble(heightm.getText().toString());
            double wh = Double.parseDouble(weightm.getText().toString());
            double cal = ( wh / (gh * gh));
            showbmi = (TextView) findViewById(R.id.showbmi);
            String finalresult = new Double(cal).toString();
            showbmi.setText(finalresult);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Hello Javatpoint", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    public void savebmi() {
        String uid;
        databaseHelper = new DatabaseHelper(this);
        showbmi = (TextView) findViewById(R.id.showbmi);
        String bmivalue = showbmi.getText().toString().trim();

        if (!bmivalue.equals("")) {
            bmval = Double.valueOf(bmivalue).doubleValue();

            databaseHelper.addbmi(bmval, mEmail);
            Toast.makeText(getApplicationContext(), "Values saved succesfully", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(getApplicationContext(), "Failed to save bmi", Toast.LENGTH_LONG).show();

        }
    }

    public void showdietinfo()
    {
        databaseHelper= new DatabaseHelper(this);
        if (databaseHelper.fordiet(mEmail)<=18.5){
           Intent intent = new Intent(healthfrstpage.this, UnderWeightDietPlanLayout.class);
         startActivity(intent);
        }
        else if (databaseHelper.fordiet(mEmail)>18.5 && databaseHelper.fordiet(mEmail)<24.9)
        {
            Intent intent = new Intent(healthfrstpage.this, NormalWeightDietLayout.class);
            startActivity(intent);
        }
        else if (databaseHelper.fordiet(mEmail)>24.9 && databaseHelper.fordiet(mEmail)<29.9)
        {
            Intent intent = new Intent(healthfrstpage.this, OverWeightDietLayout.class);
            startActivity(intent);
        }
        else  if (databaseHelper.fordiet(mEmail)>=30)
        {
            Intent intent = new Intent(healthfrstpage.this, ObeseWeightDietLayout.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(getApplicationContext(), "Wrong BMI!", Toast.LENGTH_LONG).show();
        }


    }
    public void showhealthtips()
    {}
}
