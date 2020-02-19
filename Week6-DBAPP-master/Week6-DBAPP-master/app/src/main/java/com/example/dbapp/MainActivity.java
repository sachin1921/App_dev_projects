/*
Author: Anwar Abdalbari
Date: Oct.  10, 2019
Purpose: Mysql database example
 */
package com.example.dbapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
//products ;
    EditText userInput;
    TextView buckesText;
    MySqlLiteHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // create the database when the app starts
        myDb = new MySqlLiteHelper(this,null,null,1);

        //display the current records in the DB
       displayDatabase();

    }

    //Add new record
    public void addButtonClicked(View v){

        userInput = (EditText) findViewById(R.id.userInputs);
        //create an object from product class
        Products product = new Products (userInput.getText().toString(),10);

        //add new record
        myDb.addRecord(product);

        //display the whole records
        displayDatabase();
        //clear the user's input
        userInput.setText("");
    }

    //delete records
    public void deleteButtonClicked(View v){
        userInput = (EditText) findViewById(R.id.userInputs);
        //call the deleteRecord method
        myDb.deleteRecord(userInput.getText().toString());

        //refresh the rcords after deletion
        displayDatabase();

        //clear the input text
        userInput.setText("");
    }

    //display the records from DB
    private void displayDatabase() {
        String temp = myDb.databaseToString();
        buckesText =  (TextView) findViewById(R.id.buckesText);
        buckesText.setText(temp);

    }
}
