package com.example.navigationdrawer.chat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.navigationdrawer.R;

public class NumberAunthentication extends AppCompatActivity {
    Button verify,continue_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_aunthentication);
        verify=findViewById(R.id.verify_button);
        continue_btn=findViewById(R.id.number_aunth_cobntinueid);



    }
}