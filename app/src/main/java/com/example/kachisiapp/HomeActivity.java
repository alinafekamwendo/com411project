package com.example.kachisiapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.kachisiapp.chat.NumberVerification;
import com.example.kachisiapp.youtubechannel.YoutubeActivity;

import kachisiapp.R;

public class HomeActivity extends AppCompatActivity {
    CardView to_live,to_chat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        to_chat=findViewById(R.id.chat_card);
        to_live=findViewById(R.id.live_card);

        to_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent=new Intent(getApplicationContext(), NumberVerification.class);
              startActivity(intent);
            }
        });
        to_live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent=new Intent(getApplicationContext(), YoutubeActivity.class);
                    startActivity(intent);
            }
        });

    }


}