package com.example.kachisiapp.chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import kachisiapp.R;


public class RecyclerChatAdapter extends RecyclerView.Adapter<RecyclerChatAdapter.ViewHolder>{

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.single_chat_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {

        return 6;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView userPic;
        TextView userName,userCategory,userMessage,userTime;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            userPic=itemView.findViewById(R.id.user_picture);
            userName=itemView.findViewById(R.id.user_name);
            userCategory=itemView.findViewById(R.id.user_category);
            userMessage=itemView.findViewById(R.id.user_message);
            userTime=itemView.findViewById(R.id.user_message_time);


        }
    }
}
