package com.example.navigationdrawer.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.navigationdrawer.R;

import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity {
        RecyclerView recyclerView;
        RecyclerChatAdapter recyclerChatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        //initializing my recyclerview
        recyclerView=findViewById(R.id.recyclerViewId);
        recyclerChatAdapter=new RecyclerChatAdapter();
        recyclerView.setAdapter(recyclerChatAdapter);
    }
}