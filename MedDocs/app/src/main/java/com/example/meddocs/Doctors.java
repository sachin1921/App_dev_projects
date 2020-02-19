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

public class Doctors extends AppCompatActivity {

    ListView doctorsList;
    DatabaseHelper2 doctorDb;
    String hospital_doc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors);
        doctorsList = (ListView) findViewById(R.id.doctorsList);
        doctorDb = new DatabaseHelper2(this);
        Intent intent = getIntent();
        hospital_doc = intent.getStringExtra("CLICKED_HOSPITAL");
        Toast.makeText(Doctors.this, hospital_doc, Toast.LENGTH_LONG).show();
        final ArrayList<String> doctors = new ArrayList<>();


        Cursor doc = doctorDb.getAllDoctor(hospital_doc);
        if (doc.getCount() == 0){
            Toast.makeText(Doctors.this, "Error", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(Doctors.this, "Doc found", Toast.LENGTH_LONG).show();
        }
        while (doc.moveToNext()){
            doctors.add(doc.getString(0));
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, doctors);
        doctorsList.setAdapter(null);
        doctorsList.setAdapter(arrayAdapter);

        doctorsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String clicked_doctors = doctors.get(position).toString();
//                Toast.makeText(Hospitals.this, clicked_hospital, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Doctors.this, Appointments.class);
                intent.putExtra("CLICKED_DOCTOR", clicked_doctors);
                intent.putExtra("CLICKED_HOSPITAL", hospital_doc);
                startActivity(intent);
            }
        });
    }
}
