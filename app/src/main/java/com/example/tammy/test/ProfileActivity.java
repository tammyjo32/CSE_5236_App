package com.example.tammy.test;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

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
        final String email = intent.getStringExtra("Email");
        String name = helper.getFirstName(email);
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText("Hello, "+name);
        textView.setTextColor(Color.parseColor("#FFFFFF"));


        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_profile);
        layout.addView(textView, 0);

        final EditText bioText = (EditText)findViewById(R.id.bio);
        final EditText linkedInText = (EditText)findViewById(R.id.linkedIn);
        final EditText twitterText = (EditText)findViewById(R.id.twitter);
        final EditText emailText = (EditText)findViewById(R.id.email);

        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        bioText.setText(prefs.getString(email+"bioText", "This is where you can enter a bio about yourself! Just click to edit!"));
        linkedInText.setText(prefs.getString(email+"linkedInText", "LinkedIn profile link!"));
        twitterText.setText(prefs.getString(email+"twitterText", "Twitter profile link!"));
        emailText.setText(prefs.getString(email+"emailText", "Personal email address!"));

        //save bio
        bioText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count)
            {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after)
            {
            }

            @Override
            public void afterTextChanged(Editable s)
            {
                prefs.edit().putString(email+"bioText", s.toString()).apply();
            }
        });

        //save twitter link
        twitterText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count)
            {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after)
            {
            }

            @Override
            public void afterTextChanged(Editable s)
            {
                prefs.edit().putString(email+"twitterText", s.toString()).apply();
            }
        });

        //save linkedin link
        linkedInText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count)
            {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after)
            {
            }

            @Override
            public void afterTextChanged(Editable s)
            {
                prefs.edit().putString(email+"linkedInText", s.toString()).apply();
            }
        });

        //save personal email
        emailText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count)
            {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after)
            {
            }

            @Override
            public void afterTextChanged(Editable s)
            {
                prefs.edit().putString(email+"emailText", s.toString()).apply();
            }
        });

        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());

        if(sharedPreferences.getBoolean(email+"notificationSwitchStatus", true)){

            twitterText.setVisibility(twitterText.INVISIBLE);
            linkedInText.setVisibility(linkedInText.INVISIBLE);
            emailText.setVisibility(emailText.INVISIBLE);
        }



    }

}
