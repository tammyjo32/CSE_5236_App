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


        final EditText bioText = (EditText)findViewById(R.id.bio);
        final EditText linkedInText = (EditText)findViewById(R.id.linkedIn);
        final EditText twitterText = (EditText)findViewById(R.id.twitter);
        final EditText emailText = (EditText)findViewById(R.id.email);

        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        String name = helper.getFirstName(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("EmailAddr", "defaultStringIfNothingFound"));
        TextView greetingText = (TextView)findViewById(R.id.greetings);
        greetingText.setText("Hello, "+ name);

        bioText.setText(prefs.getString(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("EmailAddr", "defaultStringIfNothingFound")+"bioText", "This is where you can enter a bio about yourself! Just click to edit!"));
        linkedInText.setText(prefs.getString(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("EmailAddr", "defaultStringIfNothingFound")+"linkedInText", "LinkedIn profile link!"));
        twitterText.setText(prefs.getString(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("EmailAddr", "defaultStringIfNothingFound")+"twitterText", "Twitter profile link!"));
        emailText.setText(prefs.getString(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("EmailAddr", "defaultStringIfNothingFound")+"emailText", "Personal email address!"));

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
                prefs.edit().putString(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("EmailAddr", "defaultStringIfNothingFound")+"bioText", s.toString()).apply();
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
                prefs.edit().putString(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("EmailAddr", "defaultStringIfNothingFound")+"twitterText", s.toString()).apply();
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
                prefs.edit().putString(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("EmailAddr", "defaultStringIfNothingFound")+"linkedInText", s.toString()).apply();
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
                prefs.edit().putString(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("EmailAddr", "defaultStringIfNothingFound")+"emailText", s.toString()).apply();
            }
        });

        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());

        if(!sharedPreferences.getBoolean(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("EmailAddr", "defaultStringIfNothingFound")+"accountSwitchStatus", false)){

            twitterText.setVisibility(twitterText.INVISIBLE);
            linkedInText.setVisibility(twitterText.INVISIBLE);
            emailText.setVisibility(twitterText.INVISIBLE);

        } else {
            twitterText.setVisibility(twitterText.VISIBLE);
            linkedInText.setVisibility(twitterText.VISIBLE);
            emailText.setVisibility(twitterText.VISIBLE);
        }



    }

}
