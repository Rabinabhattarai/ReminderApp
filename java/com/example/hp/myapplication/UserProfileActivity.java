package com.example.hp.myapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.hp.myapplication.database.DatabaseHelper;

public class UserProfileActivity extends AppCompatActivity {

    private static final String TAG = UserProfileActivity.class.getName();

    private TextView mShowEmailTextView;
    private TextView mShowUsernameTextView;
    private TextView mShowUidTextView;
    private TextView mShowBMITextView;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);

        mShowEmailTextView = findViewById(R.id.Emaillogin);
        mShowUsernameTextView = findViewById(R.id.unameshow);
        mShowUidTextView = findViewById(R.id.showuid);
        mShowBMITextView = findViewById(R.id.showbmi);

        databaseHelper = new DatabaseHelper(this);
        loadFromDB();
        showemail();
        uidfech();
        bmifech();
    }

    private void loadFromDB() {
        Log.d(TAG, "loadFromDB");


    }

    public void showemail() {
        mShowEmailTextView = findViewById(R.id.Emaillogin);
        String emailIntent = getIntent().getStringExtra("EMAIL");
        mShowEmailTextView.setText(emailIntent);

    }

    public void uidfech() {
        String femail;
        femail = mShowEmailTextView.getText().toString();
        Cursor fechuid = databaseHelper.checkUseragain(femail);
        fechuid.moveToFirst();

        while (!fechuid.isAfterLast()) {

            String getuid = fechuid.getString(0);
            String getuname = fechuid.getString(1);
            mShowUidTextView.setText(getuid);
            mShowUsernameTextView.setText(getuname);
            fechuid.moveToNext();

        }
    }

    public void bmifech() {
        mShowUidTextView = findViewById(R.id.showuid);
        mShowBMITextView = findViewById(R.id.showbmi);
        String uid = mShowUidTextView.getText().toString();
        double fetchbmi = databaseHelper.fetchbmi(uid);
        String val = fetchbmi == -1 ? "" : String.valueOf(fetchbmi);
        mShowBMITextView.setText(val);
    }
}


