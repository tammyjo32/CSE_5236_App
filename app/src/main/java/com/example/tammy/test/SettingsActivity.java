package com.example.tammy.test;

import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity {

    private Switch accountSwitch;
    private Switch notificationSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        accountSwitch = (Switch)findViewById(R.id.media_sites_settings);
        notificationSwitch = (Switch)findViewById(R.id.discussion_notification_settings);

        boolean accountSwitchState = accountSwitch.isChecked();
        boolean notificationSwitchState = notificationSwitch.isChecked();

        setTitle("Settings");
    }
}
