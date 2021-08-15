package com.example.navigationdrawer.notifications;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notifications_table")
public class Notification {

    @PrimaryKey(autoGenerate = true)
    public int uniqueId;
 
    @ColumnInfo(name ="title")
    public String title;

    @ColumnInfo(name = "message")
    public String message;

    @ColumnInfo(name = "time")
    public  String time;

    //generate setters and getters

    public void setUniqueId(int uniqueId) {
        this.uniqueId = uniqueId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }
}
