package com.example.meddocs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Cancellation extends AppCompatActivity {

    ListView bookingsList;
    DatabaseHelper myDb;
    String user_bookings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancellation);

//        bookingsList = (ListView) findViewById(R.id.appointmentBooked);
//        myDb = new DatabaseHelper(this);
//
//
//
//        SharedPreferences sp = getSharedPreferences("User", MODE_PRIVATE);
//
//        String user_email = sp.getString("EMAIL", null);
//        Toast.makeText(Cancellation.this, user_email, Toast.LENGTH_LONG).show();
//
//
//        Cursor bookings = myDb.getBookings(user_email);
//        if (bookings.getCount() == 0){
//            Toast.makeText(Cancellation.this, "Error", Toast.LENGTH_LONG).show();
//        }
//        else {
//            Toast.makeText(Cancellation.this, "Found records", Toast.LENGTH_LONG).show();
//
//            while (bookings.moveToNext()){
//                user_bookings = bookings.getString(4);
//            }
//            Toast.makeText(Cancellation.this, user_bookings, Toast.LENGTH_LONG).show();
//
//        }


    }
}
