package com.example.acvarii;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class FavouriteAcvariu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_acvariu);

        ListView listViewFavorites = findViewById(R.id.listViewFavorites);

        SharedPreferences sharedPreferences = getSharedPreferences("FavoriteAcvarii", MODE_PRIVATE);
        Set<String> favoritesSet = sharedPreferences.getStringSet("favorites", new HashSet<>());
        ArrayList<String> favoritesList = new ArrayList<>(favoritesSet);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, favoritesList);
        listViewFavorites.setAdapter(adapter);
    }
}