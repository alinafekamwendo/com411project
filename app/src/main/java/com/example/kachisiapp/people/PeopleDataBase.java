package com.example.kachisiapp.people;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Person.class},version = 1,exportSchema = false)
public   abstract class PeopleDataBase extends RoomDatabase{

    public abstract PersonDao personDao();
    private static PeopleDataBase peopleDataBase;
    private final static  String DATABASE_NAME ="People_Database";

    public static synchronized PeopleDataBase getInstance(Context context){

        if(peopleDataBase ==null){
            peopleDataBase =Room.databaseBuilder(context.getApplicationContext(),PeopleDataBase.class,DATABASE_NAME)

                    .fallbackToDestructiveMigration()
                    .addCallback(peopleDataBaseCallBack)
                    .build();
        }

        return peopleDataBase;
    }
    private static RoomDatabase.Callback peopleDataBaseCallBack= new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDBAsyncTask(peopleDataBase).execute();
        }
    };


    private static class PopulateDBAsyncTask extends AsyncTask<Void,Void,Void>{
        private PersonDao personDao;
        private  PopulateDBAsyncTask(PeopleDataBase peopleDataBase){
            personDao=peopleDataBase.personDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }


}
