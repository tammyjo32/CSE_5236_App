package com.example.tammy.test.login_related;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tammy.test.DataRelated.DatabaseHelper;
import com.example.tammy.test.R;

public class PopUpGetPassword extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Forgot Password?");
        setContentView(R.layout.pop_up_get_password);

        Intent intent = getIntent();
        String email = intent.getStringExtra("Email");
        String name = helper.getFirstName(email);
        String password = helper.getPassword(email);
        TextView textView = new TextView(this);
        textView.setTextSize(24);
        textView.setTextColor(Color.parseColor("#000000"));

        if(!password.equals("not found")){
            textView.setText("Hello, "+name+". It seems that you forgot your password! Good thing we got it saved for you in our handy dandy database! Your password is: "+password);
        }
        else{
            textView.setText("Uh oh! That email account is not registered!");
        }



        ViewGroup layout = (ViewGroup) findViewById(R.id.pop_up_get_password);
        layout.addView(textView, 0);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.8),(int)(height*0.8));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_exit_popup, menu);
        return true;
    }


    public void exitPage(MenuItem item) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }



}