package com.example.tammy.test.login_related;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;

import com.example.tammy.test.R;

/**
 * Created by Tammy on 10/3/16.
 */

public class PopUpTermsAndConditions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Terms & Conditions");
        setContentView(R.layout.pop_up_terms_and_conditions);

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
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
}
