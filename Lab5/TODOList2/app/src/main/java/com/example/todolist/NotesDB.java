package com.example.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class NotesDB extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "NotesDB";
    private static final String TABLE_NAME = "NOTES";
    private static final String COL_ID = "id";
    private static final String COL_TITLE = "title";
    private static final String COL_CONTENT = "content";
    String old_title;

    public NotesDB(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_NOTES_TABLE = "CREATE TABLE "+TABLE_NAME+"("+COL_ID+" INTEGER PRIMARY KEY,"+COL_TITLE+" TEXT,"+COL_CONTENT+" TEXT)";
        db.execSQL(CREATE_NOTES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    void addNote(NoteClass note)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_TITLE, note.getTitle());
        values.put(COL_CONTENT, note.getContent());
        old_title = note.getTitle();
        db.insert(TABLE_NAME, null,values);
        db.close();
    }

    NoteClass getNote(String title)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        NoteClass note = new NoteClass();
        //Assume no duplicate titles
        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+COL_TITLE+" = ?",new String [] {title});
        c.moveToFirst();
        if(c.isNull(c.getColumnIndex(COL_TITLE)))
        {
            db.close();
            return null;
        }
        note.setTitle(c.getString(c.getColumnIndex(COL_TITLE)));
        note.setContent(c.getString(c.getColumnIndex(COL_CONTENT)));
        db.close();
        return note;
    }

    ArrayList<String> getTitles()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String temptTitle = "";
        ArrayList<String> titles = new ArrayList<>();
        String query = "SELECT "+ COL_TITLE +" FROM "+TABLE_NAME;
        //Assume no duplicate titles
        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();
        while(!(c.isAfterLast()))
        {
            temptTitle = c.getString(c.getColumnIndex(COL_TITLE));
            titles.add(temptTitle);
            c.moveToNext();
        }
        db.close();
        return titles;
    }

    void updateNote(NoteClass note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_TITLE, note.getTitle());
        values.put(COL_CONTENT, note.getContent());
        System.out.print(note.getContent());
        db.update(TABLE_NAME, values, COL_TITLE + "=" + note.getTitle(), null);


        db.close();
    }

    void deleteNote(String title){
        SQLiteDatabase db = this.getReadableDatabase();
//         note = new NoteClass();
        //Assume no duplicate titles
        db.delete(TABLE_NAME, COL_TITLE + " LIKE ?", new String[] {title});
//        db.execSQL(query);
        db.close();
    }
}
