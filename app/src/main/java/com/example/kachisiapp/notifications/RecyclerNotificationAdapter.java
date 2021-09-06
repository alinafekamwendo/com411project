package com.example.kachisiapp.notifications;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kachisiapp.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RecyclerNotificationAdapter extends RecyclerView.Adapter<RecyclerNotificationAdapter.NotificationViewHolder> implements Filterable {

    private List<Notification> notifications=new ArrayList<>();
    private List<Notification> tempNotifications=new ArrayList<>(notifications);


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
        holder.dateText.setText(currentNotification.getDate());
        holder.textTitle.setText(currentNotification.getTitle());
        holder.timeText.setText(currentNotification.getTime());
        holder.messageText.setText(currentNotification.getMessage());
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }
    //get position
    public Notification getNotificationPos(int position){
        return notifications.get(position);
    }
    public void setNotifications(List<Notification> notifications){
        this.notifications=notifications;
        notifyDataSetChanged();
    }

    //filtering
    @Override
    public Filter getFilter() {
        return filter;
    }
    private Filter filter =new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            String inputSearch=charSequence.toString().toLowerCase();
            List<Notification> temporary=new ArrayList<>();
            if(inputSearch.isEmpty()||inputSearch.length()==0){

                temporary.addAll(tempNotifications);
            }else
                {
                for(Notification singleNotification :tempNotifications){

                    if(singleNotification.getTitle().toLowerCase().contains(inputSearch)){
                        temporary.add(singleNotification);
                    }
                }

            }
            FilterResults filteredNotifications=new FilterResults();
            filteredNotifications.values=temporary;
            return filteredNotifications;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            notifications.clear();
            notifications.addAll((Collection<? extends Notification>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    //end of filtering

    class NotificationViewHolder extends RecyclerView.ViewHolder{
        private final TextView textTitle,messageText,timeText,dateText;
    public NotificationViewHolder(@NonNull View itemView) {
        super(itemView);
        dateText=itemView.findViewById(R.id.notification_date);
        textTitle=itemView.findViewById(R.id.notification_title);
        timeText=itemView.findViewById(R.id.notification_time);
        messageText=itemView.findViewById(R.id.notification_message);
    }
}
}
