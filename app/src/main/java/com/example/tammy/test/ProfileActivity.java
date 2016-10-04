package com.example.tammy.test;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    private ImageView profileImageView;
    private TextView usernameTextView;
    private TextView emailTextVIew;

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setTitle("Profile");

        Intent intent = getIntent();
        String email = intent.getStringExtra("Email");
        String name = helper.getFirstName(email);
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText("Hello, "+name);
        textView.setTextColor(Color.parseColor("#FFFFFF"));

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_profile);
        layout.addView(textView, 0);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void editPage(MenuItem item) {

    }
}
