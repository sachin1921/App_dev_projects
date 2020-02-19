package com.example.meddocs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Signin extends AppCompatActivity {
    EditText email, pass;
    Button signin;
    DatabaseHelper myDb;
    String user_name, user_phone_num, user_bookings;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.pass);
        signin = (Button) findViewById(R.id.signin);
        myDb = new DatabaseHelper(this);


        viewAll();


    }

    public void viewAll(){
        signin.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(TextUtils.isEmpty(email.getText().toString())){
                            Toast.makeText(Signin.this, "Enter your email to continue ", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else if(TextUtils.isEmpty(pass.getText().toString())){
                            Toast.makeText(Signin.this, "Enter a password to continue ", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else{
                            Cursor doc = myDb.checkUser(email.getText().toString(), pass.getText().toString());
                            if (doc.getCount() == 0){
                                Toast.makeText(Signin.this, "Invalid email or password. Try again", Toast.LENGTH_LONG).show();

                            }
                            else{
                                Toast.makeText(Signin.this, "Sign in successful", Toast.LENGTH_LONG).show();
                                while (doc.moveToNext()){
                                    user_name = doc.getString(0);
                                    user_phone_num = doc.getString(1);
                                }

                                SharedPreferences sp = getSharedPreferences("User", MODE_PRIVATE);
                                SharedPreferences.Editor Ed = sp.edit();
                                Ed.putString("NAME", user_name);
                                Ed.putString("EMAIL", email.getText().toString());
                                Ed.putString("PHONENUM", user_phone_num);
                                Ed.commit();
                                Intent intent = new Intent(Signin.this, showHospitals.class);
                                startActivity(intent);
                            }
                        }
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
