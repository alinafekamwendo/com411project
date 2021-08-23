package com.example.kachisiapp.loginRegister;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kachisiapp.R;

public class LoginActivity extends AppCompatActivity {
  Button login_to_registerbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_to_registerbtn =findViewById(R.id.login_registerbtn);




        login_to_registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
                }
        });

    }



}