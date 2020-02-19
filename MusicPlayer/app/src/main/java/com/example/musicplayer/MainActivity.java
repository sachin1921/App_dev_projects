package com.example.musicplayer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText name, length, size, singer, link;
    Button add_button, view_button, update_button, delete_button;
    public static final String EXTRA_TEXT = "com.example.musicplayer.EXTRA_TEXT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);
        name = (EditText) findViewById(R.id.name);
        length = (EditText) findViewById(R.id.length);
        size = (EditText) findViewById(R.id.size);
        singer = (EditText) findViewById(R.id.singer);
        link = (EditText) findViewById(R.id.link);
        add_button = (Button) findViewById(R.id.add_rec);
        view_button = (Button) findViewById(R.id.search_rec);
        update_button = (Button) findViewById(R.id.update_rec);
        delete_button = (Button) findViewById(R.id.delete_rec);
        AddData();
        viewAll();
        UpdateData();
        DeleteData();
    }

    public void UpdateData(){
        update_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.updateData(name.getText().toString(),
                                length.getText().toString(),
                                size.getText().toString(),
                                singer.getText().toString(),
                                link.getText().toString());
                        if (isUpdate == true)
                            Toast.makeText(MainActivity.this, "Data Updated", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Data Not Updated", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void DeleteData(){
        delete_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData(name.getText().toString());
                        if (deletedRows > 0)
                            Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Data Not Deleted", Toast.LENGTH_LONG).show();

                    }
                }
        );
    }



    public void AddData(){
        add_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       boolean isInserted = myDb.insertData(name.getText().toString(),
                                length.getText().toString(),
                               size.getText().toString(),
                               singer.getText().toString(),
                               link.getText().toString());
                       if(isInserted == true)
                           Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                       else
                           Toast.makeText(MainActivity.this, "Data Not Inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void viewAll(){
        view_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if (res.getCount() == 0){
                            showMessage("Error", "Nothing found");
                            return;
                        }

//                        StringBuffer buffer = new StringBuffer();
                        ArrayList<String> arrayList = new ArrayList<>();
                        while (res.moveToNext()){
                            arrayList.add("Song_Name: " + res.getString(0) + "\n");
                            arrayList.add("Song_Length: " + res.getString(1) + "\n");
                            arrayList.add("Song_Size: " + res.getString(2) + "\n");
                            arrayList.add("Song_Singer: " + res.getString(3) + "\n");
                            arrayList.add(res.getString(4) + "\n\n");
                        }
//                        showMessage("Data found", buffer.toString());
                        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                        intent.putStringArrayListExtra(Intent.EXTRA_TEXT, (ArrayList<String>) arrayList);
                        startActivity(intent);
                    }
                }
        );
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}