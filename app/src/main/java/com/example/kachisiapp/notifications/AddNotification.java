package com.example.kachisiapp.notifications;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kachisiapp.R;

import java.text.SimpleDateFormat;
import java.util.Locale;


public class AddNotification extends AppCompatActivity {
    public static final String TITLE= "com.example.kachisiapp.notifications.TITLE";
    public static final String TIME= "com.example.kachisiapp.notifications.TIME";
    public static final String MESSAGE= "com.example.kachisiapp.notifications.MESSAGE";
    public static final String DATE= "com.example.kachisiapp.notifications.DATE";
    private EditText title, message, time,date;
    private Calendar calendar;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notification);
        title = findViewById(R.id.add_title);
        time = findViewById(R.id.add_time);
        message = findViewById(R.id.add_message);
        Button saveBtn = findViewById(R.id.add_save);
        date=findViewById(R.id.add_date);
        calendar=Calendar.getInstance();


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNotification();
            }
        });

        //date picker

            date.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new DatePickerDialog(AddNotification.this, datePickerListener, calendar
                            .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)).show();
                }

                DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy", Locale.ITALIAN);
                        date.setText(sdf.format(calendar.getTime()));
                    }

                };

            });




//time picker

        time.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar selectedTime = Calendar.getInstance();
                int hour = selectedTime.get(Calendar.HOUR_OF_DAY);
                int minute = selectedTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddNotification.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        if(selectedHour>=12){
                          String  status="PM";
                          String mTime=selectedHour + ":" + selectedMinute+" "+status;
                        time.setText( mTime);
                        }
                        if(selectedHour<12){
                            String status="AM";
                            String mTime=selectedHour + ":" + selectedMinute+" "+status;
                            time.setText( mTime);
                        }

                    }
                }, hour, minute, false);
                mTimePicker.setTitle("set event time");
                mTimePicker.show();

            }
        });


    }

    public void toNotifications(View view) {
        Intent intent = new Intent(AddNotification.this, NotificationsActivity.class);
        startActivity(intent);
    }

    public void saveNotification() {
        String add_date=date.getText().toString();
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
        notificationDetails.putExtra(DATE,add_date);
        setResult(RESULT_OK,notificationDetails);
        finish();
    }

}