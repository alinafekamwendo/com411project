package com.example.navigationdrawer.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.navigationdrawer.R;
import com.example.navigationdrawer.loginRegister.RegisterActivity;
import com.google.firebase.FirebaseApp;

public class NumberVerification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_verification);
        //initializing firebase
        FirebaseApp.initializeApp(this);

        setFragment();

    }

    private void setFragment() {
        NumberFragment numberFragment=new NumberFragment();
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container,numberFragment);
        fragmentTransaction.commit();
    }

}