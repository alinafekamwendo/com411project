package com.example.kachisiapp.notifications;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NotificationRepository {
    private NotificationDAO notificationDAO;
    private LiveData<List<Notification>> allNotifications;

    public NotificationRepository(Application application){
        RoomDBForNotifications roomDBForNotifications=RoomDBForNotifications.getInstance(application);
        notificationDAO =roomDBForNotifications.notificationDAO();
        allNotifications=notificationDAO.getAllNotifications();
    }
public void insert(Notification notification){
    new InsertNotificationAsyncTask(notificationDAO).execute(notification);
}
public void update(Notification notification){
    new UpdateNotificationAsyncTask(notificationDAO).execute(notification);
    }
    public void delete(Notification notification){
        new DeleteNotificationAsyncTask(notificationDAO).execute(notification);
    }
    public void deleteAllNotifications(){
        new DeleteNotificationAsyncTask(notificationDAO).execute();
    }
    public LiveData<List<Notification>> getAllNotifications(){
        return allNotifications;
    }
    private static  class InsertNotificationAsyncTask extends AsyncTask<Notification,Void,Void>{
            private NotificationDAO notificationDAO;
            private InsertNotificationAsyncTask(NotificationDAO notificationDAO){
                this.notificationDAO=notificationDAO;
            }

        @Override
        protected Void doInBackground(Notification... notifications) {
                notificationDAO.insert(notifications[0]);
            return null;
        }
    }
    private static  class UpdateNotificationAsyncTask extends AsyncTask<Notification,Void,Void>{
        private NotificationDAO notificationDAO;
        private UpdateNotificationAsyncTask(NotificationDAO notificationDAO){
            this.notificationDAO=notificationDAO;
        }

        @Override
        protected Void doInBackground(Notification... notifications) {
            notificationDAO.update(notifications[0]);
            return null;
        }
    }
    private static  class DeleteAllNotificationAsyncTask extends AsyncTask<Void,Void,Void>{
        private NotificationDAO notificationDAO;
        private DeleteAllNotificationAsyncTask(NotificationDAO notificationDAO){
            this.notificationDAO=notificationDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            notificationDAO.deleteAllNotifications();
            return null;
        }
    }
    private static  class DeleteNotificationAsyncTask extends AsyncTask<Notification,Void,Void>{
        private NotificationDAO notificationDAO;
        private DeleteNotificationAsyncTask(NotificationDAO notificationDAO){
            this.notificationDAO=notificationDAO;
        }

        @Override
        protected Void doInBackground(Notification... notifications) {
            notificationDAO.delete(notifications[0]);
            return null;
        }
    }
}
