package com.example.dbapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MySqlLiteHelper extends SQLiteOpenHelper {

    //declare the class's instance variables
    private static final String DATABASE_NAME = "products.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "product";
    private static final String COL_1 ="id" ;
    private static final String COL_2 = "productName";
    private static final String COL_3 = "productPrice";


    public MySqlLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
         String createTable = "CREATE TABLE " + TABLE_NAME + "(" +
                 COL_1 + " Integer PRIMARY KEY AUTOINCREMENT," +
                 COL_2 + " Text NOT NULL,"+
                 COL_3 + " number DEFAULT 0)" +";" ;
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      db.execSQL("Drop table " + TABLE_NAME+ ";");
      this.onCreate(db);
    }

    //add record method
    public void addRecord(Products product){
        ContentValues values = new ContentValues();
        values.put(COL_2,product.getProductName());
        values.put(COL_3,product.getProductPrice());
        //SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase db = null;
        try {
            db = this.getWritableDatabase();
            // Insert the new entry into the DB.
            db.insert(TABLE_NAME,null,values);
        } finally {
           db.close();
        }

    }

    //delete record method
    public void  deleteRecord(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("Delete from " + TABLE_NAME + " where " + COL_2 + "='"  + name +"';");

        db.close();
    }


    //select all records method
    public  String databaseToString(){

        String result= "";

        SQLiteDatabase db = getWritableDatabase();
        String query ="Select "+ COL_2 + "  from " + TABLE_NAME +";";
        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();

        while( !c.isAfterLast()){
            result += c.getString(c.getColumnIndex(COL_2));
            result +="\n";
            Log.d("select","value : "+result);
            c.moveToNext();
        }
        db.close();
        return result;
    }
}
