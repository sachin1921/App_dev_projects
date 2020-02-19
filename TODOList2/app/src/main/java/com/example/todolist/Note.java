package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Note extends AppCompatActivity {
    Button saveB;
    EditText titleT;
    EditText contentT;
    NotesDB notesDB;
    NoteClass note;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        notesDB = new NotesDB(this);
        saveB = (Button) findViewById(R.id.saveBtn);
        titleT = (EditText) findViewById(R.id.titleTxt);
        contentT = (EditText)findViewById(R.id.contentTxt);
    }
    public void save(View v)
    {
        String title = titleT.getText().toString();
        String content = contentT.getText().toString();
        note = new NoteClass(title, content);
        notesDB.addNote(note);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
