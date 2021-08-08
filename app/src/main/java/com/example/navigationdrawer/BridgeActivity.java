package com.example.navigationdrawer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.navigationdrawer.chat.NumberAunthentication;
import com.example.navigationdrawer.youtubechannel.YoutubeActivity;

public class BridgeActivity extends AppCompatActivity {
    Button to_live,to_chat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bridge);

        to_chat=findViewById(R.id.bridge_to_chat_Id);
        to_live=findViewById(R.id.bridge_to_live);

        to_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent=new Intent(getApplicationContext(), NumberAunthentication.class);
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