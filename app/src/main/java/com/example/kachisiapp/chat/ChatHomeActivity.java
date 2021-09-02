package com.example.kachisiapp.chat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.kachisiapp.R;
import com.example.kachisiapp.home.HomeDrawer;
import com.google.android.material.tabs.TabLayout;

public class ChatHomeActivity extends AppCompatActivity {

    TabLayout chat_tablayout;
    ViewPager2 viewPager;
    Toolbar chat_toolbar;
    RecyclerChatAdapter recyclerChatAdapter;
    RecyclerView chatRecycler;
    ChatFragmentsPagerAdapter chatFragmentsPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_home);
        viewPager = findViewById(R.id.chat_viewPager2);
        chat_tablayout = findViewById(R.id.chat_tablyt_Id);

        recyclerChatAdapter = new RecyclerChatAdapter();

        FragmentManager fragmentManager=getSupportFragmentManager();
        chatFragmentsPagerAdapter=new ChatFragmentsPagerAdapter(fragmentManager,getLifecycle());
        viewPager.setAdapter(chatFragmentsPagerAdapter);

        chat_tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
    @Override
    public void onPageSelected(int position) {
        super.onPageSelected(position);
        chat_tablayout.selectTab(chat_tablayout.getTabAt(position));
    }
});


    }


    public void toHome(View view) {

    Intent intent=new Intent(ChatHomeActivity.this, HomeDrawer.class);
    startActivity(intent);}
}