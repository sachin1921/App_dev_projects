package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.to_dolist.NotesDB;

public class Notes extends AppCompatActivity {

    Button save;
    EditText title, content;
    com.example.to_dolist.NotesDB notesDB;
    NoteClass note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        save = (Button) findViewById(R.id.saveBtn);
        title = (EditText) findViewById(R.id.title);
        content = (EditText) findViewById(R.id.content);
        notesDB = new NotesDB(this);
    }

    public void save(View v){
        String titleT = title.getText().toString();
        String contentT = content.getText().toString();
        note = new NoteClass(titleT, contentT);
        notesDB.addNote(note);
        Intent intent = new Intent(this, Notes.class);
        startActivity(intent);



    }
}
