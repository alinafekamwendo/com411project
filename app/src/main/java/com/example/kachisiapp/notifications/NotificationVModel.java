package com.example.kachisiapp.notifications;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NotificationVModel extends AndroidViewModel {
    private NotificationRepository notificationRepository;
    private LiveData<List<Notification>>  allNotifications;


    public NotificationVModel(@NonNull Application application) {
        super(application);
        notificationRepository=new NotificationRepository(application);
        allNotifications=notificationRepository.getAllNotifications();
    }
    public void insert(Notification notification){
        notificationRepository.insert(notification);
    }
    public void update(Notification notification){
        notificationRepository.update(notification);
    }
    public  void delete(Notification notification){
        notificationRepository.delete(notification);
    }
    public void deleteAll(){
        notificationRepository.deleteAllNotifications();
    }
    public LiveData<List<Notification>> getAllNotifications(){
        return allNotifications;
    }
}
