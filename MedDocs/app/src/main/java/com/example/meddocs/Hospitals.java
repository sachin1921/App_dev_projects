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

public class Hospitals extends AppCompatActivity {

    ListView hospitalList;
    DatabaseHelper2 hospitalDb;
    public static final String EXTRA_TEXT = "com.example.meddocs.EXTRA_TEXT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospitals);

        hospitalList = (ListView) findViewById(R.id.hospitalList);
        hospitalDb = new DatabaseHelper2(this);
        final ArrayList<String> hospitals = new ArrayList<>();

        hospitalDb.deleteAll();
        hospitalDb.insertData("Dawes Family Practice And Walk-In", "Dave Simon", "10/11/2019 at 10:00");
        hospitalDb.insertData("Dawes Family Practice And Walk-In", "David Tenant", "10/11/2019 at 12:00");
        hospitalDb.insertData("Dawes Family Practice And Walk-In", "Derek Shephard", "10/11/2019 at 11:00");

        hospitalDb.insertData("Patient Networks Family Medicine Walk In Clinic", "Bruce Wayne", "10/11/2019 at 10:00");
        hospitalDb.insertData("Patient Networks Family Medicine Walk In Clinic", "Dr. Nick", "11/11/2019 at 12:00");
        hospitalDb.insertData("Patient Networks Family Medicine Walk In Clinic", "Dr. Nick", "11/11/2019 at 14:00");

        hospitalDb.insertData("Wellpoint Family Practice", "Dr. Miranda Bailey", "10/11/2019 at 12:00");
        hospitalDb.insertData("Wellpoint Family Practice", "Dr. Miranda Bailey", "11/11/2019 at 12:00");
        hospitalDb.insertData("Wellpoint Family Practice", "Dr. Frasier Crane", "12/11/2019 at 15:00");

        hospitalDb.insertData("Beaches Family Practice", "Dr. John Carter", "10/11/2019 at 15:30");
        hospitalDb.insertData("Beaches Family Practice", "Dr. John Carter", "14/11/2019 at 12:00");
        hospitalDb.insertData("Beaches Family Practice", "Dr. John Dorian", "12/11/2019 at 15:00");

        hospitalDb.insertData("Davisville Family Practice", "Dr. Doogie Howser", "11/11/2019 at 15:30");
        hospitalDb.insertData("Davisville Family Practice", "Dr. Doogie Howser", "14/11/2019 at 12:00");
        hospitalDb.insertData("Davisville Family Practice", "Dr. Gregory House", "12/11/2019 at 15:00");




        Cursor res = hospitalDb.getAllHospital();
        if (res.getCount() == 0){
            Toast.makeText(Hospitals.this, "Error", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(Hospitals.this, "Hospitals found", Toast.LENGTH_LONG).show();

        }

        while (res.moveToNext()){
            hospitals.add(res.getString(0));
        }



        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, hospitals);
        hospitalList.setAdapter(null);
        hospitalList.setAdapter(arrayAdapter);


        hospitalList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String clicked_hospital = hospitals.get(position).toString();
//                Toast.makeText(Hospitals.this, clicked_hospital, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Hospitals.this, Doctors.class);
                intent.putExtra("CLICKED_HOSPITAL", clicked_hospital);
                startActivity(intent);

            }
        });

    }
}
