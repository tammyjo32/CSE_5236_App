package com.example.tammy.test.displayMenu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import com.example.tammy.test.R;

public class SettingsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        setTitle("Settings");

        final Switch accountSwitch;
        final Switch notificationSwitch;
        accountSwitch = (Switch) findViewById(R.id.media_sites_settings);
        notificationSwitch = (Switch) findViewById(R.id.discussion_notification_settings);
        accountSwitch.setOnClickListener(new Switch.OnClickListener() {

            public void onClick(View v) {
                SharedPreferences sharedPreferences = PreferenceManager
                        .getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("EmailAddr", "defaultStringIfNothingFound")+"accountSwitchStatus", accountSwitch.isChecked());
                editor.apply();
            }
        });

        notificationSwitch.setOnClickListener(new Switch.OnClickListener() {

            public void onClick(View v) {
                SharedPreferences sharedPreferences = PreferenceManager
                        .getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("EmailAddr", "defaultStringIfNothingFound")+"notificationSwitchStatus", notificationSwitch.isChecked());
                editor.apply();
            }
        });

        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());
        accountSwitch.setChecked(sharedPreferences.getBoolean(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("EmailAddr", "defaultStringIfNothingFound")+"accountSwitchStatus", false));  //default is false
        notificationSwitch.setChecked(sharedPreferences.getBoolean(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("EmailAddr", "defaultStringIfNothingFound")+"notificationSwitchStatus", false));

    }
}
