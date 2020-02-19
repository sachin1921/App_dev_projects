package com.example.sofe4640.intents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void runIntent(View v){
        Intent intent = new Intent(this,Main2Activity.class);
        Bundle bundle = new Bundle();
        bundle.putDouble("temp",23.6);
        bundle.putInt("counter",20);
        int [] a = {10,20,30};
        bundle.putIntArray("IntArray",a);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
