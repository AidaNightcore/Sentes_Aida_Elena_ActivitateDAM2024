package com.example.acvarii;

import static android.content.Intent.getIntent;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListAcvarii extends AppCompatActivity {
    private com.example.acvarii.AcvariuAdapter adapter = null;
    private int idModificat = 0;

    List<Acvariu> Acvariue = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_acvarii);
        Intent it = getIntent();
        Acvariue = it.getParcelableArrayListExtra("Acvarii");

        ListView lv = findViewById(R.id.listAcvariuLV);

        adapter = new AcvariuAdapter(Acvariue, getApplicationContext(), R.layout.activity_list_acvarii);
        lv.setAdapter(adapter);

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Acvariu acvariu = (Acvariu) parent.getItemAtPosition(position);

                SharedPreferences sharedPreferences = getSharedPreferences("FavoriteAcvarii", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                Set<String> favorites = sharedPreferences.getStringSet("favorites", new HashSet<>());
                favorites.add(acvariu.toString()); 
                editor.putStringSet("favorites", favorites);
                editor.apply();

                Toast.makeText(ListAcvarii.this, "Adaugat la favorite", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
//
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent modificaAcvariuIntent = new Intent(getApplicationContext(), AdaugaAcvariu.class);
//                modificaAcvariuIntent.putExtra("Acvariu" , Acvariue.get(position));
//                int idModificat = position;
//                startActivityForResult(modificaAcvariuIntent, 234);
//
//            }
//        });
//
//        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                Acvariue.remove(position);
//                adapter.notifyDataSetChanged();
//                return false;
//            }
//        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode==234){
            Acvariue.set(idModificat, data.getParcelableExtra("Acvariu"));
            adapter.notifyDataSetChanged();
        }
    }
}

