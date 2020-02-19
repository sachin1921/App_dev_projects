package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ViewNote extends AppCompatActivity {

    EditText title;
    EditText content;
    Button back;
    Intent intent;
    Button save, delete;
    NoteClass note;
    NotesDB notesDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);
        intent = getIntent();
        title = (EditText) findViewById(R.id.title);
        content = (EditText) findViewById(R.id.content);
        back = (Button) findViewById(R.id.back);
        save = (Button) findViewById(R.id.saveBtn);
        delete = (Button) findViewById(R.id.deleteBtn);

        String old_title = intent.getStringExtra("title");
        NoteClass note = new NoteClass();
        notesDB = new NotesDB(this);
        note = notesDB.getNote(old_title);
        title.setText(note.getTitle());
        content.setText(note.getContent());
    }
    public void back(View v)
    {
        setResult(RESULT_OK,intent);
        finish();
    }

    public void deleteNote(View v){
        String titleText = title.getText().toString();
        Toast toast=Toast.makeText(getApplicationContext(), titleText,Toast.LENGTH_SHORT);
        toast.setMargin(50,50);
        toast.show();

//        String contentT = content.getText().toString();
//        note = new NoteClass(titleT, contentT);
        notesDB.deleteNote(titleText);

        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }

    public void updateNote(View v){
        String titleT = title.getText().toString();
        String contentT = content.getText().toString();
        note = new NoteClass(titleT, contentT);
        notesDB.updateNote(note);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }



}
