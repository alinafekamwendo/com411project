package com.example.navigationdrawer.notifications;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigationdrawer.R;
import com.example.navigationdrawer.chat.RecyclerChatAdapter;

import java.util.ArrayList;
import java.util.List;

public class RecyclerNotificationAdapter extends RecyclerView.Adapter<RecyclerNotificationAdapter.NotificationViewHolder> {

    private List<Notification> notifications=new ArrayList<>();

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_notification_item,parent,false);

        return new NotificationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        Notification currentNotification=notifications.get(position);
        holder.textTitle.setText(currentNotification.getTitle());
        holder.timeText.setText(currentNotification.getTime());
        holder.messageText.setText(currentNotification.getMessage());
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }
    public void setNotifications(List<Notification> notifications){
        this.notifications=notifications;
        notifyDataSetChanged();
    }

    class NotificationViewHolder extends RecyclerView.ViewHolder{
        private TextView textTitle,messageText,timeText;
    public NotificationViewHolder(@NonNull View itemView) {
        super(itemView);
        textTitle=itemView.findViewById(R.id.notification_title);
        timeText=itemView.findViewById(R.id.notification_time);
        messageText=itemView.findViewById(R.id.notification_message);
    }
}
}
