package com.example.tammy.test.DataRelated;

import android.util.Base64;
import android.util.Log;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import static android.content.ContentValues.TAG;

/**
 * Created by Tammy on 10/2/16.
 */

public class UserInformation {


    String firstname, lastname, email, username, password, bio;

    public void setFirstname(String firstname){
        this.firstname = firstname;
    }

    public String getFirstname(){
        return this.firstname;
    }

    public void setLastname(String lastname){
        this.lastname = lastname;
    }

    public String getLastname(){
        return this.lastname;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername(){
        return this.username;
    }

    public void setPassword(String password){
// Set up secret key spec for 128-bit AES encryption and decryption
            this.password = password;
//        SecretKeySpec sks = null;
//        try {
//            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
//            sr.setSeed("R@nD0m s33d G3NeRaT0r".getBytes());
//            KeyGenerator kg = KeyGenerator.getInstance("AES");
//            kg.init(128, sr);
//            sks = new SecretKeySpec((kg.generateKey()).getEncoded(), "AES");
//        } catch (Exception e) {
//            Log.e(TAG, "AES secret key spec error");
//        }
//
//        // Encode the original data with AES
//        byte[] encodedBytes = null;
//        try {
//            Cipher c = Cipher.getInstance("AES");
//            c.init(Cipher.ENCRYPT_MODE, sks);
//            encodedBytes = c.doFinal(password.getBytes());
//        } catch (Exception e) {
//            Log.e(TAG, "AES encryption error");
//        }
//        this.password = Base64.encodeToString(encodedBytes, Base64.DEFAULT);
    }

    public String getPassword(){
        return this.password;
//        SecretKeySpec sks = null;
//        try {
//            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
//            sr.setSeed("R@nD0m s33d G3NeRaT0r".getBytes());
//            KeyGenerator kg = KeyGenerator.getInstance("AES");
//            kg.init(128, sr);
//            sks = new SecretKeySpec((kg.generateKey()).getEncoded(), "AES");
//        } catch (Exception e) {
//            Log.e(TAG, "AES secret key spec error");
//        }
//        // Decode the encoded data with AES
//        byte[] decodedBytes = null;
//        try {
//            Cipher c = Cipher.getInstance("AES");
//            c.init(Cipher.DECRYPT_MODE, sks);
//            decodedBytes = c.doFinal(this.password.getBytes());
//        } catch (Exception e) {
//            Log.e(TAG, "AES decryption error");
//        }
//        return new String(decodedBytes);
    }


}
