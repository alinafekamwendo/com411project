package com.example.kachisiapp.people;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
       //String fullName=member.firstname.toString()+" "+member.surname.toString();
        holder.userName.setText(member.getFirstname());
        holder.userPhone.setText(member.phoneNumber);
        holder.userCategory.setText(member.category);

    }

    @Override
    public int getItemCount() {

        return membersList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView userName,userCategory,userPhone;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            userName=itemView.findViewById(R.id.member_fullname);
            userCategory=itemView.findViewById(R.id.member_category);
            userPhone=itemView.findViewById(R.id.member_phoneNumber);
        }
    }
}
