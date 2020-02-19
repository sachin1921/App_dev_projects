/*
Author: Anwar Abdalbari
Date: Sept. 30
Purpose: XML parser
 */
package com.example.xmlparser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Handle the button click
    public void runParser(View v){

        //call Activity 2 using an intent
        Intent intent= new Intent(this,XmlParser.class);
        startActivity(intent);
    }
}
