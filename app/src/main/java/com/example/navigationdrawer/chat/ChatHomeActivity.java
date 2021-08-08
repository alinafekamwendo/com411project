package com.example.navigationdrawer.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.navigationdrawer.R;
import com.google.android.material.tabs.TabLayout;

public class ChatHomeActivity extends AppCompatActivity {

    TabLayout chat_tablayout;
    ViewPager chat_viewpgr;
    Toolbar chat_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_home);

    }
}