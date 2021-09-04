package com.example.kachisiapp.people;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kachisiapp.R;
import com.example.kachisiapp.home.HomeDrawer;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class PeopleActivity extends AppCompatActivity {
    RecyclerPeopleAdapter mrecyclerPeopleAdapter;
    RecyclerView recyclerView;
    Context context;
    List<Member> mmembers;
    FirebaseFirestore mfirebaseFirestore;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);
        mfirebaseFirestore =FirebaseFirestore.getInstance();
        mmembers=new ArrayList<Member>();
        recyclerView=findViewById(R.id.people_recycler);
        mrecyclerPeopleAdapter = new RecyclerPeopleAdapter(PeopleActivity.this,mmembers);
        recyclerView.setAdapter(mrecyclerPeopleAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressBar=findViewById(R.id.people_progressbar);

       ListenToDB();
    }

    public void goHome(View view) {
        Intent intent=new Intent(PeopleActivity.this, HomeDrawer.class);
        startActivity(intent);

    }
    private void ListenToDB(){
        mfirebaseFirestore.collection("members").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        if (!queryDocumentSnapshots.isEmpty()) {
                            progressBar.setVisibility(View.GONE);
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {

                                Member members = d.toObject(Member.class);
                                mmembers.add(members);

                            }

                            mrecyclerPeopleAdapter.notifyDataSetChanged();
                        } else {
                            // if the snapshot is empty we are displaying a toast message.
                            Toast.makeText(PeopleActivity.this, "No data found in Database", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(PeopleActivity.this, "Fail to get the data.", Toast.LENGTH_SHORT).show();
            }
        });
    }

}