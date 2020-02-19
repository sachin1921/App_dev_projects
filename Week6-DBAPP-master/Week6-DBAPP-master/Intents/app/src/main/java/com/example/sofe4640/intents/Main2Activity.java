package com.example.sofe4640.intents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Bundle bundle = getIntent().getExtras();
        int counter = bundle.getInt("counter");
        double tempreture = bundle.getInt("temp");
        int myArray[]  = bundle.getIntArray("IntArray");

        String finalCounter = new Integer(counter).toString();
        TextView textViewInteger = (TextView) findViewById(R.id.lblInteger);
        textViewInteger.setText(finalCounter);


        String finatempreture = new Double(tempreture).toString();
        TextView textViewTempreture = (TextView) findViewById(R.id.lblTempreture);
        textViewTempreture.setText(finatempreture);




    }


    public void backToMain(View v){
        setContentView(R.layout.activity_main);

    }
}
