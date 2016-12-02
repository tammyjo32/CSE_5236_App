package com.example.tammy.test.login_related;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tammy.test.DataRelated.DatabaseHelper;
import com.example.tammy.test.displayMenu.DisplayActivity;
import com.example.tammy.test.R;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import static android.content.ContentValues.TAG;

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
//        if(password!="not found"){
//            SecretKeySpec sks = null;
//            try {
//                SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
//                sr.setSeed("R@nD0m s33d G3NeRaT0r".getBytes());
//                KeyGenerator kg = KeyGenerator.getInstance("AES");
//                kg.init(128, sr);
//                sks = new SecretKeySpec((kg.generateKey()).getEncoded(), "AES");
//            } catch (Exception e) {
//                Log.e(TAG, "AES secret key spec error");
//            }
//            // Decode the encoded data with AES
//            byte[] decodedBytes = null;
//            try {
//                Cipher c = Cipher.getInstance("AES");
//                c.init(Cipher.DECRYPT_MODE, sks);
//                decodedBytes = c.doFinal(password.getBytes());
//            } catch (Exception e) {
//                Log.e(TAG, "AES decryption error");
//            }
//            password = new String(decodedBytes);
//        }

        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("EmailAddr", email).apply();

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

        Intent intent = new Intent(this, PopUpForgotPassword.class);
        startActivity(intent);
    }
}
