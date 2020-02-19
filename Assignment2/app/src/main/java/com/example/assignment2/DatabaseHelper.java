package com.example.assignment2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "songs.db";
    public static final String TABLE_NAME = "song_table";
    public static final String COL_1 = "NAME";
    public static final String COL_2 = "LENGTH";
    public static final String COL_3 = "SIZE";
    public static final String COL_4 = "SINGER";
    public static final String COL_5 = "LINK";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (NAME TEXT, LENGTH TEXT, SIZE TEXT, SINGER TEXT, LINK TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String length, String size, String singer, String link ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, name);
        contentValues.put(COL_2, length);
        contentValues.put(COL_3, size);
        contentValues.put(COL_4, singer);
        contentValues.put(COL_5, link);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME , null);
        return res;
    }

    public boolean updateData(String name, String length, String size, String singer, String link){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, name);
        contentValues.put(COL_2, length);
        contentValues.put(COL_3, size);
        contentValues.put(COL_4, singer);
        contentValues.put(COL_5, link);
        db.update(TABLE_NAME , contentValues, "NAME = ?",new String[] {name} );
        return true;
    }

    public Integer deleteData(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "NAME = ?", new String[] {name});
    }
}