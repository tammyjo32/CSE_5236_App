package com.example.tammy.test.displayMenu;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tammy.test.R;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        setTitle("Help");
    }

    public void sendMessage(View view) {

        EditText editText = (EditText)findViewById(R.id.subject);
        String subject = editText.getText().toString();
        EditText editText1 = (EditText)findViewById(R.id.message);
        String message = editText1.getText().toString();

        if(subject.equals("") || message.equals("")){
            Toast temp = Toast.makeText(this, "Subject and Message field cannot be blank!" , Toast.LENGTH_SHORT);
            temp.show();
        }
        else {

            Intent email = new Intent(Intent.ACTION_SEND);
            email.putExtra(Intent.EXTRA_EMAIL, new String[]{"tamtam1994@gmail.com"});
            email.putExtra(Intent.EXTRA_SUBJECT, subject);
            email.putExtra(Intent.EXTRA_TEXT, message);

            email.setType("message/rfc822");

            //Note that activity below will not work on emulator. Need real device to work
            startActivity(Intent.createChooser(email, "Choose an Email Client :"));

        }

    }

}
