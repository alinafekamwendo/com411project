package com.example.navigationdrawer.notifications;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NotificationDAO {

    //pulling all notifications from the database
    @Query("SELECT * FROM notifications_table")
    List<Notification> getAllNotifications();

    //inserting a notification into our room database
    @Insert(onConflict =REPLACE)
    void  insertNotification(Notification... notifications);

    //in case ,need to delete
    @Delete
    void delete(Notification notification);
    //deleting all

}

