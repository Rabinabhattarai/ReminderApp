package com.example.hp.myapplication.Water.Dialouge.Setting;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.hp.myapplication.R;


public class SettingsActivity extends PreferenceActivity {

    static Toolbar bar ;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = (LinearLayout)findViewById(android.R.id.list).getParent().getParent().getParent();
        bar = (Toolbar) LayoutInflater.from(this).inflate(R.layout.settings_toolbar, root, false);
        root.addView(bar, 0); // insert at top
        Toast.makeText(getApplicationContext(),"ON",Toast.LENGTH_SHORT).show();
        bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(this).inflate(R.layout.settings_fragment, root, false);
        root.addView(frameLayout,1);
        getFragmentManager().beginTransaction().replace(R.id.content_frame, new FragmentPrefs()).commit();
    }
}
