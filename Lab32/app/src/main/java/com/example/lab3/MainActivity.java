package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button public_btn;
    EditText message;
    String content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        public_btn = (Button) findViewById(R.id.public_btn);
        message = (EditText) findViewById(R.id.message);

    }

    public void public_save(View v){
        String state;
        state = Environment.getExternalStorageState();

        if(Environment.MEDIA_MOUNTED.equals(state)){

            File root = Environment.getExternalStorageDirectory();
            File dir = new File(root.getAbsolutePath() + "/MyAppFile");

            if(!dir.exists()){
                dir.mkdir();
            }
            File file = new File(dir, "My message.txt");
            content= message.getText().toString();

            try {
                FileOutputStream fileOutputStream = null;
                fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(content.getBytes());
                fileOutputStream.close();
                content.setText("");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            Toast.makeText(getApplicationContext(), "SD Card not found", Toast.LENGTH_LONG).show();
        }

    }
}
