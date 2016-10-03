package com.example.tammy.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.example.tammy.EMAIL";
    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void signIn(View view){
            EditText editText = (EditText)findViewById(R.id.email);
            String email = editText.getText().toString();
            EditText editText1 = (EditText)findViewById(R.id.password);
            String pass = editText1.getText().toString();

            String password = helper.searchPass(email);


            if(pass.equals(password))
            {
                Intent intent = new Intent(this, DisplayActivity.class);
                intent.putExtra("Email", email);
                startActivity(intent);
            }
            else
            {
                Toast temp = Toast.makeText(MainActivity.this, "Email and Password don't match!" , Toast.LENGTH_SHORT);
                temp.show();
            }

    }

    public void signUp(View view){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    public void forgotPassword(View view) {
    }
}
