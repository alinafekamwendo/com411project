package com.example.kachisiapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kachisiapp.home.HomeDrawer;
import com.example.kachisiapp.loginRegister.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LaunchActivity extends AppCompatActivity {

    FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //This method will be executed once the timer is over
                // check if user is logged in already
                if (firebaseUser!=null){

                    Intent i = new Intent(LaunchActivity.this, HomeDrawer.class);
                    startActivity(i);
                    // close this activity
                    finish();
                }else {

                    Intent i = new Intent(LaunchActivity.this, LoginActivity.class);
                    startActivity(i);
                    // close this activity
                    finish();
                }
            }
        }, 1000);
    }

}