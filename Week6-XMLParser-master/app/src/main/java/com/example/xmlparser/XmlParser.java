/*
Author: Anwar Abdalbari
Date: Sept. 30
Purpose: XML parser
 */

package com.example.xmlparser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.widget.LinearLayout;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class XmlParser extends AppCompatActivity {

    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml_parser);

        linearLayout = (LinearLayout) findViewById(R.id.ll1);
        XmlResourceParser parser = getResources().getXml(R.xml.weather);

        try {
            processData(parser);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }


    }

    private void processData(XmlResourceParser parser) throws IOException, XmlPullParserException {
        int eventType = 0;

        while (eventType != XmlResourceParser.END_DOCUMENT){
            if(eventType == XmlResourceParser.START_TAG){
                String name = parser.getName();
                if(name.equals("location")){
                    String city = parser.getAttributeValue(null,"city");
                    String temp = parser.getAttributeValue(null,"temerature");
                    String weather = parser.getAttributeValue(null,"weather");
                    printValues(city, temp, weather);
                }

            }
            eventType = parser.next();
        }
    }

    private void printValues(String city, String temp, String weather) {

    }
}
