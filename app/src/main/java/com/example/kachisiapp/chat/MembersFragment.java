package com.example.kachisiapp.chat;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kachisiapp.R;
import com.example.kachisiapp.people.Member;
import com.example.kachisiapp.people.RecyclerPeopleAdapter;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class MembersFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerPeopleAdapter recyclerPeopleAdapter;
    FirebaseFirestore firebaseFirestore;
    RecyclerPeopleAdapter mrecyclerPeopleAdapter;
    Context context;
    List<Member> members;
    ProgressDialog progressDialog;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_members, container, false);

        //initialising variables
        recyclerView=view.findViewById(R.id.membersFragment_recyclerView);
        List<Member> members=new ArrayList<>();
        //firestore instance
        firebaseFirestore=FirebaseFirestore.getInstance();

        //adapter
        recyclerPeopleAdapter= new RecyclerPeopleAdapter(getActivity(),members);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(mrecyclerPeopleAdapter);

        progressDialog=new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading......");
        readFromDB();


        return view;




    }
    private void  readFromDB(){
        firebaseFirestore.collection("members").orderBy("firsname", Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error !=null){
                    progressDialog.dismiss();
                    Toast.makeText(getActivity(), "Oops! "+error.getMessage(), Toast.LENGTH_SHORT).show();
                }
                for (DocumentChange updatedList:value.getDocumentChanges()){
                    if(updatedList.getType()==DocumentChange.Type.ADDED){
                        members.add(updatedList.getDocument().toObject(Member.class));
                    }
                    mrecyclerPeopleAdapter.notifyDataSetChanged();
                }
            }
        });
    }

}