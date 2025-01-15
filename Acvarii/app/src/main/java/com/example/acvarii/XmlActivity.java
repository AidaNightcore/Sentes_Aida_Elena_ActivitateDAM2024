package com.example.acvarii;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class XmlActivity extends AppCompatActivity {
    List<Acvariu> acvariuList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml);

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());

        String link = "https://gist.githubusercontent.com/AidaNightcore/cf498f442e744f3c9f1776e3a7321eee/raw/0c023e862b400baa7d7f3055b32e9846c94653be/itemsExample.xml" ;

        executor.execute(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(link);
                    connection = (HttpURLConnection) url.openConnection();

                    InputStream inputStream = connection.getInputStream();

                    XmlPullParser parser = android.util.Xml.newPullParser();
                    parser.setInput(inputStream, null);

                    String tagName = null;
                    String numePesti = null ;
                    String brandAcvariu = null;
                    Integer nrPesti = null;
                    Integer marimeAcvariu = null;

                    int eventType = parser.getEventType();
                    while(eventType!=XmlPullParser.END_DOCUMENT){
                        switch (eventType){
                            case XmlPullParser.START_TAG:
                                tagName = parser.getName();
                                if(tagName.equals("item")){
                                    numePesti = null;
                                    nrPesti = null;
                                    marimeAcvariu= null;
                                    brandAcvariu = null;
                                }
                                break;
                            case XmlPullParser.TEXT:
                                String text = parser.getText();
                                if (tagName!=null){
                                    switch (tagName){
                                        case "stringAttribute":
                                            numePesti = text;
                                            break;
                                        case "intAttribute":
                                            nrPesti = Integer.valueOf(text);
                                            marimeAcvariu = Integer.valueOf(text);
                                            break;

                                        case "stringArrayAttribute":
                                            brandAcvariu = text;
                                            break;
                                    }
                                }
                                break;
                            case XmlPullParser.END_TAG:
                                if(parser.getName().equals("item")){
                                    Acvariu acvariu = new Acvariu(numePesti, nrPesti, marimeAcvariu, brandAcvariu);
                                    acvariuList.add(acvariu);
                                }
                                tagName= null;
                                break;
                        }
                        eventType = parser.next();

                    }

                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                catch (IOException | XmlPullParserException e) {
                    Log.e("XMLParsing", "Exception: " + e.getMessage());
                    e.printStackTrace();
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        ListView listView = findViewById(R.id.LVXmlList);
                        ArrayAdapter<Acvariu> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, acvariuList);
                        listView.setAdapter(adapter);
                    }
                });
            }
        });
    }
}