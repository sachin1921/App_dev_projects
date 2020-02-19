package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ViewNote extends AppCompatActivity {

    EditText title;
    EditText content;
    Button back;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);
        intent = getIntent();
        title = (EditText) findViewById(R.id.titleTxt);
        content = (EditText) findViewById(R.id.contentTxt);
        back = (Button) findViewById(R.id.back);
        String old_title = intent.getStringExtra("title");
        NoteClass note = new NoteClass();
        NotesDB notesDB = new NotesDB(this);
        note = notesDB.getNote(old_title);
        title.setText(note.getTitle());
        content.setText(note.getContent());
    }
    public void back(View v)
    {

        setResult(RESULT_OK);
        finish();
    }
}

