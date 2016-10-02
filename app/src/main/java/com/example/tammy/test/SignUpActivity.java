package com.example.tammy.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);

    public final static String EXTRA_MESSAGE = "com.example.tammy.EMAIL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    /** Called when user clicks the sign up button */
    public void signUp(View view) {

        if(view.getId()==R.id.sign_up_button){

            EditText firstName = (EditText)findViewById(R.id.first_name);
            EditText lastName = (EditText)findViewById(R.id.last_name);
            EditText email = (EditText)findViewById(R.id.email);
            EditText username = (EditText)findViewById(R.id.username);
            EditText password1 = (EditText)findViewById(R.id.password1);
            EditText password2 = (EditText)findViewById(R.id.password2);


            String firstNameStr = firstName.getText().toString();
            String lastNameStr = lastName.getText().toString();
            String emailStr = email.getText().toString();
            String usernameStr = username.getText().toString();
            String pass1Str = password1.getText().toString();
            String pass2Str = password2.getText().toString();

            if(!pass1Str.equals(pass2Str)){

                //popup message
                Toast pass = Toast.makeText(SignUpActivity.this, "Passwords don't match!", Toast.LENGTH_SHORT);
                pass.show();
            }
            else if(firstNameStr.equals("")||lastNameStr.equals("")||emailStr.equals("")||usernameStr.equals("")||pass1Str.equals("")||pass2Str.equals("")){
                //popup message
                Toast error = Toast.makeText(SignUpActivity.this, "Missing Required Information", Toast.LENGTH_SHORT);
                error.show();
            }
            else
            {
                //insert the details in database
                UserInformation user = new UserInformation();
                user.setFirstname(firstNameStr);
                user.setLastname(lastNameStr);
                user.setEmail(emailStr);
                user.setUsername(usernameStr);
                user.setPassword(pass1Str);

                helper.insertUser(user);

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);

            }



        }
    }
}
