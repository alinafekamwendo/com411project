package com.example.kachisiapp.people;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PersonDao {

    @Insert
    void insert(Person person);

    @Delete
    void delete(Person person);

    @Update
    void update(Person person);

    @Query("DELETE FROM Person_Table")
    Void deleteAllPeople();

    @Query("SELECT * FROM Person_Table")
    LiveData<List<Person>> getALLPeople();



}
