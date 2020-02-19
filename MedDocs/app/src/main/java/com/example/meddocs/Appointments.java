package com.example.meddocs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Appointments extends AppCompatActivity {
    ListView appointmentList;
    DatabaseHelper2 appointDb;
    String hospital, doc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);

        appointmentList = (ListView) findViewById(R.id.appointmentList);
        appointDb = new DatabaseHelper2(this);
        Intent intent = getIntent();
        hospital = intent.getStringExtra("CLICKED_HOSPITAL");
        doc = intent.getStringExtra("CLICKED_DOCTOR");

        Toast.makeText(Appointments.this, hospital + " " + doc, Toast.LENGTH_LONG).show();
        final ArrayList<String> appointments = new ArrayList<>();

        Cursor times = appointDb.getAllAppointment(hospital, doc);
        while (times.moveToNext()){
            appointments.add(times.getString(0));
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, appointments);
        appointmentList.setAdapter(null);
        appointmentList.setAdapter(arrayAdapter);
        appointmentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String clicked_time = appointments.get(position).toString();
//                Toast.makeText(Hospitals.this, clicked_hospital, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Appointments.this, Confirmation.class);
                intent.putExtra("CLICKED_TIME", clicked_time);
                intent.putExtra("CLICKED_HOSPITAL", hospital);
                intent.putExtra("CLICKED_DOCTOR", doc);
                startActivity(intent);
            }
        });


    }
}
