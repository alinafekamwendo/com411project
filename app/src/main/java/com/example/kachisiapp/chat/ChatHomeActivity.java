package com.example.kachisiapp.chat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.kachisiapp.R;
import com.example.kachisiapp.home.HomeDrawer;
import com.google.android.material.tabs.TabLayout;

public class ChatHomeActivity extends AppCompatActivity {

    TabLayout chat_tablayout;
    ViewPager chat_viewpgr;
    Toolbar chat_toolbar;
    RecyclerChatAdapter recyclerChatAdapter;
    RecyclerView chatRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_home);

        recyclerChatAdapter=new RecyclerChatAdapter();
        chatRecycler=findViewById(R.id.chat_recyclerView);
        chatRecycler.setAdapter(recyclerChatAdapter);
        chatRecycler.setLayoutManager(new LinearLayoutManager(this));

    }


    public void toHome(View view) {
        Intent intent=new Intent(ChatHomeActivity.this, HomeDrawer.class);
        startActivity(intent);
    }
}