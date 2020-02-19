package com.example.meddocs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "medDocs.db";
    public static final String TABLE_NAME = "users_table";
    public static final String COL_1 = "NAME";
    public static final String COL_2 = "EMAIL";
    public static final String COL_3 = "PHONENUM";
    public static final String COL_4 = "PASSWORD";



    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (NAME TEXT, EMAIL TEXT, PHONENUM TEXT, PASSWORD TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public static final String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public boolean insertData(String name, String email, String phonenum, String pass ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        pass = md5(pass);
        contentValues.put(COL_1, name);
        contentValues.put(COL_2, email);
        contentValues.put(COL_3, phonenum);
        contentValues.put(COL_4, pass);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }


    public Cursor getAllData(String name, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        password = md5(password);
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_2 + " = ? AND " + COL_4 + " = ?" ,new String[]{name, password});
//        Cursor res =  db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_2 + " = ? ", new String[]{name});
        return res;
    }

    public Cursor checkUser(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();

        password = md5(password);
        Cursor res =  db.rawQuery("SELECT " + COL_1 + "," + COL_3 + " FROM " + TABLE_NAME + " WHERE " + COL_2 + " = ? AND " + COL_4 + " = ?", new String[]{email, password});

        return res;
    }

//    public Cursor getBookings(String email){
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor res =  db.rawQuery("SELECT * " + " FROM " + TABLE_NAME + " WHERE " + COL_2 + " = ?", new String[]{email});
//
//        return res;
//    }




//    public Cursor confirmationInfo(){
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_2 + " = ? AND " + COL_4 + " = ?" ,new String[]{name, password});
////        Cursor res =  db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_2 + " = ? ", new String[]{name});
//        return res;
//    }





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