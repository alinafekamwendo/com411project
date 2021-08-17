package com.example.navigationdrawer.notifications;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Notification.class},version=1,exportSchema = false)
public abstract class RoomDBForNotifications extends RoomDatabase {

    private static RoomDBForNotifications roomDBForNotifications;

    public abstract NotificationDAO notificationDAO();

    //private static String DATABASE_NAME = "notification_database";

    public static synchronized  RoomDBForNotifications getInstance(Context context) {

        //check existence of the database
        if (roomDBForNotifications == null) {
            //when does not exist initialize it
            roomDBForNotifications = Room.databaseBuilder(context.getApplicationContext(),
                     RoomDBForNotifications.class, "notification_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomDBcallback)
                    .build();
        }
        //return the createdn database
        return roomDBForNotifications;
    }
    private static RoomDatabase.Callback roomDBcallback= new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new FillTheDbAsyncTask(roomDBForNotifications).execute();
        }
    };
    public static class FillTheDbAsyncTask extends AsyncTask<Void,Void,Void>{
        private NotificationDAO notificationDAO;
        private FillTheDbAsyncTask(RoomDBForNotifications roomDBForNotifications){
            notificationDAO=roomDBForNotifications.notificationDAO();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            notificationDAO.insert(new Notification("church service","you are reminded of the sunday's church service","11:30"));
            notificationDAO.insert(new Notification("officiation ceremony","take note that we will have officiation ceremony of MR and MRS","09:12"));
            return null;
        }
    }
}

