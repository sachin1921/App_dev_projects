package com.example.meddocs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button signup, signin;
    DatabaseHelper myDb;
    EditText name, email, phonenum, pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signup = (Button) findViewById(R.id.signup);
        signin = (Button) findViewById(R.id.signin);
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        phonenum = (EditText) findViewById(R.id.phonenum);
        pass = (EditText) findViewById(R.id.pass);
        myDb = new DatabaseHelper(this);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent intent = new Intent(MainActivity.this, Signin.class);
                Intent intent = new Intent(MainActivity.this, Signin.class);
                startActivity(intent);

            }
        });
        AddData();


    }

    public void AddData(){
        signup.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(TextUtils.isEmpty(name.getText().toString())){
                            Toast.makeText(MainActivity.this, "Enter your name to continue ", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else if(TextUtils.isEmpty(email.getText().toString())){
                            Toast.makeText(MainActivity.this, "Enter your email to continue ", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else if(TextUtils.isEmpty(phonenum.getText().toString())){
                            Toast.makeText(MainActivity.this, "Enter your phone number to continue ", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else if(TextUtils.isEmpty(pass.getText().toString())){
                            Toast.makeText(MainActivity.this, "Enter a password to continue ", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else{
                            Cursor doc = myDb.checkUser(email.getText().toString(), pass.getText().toString());
                            if (doc.getCount() == 0){
                                boolean isInserted = myDb.insertData(name.getText().toString(),
                                        email.getText().toString(),
                                        phonenum.getText().toString(),
                                        pass.getText().toString());
                                if(isInserted == true){
                                    SharedPreferences sp = getSharedPreferences("User", MODE_PRIVATE);
                                    SharedPreferences.Editor Ed = sp.edit();
//                                    Ed.putString("METHOD", "Signup");
                                    Ed.putString("NAME", name.getText().toString() );
                                    Ed.putString("EMAIL", email.getText().toString());
                                    Ed.putString("PHONENUM", phonenum.getText().toString());
                                    Ed.commit();
                                    Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(MainActivity.this, showHospitals.class);
                                    intent.putExtra("NAME",name.getText().toString() );
                                    intent.putExtra("EMAIL", email.getText().toString());
                                    intent.putExtra("PHONENUM", phonenum.getText().toString());
                                    startActivity(intent);

                                }

                                else
                                    Toast.makeText(MainActivity.this, "Data Not Inserted", Toast.LENGTH_LONG).show();
                            }
                            else{
                                Toast.makeText(MainActivity.this, "User already exists, sign in", Toast.LENGTH_LONG).show();
                            }

                        }

                    }
                }
        );
    }

    private boolean isEmpty(EditText etText)
    {
        return etText.getText().toString().trim().length() == 0;
    }


}
