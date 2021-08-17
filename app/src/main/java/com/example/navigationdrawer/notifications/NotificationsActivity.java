package com.example.navigationdrawer.notifications;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.navigationdrawer.HomeActivity;
import com.example.navigationdrawer.R;
import com.example.navigationdrawer.home.HomeDrawer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class NotificationsActivity extends AppCompatActivity {
      private NotificationVModel notificationVModel;
      FloatingActionButton notificationFloatingButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        notificationFloatingButton=findViewById(R.id.floatingActionButton);

        ActivityResultLauncher<Intent> intentLauncher =registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode()== Activity.RESULT_OK){
                        String title=result.getData().getStringExtra(AddNotification.TITLE);
                        String time=result.getData().getStringExtra(AddNotification.TIME);
                        String message=result.getData().getStringExtra(AddNotification.MESSAGE);
                        Notification notification=new Notification(title,message,time);
                        Toast.makeText(NotificationsActivity.this, "saved succesfully", Toast.LENGTH_SHORT).show();
                        notificationVModel.insert(notification);
                    }
                    else{
                        Toast.makeText(NotificationsActivity.this, "not saved", Toast.LENGTH_SHORT).show();
                    }

                }
        );
       notificationFloatingButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(NotificationsActivity.this,AddNotification.class);
               intentLauncher.launch(intent);
           }
       });

        RecyclerView recyclerView=findViewById(R.id.notification_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerNotificationAdapter recyclerNotificationAdapter=new RecyclerNotificationAdapter();
        recyclerView.setAdapter(recyclerNotificationAdapter);

       notificationVModel= ViewModelProviders.of(NotificationsActivity.this).get(NotificationVModel.class);
        notificationVModel.getAllNotifications().observe(this,new Observer<List<Notification>>() {
            @Override
            public void onChanged(List<Notification> notifications) {
               recyclerNotificationAdapter.setNotifications(notifications);
            }
        });
    }

    public void toHome(View view) {
        Intent intent=new Intent(NotificationsActivity.this,HomeDrawer.class);
        startActivity(intent);
    }

    public void toAddNotification(View view) {
        Intent intent=new Intent(NotificationsActivity.this,AddNotification.class);
        startActivity(intent);
    }

  }
