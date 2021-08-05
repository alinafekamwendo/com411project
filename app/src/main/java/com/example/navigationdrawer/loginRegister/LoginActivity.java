package com.example.navigationdrawer.loginRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.navigationdrawer.R;
import com.example.navigationdrawer.chat.ChatHomeActivity;

public class LoginActivity extends AppCompatActivity {
  Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login=findViewById(R.id.login_registerbtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent =new Intent(LoginActivity.this, ChatHomeActivity.class);
                    startActivity(intent);
                }
        });

    }



}