package com.example.navigationdrawer.people;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.navigationdrawer.R;
import com.example.navigationdrawer.home.HomeDrawer;
import com.example.navigationdrawer.people.RecyclerPeopleAdapter;

public class PeopleActivity extends AppCompatActivity {
    RecyclerPeopleAdapter recyclerPeopleAdapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);

        recyclerView=findViewById(R.id.people_recycler);
        recyclerPeopleAdapter= new RecyclerPeopleAdapter();
        recyclerView.setAdapter(recyclerPeopleAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void goHome(View view) {
        Intent intent=new Intent(PeopleActivity.this, HomeDrawer.class);
        startActivity(intent);

    }
}