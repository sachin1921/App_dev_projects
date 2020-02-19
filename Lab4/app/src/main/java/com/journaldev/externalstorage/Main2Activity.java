package com.journaldev.externalstorag

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.andy.myapplication.R;

public class Main2Activity extends AppCompatActivity {
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        save = findViewById(R.id.save);

        save.setOnClickListener();

    }


}
