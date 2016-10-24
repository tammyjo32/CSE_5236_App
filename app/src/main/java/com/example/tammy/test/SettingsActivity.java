package com.example.tammy.test;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    private TextView switchStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        setTitle("Settings");

        Intent intent = getIntent();
        final String email = intent.getStringExtra("Email");

        final Switch accountSwitch;
        final Switch notificationSwitch;
        Switch mySwitch;
        accountSwitch = (Switch) findViewById(R.id.media_sites_settings);
        notificationSwitch = (Switch) findViewById(R.id.discussion_notification_settings);
        accountSwitch.setOnClickListener(new Switch.OnClickListener() {

            public void onClick(View v) {
                SharedPreferences sharedPreferences = PreferenceManager
                        .getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(email+"accountSwitchStatus", accountSwitch.isChecked());
                editor.commit();
            }
        });

        notificationSwitch.setOnClickListener(new Switch.OnClickListener() {

            public void onClick(View v) {
                SharedPreferences sharedPreferences = PreferenceManager
                        .getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(email+"notificationSwitchStatus", notificationSwitch.isChecked());
                editor.commit();
            }
        });

        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());
        accountSwitch.setChecked(sharedPreferences.getBoolean("accountSwitchStatus", false));  //default is false
        notificationSwitch.setChecked(sharedPreferences.getBoolean("notificationSwitchStatus", false));

    }
}
