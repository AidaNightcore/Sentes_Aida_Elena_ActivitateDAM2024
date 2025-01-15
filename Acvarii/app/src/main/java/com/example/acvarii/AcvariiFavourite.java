package com.example.acvarii;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AcvariiFavourite extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acvarii_favourite);
        SharedPreferences sharedPreferences = getSharedPreferences("acvariiFavourite", MODE_PRIVATE);
        Map<String, String> dictionary = (Map<String, String>) sharedPreferences.getAll();
        List<String> favouritesList = new ArrayList<>();

        for (Map.Entry<String, String> object:dictionary.entrySet()){
            favouritesList.add(object.getValue());
        }

        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, favouritesList);
        ListView listView = findViewById(R.id.LVFavourites);
        listView.setAdapter(adapter);
    }
}