package com.example.navigationdrawer.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.navigationdrawer.R;
import com.example.navigationdrawer.databinding.ActivityNumberBinding;
import com.example.navigationdrawer.loginRegister.RegisterActivity;
import com.google.firebase.FirebaseApp;

public class NumberVerification extends AppCompatActivity {

    ActivityNumberBinding activityNumberBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityNumberBinding=ActivityNumberBinding.inflate(getLayoutInflater());
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