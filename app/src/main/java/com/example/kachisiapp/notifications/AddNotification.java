package com.example.kachisiapp.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kachisiapp.R;


public class AddNotification extends AppCompatActivity {
    public static final String TITLE= "com.example.kachisiapp.notifications.TITLE";
    public static final String TIME= "com.example.kachisiapp.notifications.TIME";
    public static final String MESSAGE= "com.example.kachisiapp.notifications.MESSAGE";
    private EditText title, message, time;
    private Button saveBtn;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notification);
        title = findViewById(R.id.add_title);
        time = findViewById(R.id.add_time);
        message = findViewById(R.id.add_message);
        saveBtn=findViewById(R.id.add_save);

        //saveBtn.setOnClickListener(new View.OnClickListener() {
           // @Override
          //  public void onClick(View view) {
          //     saveNotification(view);
          //  }
       // });
    }

    public void toNotifications(View view) {
        Intent intent = new Intent(AddNotification.this, NotificationsActivity.class);
        startActivity(intent);
    }

    public void saveNotification(View view) {
        String add_title = title.getText().toString();
        String add_time = time.getText().toString();
        String add_message = message.getText().toString();

        if (add_title.trim().isEmpty() || add_time.trim().isEmpty() || add_message.trim().isEmpty()) {
            Toast.makeText(this, "please fill in all the fields", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent notificationDetails = new Intent();
        notificationDetails.putExtra(TITLE,add_title);
        notificationDetails.putExtra(MESSAGE,add_message);
        notificationDetails.putExtra(TIME,add_time);
        setResult(RESULT_OK,notificationDetails);
        finish();
    }
}