package com.example.kachisiapp.people;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kachisiapp.R;

import java.util.List;


public class RecyclerPeopleAdapter extends RecyclerView.Adapter<RecyclerPeopleAdapter.ViewHolder>{

    List<Member> membersList;
    Context context;

    public RecyclerPeopleAdapter(FragmentActivity activity, List<Member> members) {
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

        holder.userName.setText(member.getFirstname());
        holder.userPhone.setText(member.getFirstname());
        holder.userCategory.setText(member.getCategory());

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
