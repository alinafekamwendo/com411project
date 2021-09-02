package com.example.kachisiapp.notifications;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NotificationDAO {

    @Insert
    void insert(Notification notification);

    @Update
    void update(Notification notification);

    @Delete
    void delete(Notification notification);

    @Query("DELETE FROM notifications_table")
    void deleteAllNotifications();

    @Query("SELECT * FROM notifications_table ORDER BY time ASC")
    LiveData<List<Notification>> getAllNotifications();


}
