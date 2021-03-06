package com.example.tammy.test.displayMenu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.tammy.test.R;
import com.example.tammy.test.geofence.AllGeofencesActivity;


public class CheckInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);

        final TextView classList = (TextView) findViewById(R.id.classList);
        final TextView instructingList = (TextView) findViewById(R.id.instructingList);

        setTitle("Check-In");

        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());

        classList.setText(sharedPreferences.getString(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("EmailAddr", "defaultStringIfNothingFound")+"checkInClass", "You are not in any classes!"));
        instructingList.setText(sharedPreferences.getString(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("EmailAddr", "defaultStringIfNothingFound")+"checkInInstruct", "You are not instructing any classes!"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_checkin, menu);
        return true;
    }

    public void editPage(MenuItem item) {
        Intent intent = new Intent(this, AllGeofencesActivity.class);
        startActivity(intent);
    }
}
