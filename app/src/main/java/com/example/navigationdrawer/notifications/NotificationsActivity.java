package com.example.navigationdrawer.notifications;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.navigationdrawer.HomeActivity;
import com.example.navigationdrawer.R;
import com.example.navigationdrawer.home.HomeDrawer;

public class NotificationsActivity extends AppCompatActivity {
        Button toHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        toHome=findViewById(R.id.toHome);
        toHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), HomeDrawer.class);
                startActivity(intent);
            }
        });
    }
}