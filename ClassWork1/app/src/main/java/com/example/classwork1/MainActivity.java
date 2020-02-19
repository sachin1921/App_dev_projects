package com.example.classwork1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    Button send;
    EditText addrInp, subjectInp, contentInp;
    String addr_str, subject_str, content_str;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        send = (Button) findViewById(R.id.email_btn);
        addrInp = (EditText) findViewById(R.id.addr);
        subjectInp = (EditText) findViewById(R.id.subject);
        contentInp = (EditText) findViewById(R.id.content);


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addr_str = addrInp.getText().toString();
                String emails[] = addr_str.split(",");
                subject_str = subjectInp.getText().toString();
                content_str = contentInp.getText().toString();

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("plain/text");
//                String[] recipients = new String[] {toEmail};
                intent.putExtra(Intent.EXTRA_EMAIL, emails);
                intent.putExtra(Intent.EXTRA_SUBJECT, subject_str);
                intent.putExtra(Intent.EXTRA_TEXT, content_str);
                intent.setType("message/rfc822");
                startActivity(Intent.createChooser(intent,
                        "How do you want to send this message?"));

            }
        });
    }
}
