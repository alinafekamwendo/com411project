package com.example.kachisiapp.notifications;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kachisiapp.R;
import com.example.kachisiapp.home.HomeDrawer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class NotificationsActivity extends AppCompatActivity {
    private NotificationVModel notificationVModel;
    FloatingActionButton notificationFloatingButton;
    CardView cardView;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        notificationFloatingButton=findViewById(R.id.floatingActionButton);
        toolbar=findViewById(R.id.toolbar);
       // cardView=findViewById(R.id.notification_deleteCardV);

        toolbar.inflateMenu(R.menu.menu_delete);

        ActivityResultLauncher<Intent> intentLauncher =registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode()== Activity.RESULT_OK){
                        String title=result.getData().getStringExtra(AddNotification.TITLE);
                        String time=result.getData().getStringExtra(AddNotification.TIME);
                        String message=result.getData().getStringExtra(AddNotification.MESSAGE);
                        Notification notification=new Notification(title,message,time);
                        notificationVModel.insert(notification);
                        Toast.makeText(NotificationsActivity.this, "saved succesfully", Toast.LENGTH_SHORT).show();
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

        //swipe delete
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT |ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
             notificationVModel.delete(recyclerNotificationAdapter.getNotificationPos(viewHolder.getAdapterPosition()));
                Toast.makeText(NotificationsActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

    }

    public void toHome(View view) {
        Intent intent=new Intent(NotificationsActivity.this,HomeDrawer.class);
        startActivity(intent);
    }

    public void toAddNotification(View view) {
        Intent intent=new Intent(NotificationsActivity.this,AddNotification.class);
        startActivity(intent);
    }
    //outside oncreate create menu


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_delete,menu);
        return true;
        //return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_deleteAll:
                notificationVModel.deleteAll();
                Toast.makeText(this, "all notifications deleted", Toast.LENGTH_SHORT).show();
                return true;
            default:

                return super.onOptionsItemSelected(item);

        }
    }
}







