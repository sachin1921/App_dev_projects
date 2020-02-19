package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    ListView listView;
    Button start, stop, pause;
    List<String> list = new ArrayList<>();
    List<String> links = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        listView = (ListView) findViewById(R.id.list);
        ArrayList<String> arrayList = new ArrayList<>();
        start = (Button) findViewById(R.id.start_btn);
        stop = (Button) findViewById(R.id.stop_btn);
        pause = (Button) findViewById(R.id.pause_btn);

        Intent intent = getIntent();
        final ArrayList<String> test = getIntent().getStringArrayListExtra(Intent.EXTRA_TEXT);

        for (int i = 0; i < test.size(); i++) {
            if (i % 5 == 0) {
                list.add(test.get(i));
            }
            if(test.get(i).contains("Song_") != true){
                links.add(test.get(i));
            }
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(arrayAdapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Main2Activity.this, links.get(position).toString(), Toast.LENGTH_LONG).show();
                final String url = links.get(position).toString();
                final MediaPlayer mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                try {
                    mediaPlayer.setDataSource(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    mediaPlayer.prepare(); // might take long! (for buffering, etc)
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                Toast.makeText(Main2Activity.this, "Ready", Toast.LENGTH_LONG).show();
                mediaPlayer.start();
                stop.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mediaPlayer.stop();
                            }
                        }
                );
                pause.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(mediaPlayer.isPlaying()){
                                    mediaPlayer.pause();
                                } else {
                                    Toast.makeText(Main2Activity.this, "Music is not playing", Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                );
                start.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(mediaPlayer.isPlaying()!= true){
                                    mediaPlayer.start();
                                } else {
                                    Toast.makeText(Main2Activity.this, "Music is already playing", Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                );


            }
        });

    }
}
