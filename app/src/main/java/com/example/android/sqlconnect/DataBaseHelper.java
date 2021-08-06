package com.example.android.sqlconnect;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String NAME = "NAME";
    public static final String YEAROFRELEASE = "YEAROFRELEASE";
    public static final String ACTOR = "ACTOR";
    public static final String ACTRESS = "ACTRESS";
    public static final String MOVIE_TABLE = "MOVIE_TABLE";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "movie.db", null, 1);
    }

    //    The onCreate will create a Database table the first time we run the code
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + MOVIE_TABLE + "( ID INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT NOT NULL , " + YEAROFRELEASE + " INTEGER NOT NULL, " + ACTOR + " TEXT, " + ACTRESS + " TEXT)";

        db.execSQL(createTableStatement);
    }

//      This method is called whenever the version of the database is changed
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addData(MovieModel movieModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(NAME, movieModel.getName());
        cv.put(YEAROFRELEASE, movieModel.getYearOfRelease());
        cv.put(ACTOR,movieModel.getActor());
        cv.put(ACTRESS, movieModel.getActress());
        long insert  = db.insert(MOVIE_TABLE, null, cv);
        if (insert==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public List<MovieModel> displayall(){
        List<MovieModel> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + MOVIE_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            do{
                int movieID = cursor.getInt(0);
                String movieName = cursor.getString(1);
                int yearOfRelease = cursor.getInt(2);
                String actor = cursor.getString(3);
                String actress = cursor.getString(4);

                MovieModel newEntry = new MovieModel(movieID, movieName, yearOfRelease, actor, actress);
                returnList.add(newEntry);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnList;

    }
    public Cursor searchData(String name){
        SQLiteDatabase db = this.getReadableDatabase();
//        string queryString = "SELECT * FROM " + MOVIE_TABLE + " WHERE " + NAME + " = " + name;
        String queryString = "SELECT * FROM " + MOVIE_TABLE;
        Cursor cursor = db.rawQuery(queryString, null);
        return cursor;
    }
}
