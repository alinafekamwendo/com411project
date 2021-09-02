package com.example.kachisiapp.people;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kachisiapp.R;
import com.example.kachisiapp.home.HomeDrawer;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class PeopleActivity extends AppCompatActivity {
    RecyclerPeopleAdapter mrecyclerPeopleAdapter;
    RecyclerView recyclerView;
    Context context;
    List<Member> members;
    FirebaseFirestore mfirebaseFirestore;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);
        mfirebaseFirestore =FirebaseFirestore.getInstance();
        members=new ArrayList<Member>();
        recyclerView=findViewById(R.id.people_recycler);
        mrecyclerPeopleAdapter = new RecyclerPeopleAdapter(PeopleActivity.this,members);
        recyclerView.setAdapter(mrecyclerPeopleAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

         progressDialog=new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading......");
       ListenToDB();
    }

    public void goHome(View view) {
        Intent intent=new Intent(PeopleActivity.this, HomeDrawer.class);
        startActivity(intent);

    }
    private void ListenToDB(){
        mfirebaseFirestore.collection("members").orderBy("firstname", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error !=null){
                    if(progressDialog.isShowing()){
                    progressDialog.dismiss();}
                    Toast.makeText(PeopleActivity.this, "Oops! "+error.getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }
                for (DocumentChange updatedList : value.getDocumentChanges()){
                    if(updatedList.getType()==DocumentChange.Type.ADDED){
                        members.add(updatedList.getDocument().toObject(Member.class));
                    }
                    mrecyclerPeopleAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}