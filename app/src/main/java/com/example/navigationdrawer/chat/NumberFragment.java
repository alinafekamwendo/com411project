package com.example.navigationdrawer.chat;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.navigationdrawer.R;
import com.google.firebase.FirebaseApp;

import java.util.zip.Inflater;


public class NumberFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     return inflater.inflate(R.layout.fragment_number, container, false);

    }
}
