package com.example.kachisiapp.people;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kachisiapp.R;

import java.util.ArrayList;
import java.util.List;


public class RecyclerPeopleAdapter extends RecyclerView.Adapter<RecyclerPeopleAdapter.ViewHolder>{

    List<Member> membersList =new ArrayList<>();
    Context context;

    public RecyclerPeopleAdapter(Activity activity, List<Member> members) {
        this.context=activity;
        this.membersList=members;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.single_member,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Member member= membersList.get(position);
        holder.firstName.setText(member.getFirstname().toString().trim());
        holder.lastName.setText(member.getSurname().toString().trim());
        holder.userPhone.setText(member.getPhoneNumber().toString());
        holder.userCategory.setText(member.getCategory().toString());

    }

    @Override
    public int getItemCount() {

        return membersList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView avatar;
        TextView firstName,userCategory,userPhone,lastName;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            lastName=itemView.findViewById(R.id.member_surname);
            firstName =itemView.findViewById(R.id.member_firstname);
            userCategory=itemView.findViewById(R.id.member_category);
            userPhone=itemView.findViewById(R.id.member_phoneNumber);
        }
    }
}
