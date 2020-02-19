package com.example.todolist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button newNoteB;
    ListView notesList;
    NotesDB notes;
    int position;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newNoteB = (Button) findViewById(R.id.newNoteBtn);
        notesList = (ListView) findViewById(R.id.notesList);
        ArrayList<String> titles = new ArrayList<>();
        notes = new NotesDB(this);
        titles = notes.getTitles();
        ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,titles);
        notesList.setAdapter(adapter);
        notesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String title = (String) parent.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this,ViewNote.class);
                intent.putExtra("title",title);
                intent.putExtra("old","old");
                int REQUEST_ID=13;
                startActivityForResult(intent,REQUEST_ID);
            }
        });

    }
    public void newNote(View v)
    {
        Intent intent = new Intent(this, Note.class);
        startActivity(intent);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState){
        super.onSaveInstanceState(outState, outPersistentState);
        position = notesList.getFirstVisiblePosition();
        outState.putInt("key", position);
    }

    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistenceState){
        super.onRestoreInstanceState(savedInstanceState, persistenceState);
        int key = savedInstanceState.getInt("key");
        notesList.smoothScrollToPosition(key);
    }
}

