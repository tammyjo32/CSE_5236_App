package com.example.tammy.test.displayMenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.tammy.test.R;
import com.example.tammy.test.geofence.AllGeofencesActivity;
import com.example.tammy.test.login_related.MainActivity;

public class DisplayActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_display);
    }

    public void profile (View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    public void checkIn (View view) {
        Intent intent = new Intent(this, CheckInActivity.class);
        startActivity(intent);
    }

    public void discussionBoard (View view) {
        Intent intent = new Intent(this, DiscussionActivity.class);
        startActivity(intent);
    }

    public void help (View view) {
        Intent intent = new Intent(this, HelpActivity.class);
        startActivity(intent);
    }

    public void settings (View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void signOut (View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}

