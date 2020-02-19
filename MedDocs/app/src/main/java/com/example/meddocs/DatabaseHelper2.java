package com.example.meddocs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DatabaseHelper2 extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "info.db";
    public static final String TABLE_NAME = "hospital_table";
    public static final String COL_1 = "HOSPITAL_NAME";
    public static final String COL_2 = "DOCTORS";
    public static final String COL_3 = "TIME";


    public DatabaseHelper2(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (HOSPITAL_NAME TEXT, DOCTORS TEXT, TIME TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }



    public boolean insertData(String hospital_name, String doctors, String time){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, hospital_name);
        contentValues.put(COL_2, doctors);
        contentValues.put(COL_3, time);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }

    public Cursor getAllHospital(){
        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_2 + " = ? AND " + COL_4 + " = ?" ,new String[]{name, password});
        Cursor res =  db.rawQuery("SELECT DISTINCT " + COL_1 + " FROM " + TABLE_NAME, null);
        return res;
    }

    public void deleteAll()
    {        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("delete from "+ TABLE_NAME);
        db.close();
    }

    public Cursor getAllDoctor(String hospital_name){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res =  db.rawQuery("SELECT DISTINCT " + COL_2 + " FROM " + TABLE_NAME + " WHERE " + COL_1 + " = ?", new String[]{hospital_name});
        return res;
    }

    public Cursor getAllAppointment(String hospital_name, String doctors){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res =  db.rawQuery("SELECT " + COL_3 + " FROM " + TABLE_NAME + " WHERE " + COL_1 + " = ? AND " + COL_2 + " = ?", new String[]{hospital_name, doctors});
        return res;
    }

    public void delete(String hospital_name, String doctors, String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+TABLE_NAME+" where " + COL_1 + "= ? AND " + COL_2 + "=? AND " + COL_3 + "= ?", new String[]{hospital_name, doctors, time});
    }

//
//    public boolean updateData(String name, String length, String size, String singer, String link){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL_1, name);
//        contentValues.put(COL_2, length);
//        contentValues.put(COL_3, size);
//        contentValues.put(COL_4, singer);
//        contentValues.put(COL_5, link);
//        db.update(TABLE_NAME , contentValues, "NAME = ?",new String[] {name} );
//        return true;
//    }
//
//    public Integer deleteData(String name){
//        SQLiteDatabase db = this.getWritableDatabase();
//        return db.delete(TABLE_NAME, "NAME = ?", new String[] {name});
//    }
}