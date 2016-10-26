package com.example.tammy.test;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CheckInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);

        Intent intent = getIntent();
        final String email = intent.getStringExtra("Email");

        final TextView classList = (TextView) findViewById(R.id.classList);
        final TextView instructingList = (TextView) findViewById(R.id.instructingList);

        setTitle("Check-In");

        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());

        classList.setText(sharedPreferences.getString(email+"checkInClass", "You are not in any classes!"));
        instructingList.setText(sharedPreferences.getString(email+"checkInInstruct", "You are not instructing any classes!"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_checkin, menu);
        return true;
    }

    public void editPage(MenuItem item) {
    }
}
