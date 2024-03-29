package com.example.kachisiapp.notifications;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notifications_table")
public class Notification {

    @PrimaryKey(autoGenerate = true)
    public int uniqueId;
 
    @ColumnInfo(name ="title")
    private String title;

    @ColumnInfo(name = "date")
    private String date;
    @ColumnInfo(name = "message")
    private String message;

    @ColumnInfo(name = "time")
    private  String time;

    public Notification(String title, String message, String time,String date) {
        this.date=date;
        this.title = title;
        this.message = message;
        this.time = time;
    }
    //generate setters and getters

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setUniqueId(int uniqueId){this.uniqueId=uniqueId;}
    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTime(String time) {
        this.time = time;
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
