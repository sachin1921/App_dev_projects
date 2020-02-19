package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button newNoteB;
    ListView notesList;
    NotesDB notes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newNoteB = (Button) findViewById(R.id.newNoteBtn);
        notesList = (ListView) findViewById(R.id.notesList);
        ArrayList<String> titles = new ArrayList<>();
        notes = new NotesDB(this);
        titles = notes.getTitles();
        ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, titles);
        notesList.setAdapter(adapter);
        notesList.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String title = (String) parent.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, Note.class);;
                intent.putExtra("title", title);
                intent.putExtra("old", "old");
                startActivity(intent);
                int REQUEST_ID=13;

            }
        });
    }

    public void newNote(View v)
    {
        Intent intent = new Intent(this, Note.class);
        startActivity(intent);

    }
}
