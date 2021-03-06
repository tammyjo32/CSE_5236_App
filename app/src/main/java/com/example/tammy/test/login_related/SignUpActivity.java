package com.example.tammy.test.login_related;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tammy.test.DataRelated.DatabaseHelper;
import com.example.tammy.test.R;
import com.example.tammy.test.DataRelated.UserInformation;

public class SignUpActivity extends Activity {

    DatabaseHelper helper = new DatabaseHelper(this);

    public final static String EXTRA_MESSAGE = "com.example.tammy.EMAIL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_sign_up);

    }

    public void termsConditions(View view) {
        Intent intent = new Intent(this, PopUpTermsAndConditions.class);
        startActivity(intent);
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
                Toast pass = Toast.makeText(SignUpActivity.this, "Passwords Don't Match!", Toast.LENGTH_SHORT);
                pass.show();
            }
            else if(firstNameStr.equals("")||lastNameStr.equals("")||emailStr.equals("")||usernameStr.equals("")||pass1Str.equals("")||pass2Str.equals("")){
                //popup message
                Toast error = Toast.makeText(SignUpActivity.this, "Missing Required Information!", Toast.LENGTH_SHORT);
                error.show();
            }
            else if(helper.alreadyExistEmail(emailStr)){
                //popup message
                Toast error = Toast.makeText(SignUpActivity.this, "Email Already Registered!", Toast.LENGTH_SHORT);
                error.show();
            }
            else if(helper.alreadyExistUsername(usernameStr)){
                //popup message
                Toast error = Toast.makeText(SignUpActivity.this, "Username Already Taken!", Toast.LENGTH_SHORT);
                error.show();
            }
            else if(!isPasswordValid((CharSequence)pass1Str)){
                //popup message
                Toast error = Toast.makeText(SignUpActivity.this, "Password Must Be At Least 10 Characters Long!", Toast.LENGTH_SHORT);
                error.show();
            }
            else if(!isEmailValid((CharSequence)emailStr)){
                //popup message
                Toast error = Toast.makeText(SignUpActivity.this, "Incorrect Format for Email!", Toast.LENGTH_SHORT);
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

    protected boolean isEmailValid(CharSequence emailStr) {
        if(emailStr == null){
            return false;
        }else{
            return android.util.Patterns.EMAIL_ADDRESS.matcher(emailStr).matches();
        }
    }

    protected boolean isPasswordValid(CharSequence passwordStr) {
        if(passwordStr != null && passwordStr.length() > 9){
            return true;
        }else{
            return false;
        }
    }



}
