package com.example.fooddelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextClock;
import android.widget.TextView;

public class Confirmation extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        Intent intent = getIntent();
        String nameInput = intent.getStringExtra(MainActivity.EXTRA_NAME);
        String emailInput = intent.getStringExtra(MainActivity.EXTRA_EMAIL);
        String addrInput = intent.getStringExtra(MainActivity.EXTRA_ADDRESS);
        int numberInput = intent.getIntExtra(MainActivity.EXTRA_NUMBER, 0);
        double totalValue = intent.getDoubleExtra(MainActivity.EXTRA_TOTAL, 0.0);

        TextView textView1 = (TextView) findViewById(R.id.showName);
        TextView textView2 = (TextView) findViewById(R.id.showEmail);
        TextView textView3 = (TextView) findViewById(R.id.showAddr);
        TextView textView4 = (TextView) findViewById(R.id.showNum);
        TextView textView5 = (TextView) findViewById(R.id.total);

        textView1.setText(nameInput);
        textView2.setText(emailInput);
        textView3.setText(addrInput);
        textView4.setText("" + numberInput);
        textView5.setText("$" + totalValue);

    }
}
