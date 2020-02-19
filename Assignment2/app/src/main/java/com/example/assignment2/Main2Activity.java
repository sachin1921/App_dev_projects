package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    Button start, stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        final ArrayList<String> test = getIntent().getStringArrayListExtra(Intent.EXTRA_TEXT);

//        String url = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"; // your URL here
//        final MediaPlayer mediaPlayer = new MediaPlayer();
//        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//        try {
//            mediaPlayer.setDataSource(url);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            mediaPlayer.prepare(); // might take long! (for buffering, etc)
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        start = (Button) findViewById(R.id.start);
//        stop = (Button) findViewById(R.id.stop);

//        start.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        mediaPlayer.start();
//
//                    }
//                }
//        );
//
//        stop.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        mediaPlayer.stop();
//                    }
//                }
//        );

    }
}
