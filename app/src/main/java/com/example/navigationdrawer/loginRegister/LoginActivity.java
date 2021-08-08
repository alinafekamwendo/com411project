package com.example.navigationdrawer.loginRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.navigationdrawer.R;
import com.example.navigationdrawer.youtubechannel.YoutubeActivity;
import com.google.android.youtube.player.YouTubeBaseActivity;

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
                    Intent intent =new Intent(LoginActivity.this, RegisterActivity.class);
                    startActivity(intent);
                }
        });

    }



}