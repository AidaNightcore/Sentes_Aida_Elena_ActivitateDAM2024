package com.example.acvarii;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class JsonAcvitity extends AppCompatActivity {

    List<Acvariu> acvariuList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_acvitity);

        String link = "https://gist.githubusercontent.com/AidaNightcore/cf498f442e744f3c9f1776e3a7321eee/raw/fcb7873c8033e8c7810b24f0567875523635d688/itemsExample.json";

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(link);
                    connection= (HttpURLConnection) url.openConnection();

                    InputStream inputStream = connection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    StringBuilder stringBuilder = new StringBuilder();

                    String line = null;

                    while ((line = bufferedReader.readLine())!=null){
                        stringBuilder.append(line);
                    }

                    JSONObject jsonObject = new JSONObject(stringBuilder.toString());
                    JSONArray jsonArray = jsonObject.getJSONArray("jsonItems");

                    for(int i = 0; i< jsonArray.length(); i++)
                    {
                        JSONObject jsonAcvariu = jsonArray.getJSONObject(i);

                        String numePesti = jsonAcvariu.getString("stringAttribute");
                        Integer nrPesti = Integer.valueOf(jsonAcvariu.getString("intAttribute"));
                        Integer marimeAcvariu = Integer.valueOf(jsonAcvariu.getString("intAttribute"));
                        String brandAcvariu =jsonAcvariu.getString("stringArrayListAttribute");

                        Acvariu acvariu = new Acvariu(numePesti, nrPesti, marimeAcvariu, brandAcvariu);
                        acvariuList.add(acvariu);
                    }

                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                } catch (Exception e) {
                    Log.e("JsonAcvitity", "Exception: " + e.getMessage());
                    e.printStackTrace();
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        ListView listView = findViewById(R.id.LVJsonList);
                        ArrayAdapter<Acvariu> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, acvariuList);
                        listView.setAdapter(adapter);
                    }
                });
            }
        });
    }

}