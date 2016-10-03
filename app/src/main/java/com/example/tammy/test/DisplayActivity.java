package com.example.tammy.test;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

public class DisplayActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_display);

        Intent intent = getIntent();
        String email = intent.getStringExtra("Email");
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText("Hello, "+email);
        textView.setTextColor(Color.parseColor("#FFFFFF"));

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_display);
        layout.addView(textView);
    }
}
