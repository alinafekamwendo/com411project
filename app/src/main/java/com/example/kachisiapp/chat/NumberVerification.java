package com.example.kachisiapp.chat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;

import kachisiapp.databinding.ActivityNumberBinding;

public class NumberVerification extends AppCompatActivity {

    ActivityNumberBinding activityNumberBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityNumberBinding= ActivityNumberBinding.inflate(getLayoutInflater());
        setContentView(activityNumberBinding.getRoot());
        //initializing firebase
        FirebaseApp.initializeApp(this);

        activityNumberBinding.verifyCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(NumberVerification.this,OTP_activity.class);
                intent.putExtra("phone number",activityNumberBinding.codeNumber.getText().toString());
                startActivity(intent);
            }
        });




    }

}