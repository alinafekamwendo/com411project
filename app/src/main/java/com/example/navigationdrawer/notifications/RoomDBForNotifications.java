package com.example.navigationdrawer.notifications;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Notification.class},version=1,exportSchema = false)
public abstract class RoomDBForNotifications extends RoomDatabase {

    private static RoomDBForNotifications database;
    private  static String DATABASE_NAME="database";
    public synchronized  static RoomDBForNotifications getInstance(Context context){

        //check existence of the database
        if(database==null){
            //when does not exist initialize it
            database=Room.databaseBuilder(context.getApplicationContext()
                    ,RoomDBForNotifications.class,DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        //return the createdn database
        return database;
    }

//create Dao
public abstract NotificationDAO notificationDAO();
    }

