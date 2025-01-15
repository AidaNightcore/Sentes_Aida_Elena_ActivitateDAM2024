package com.example.acvarii;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AcvariuList extends AppCompatActivity {
    private AcvariuAdapter adapter = null;
    public List<Acvariu> acvariuList = new ArrayList<>();
    public AcvariuDatabase acvariuDatabase = null;

    private int idModified=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acvariu_list);

        Intent intent = getIntent();

        acvariuDatabase = Room.databaseBuilder(getApplicationContext(), AcvariuDatabase.class, "AcvariuDatabase").build();

        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                List<Acvariu> acvariuStored = acvariuDatabase.acvariuDAO().getAcvarii();
                acvariuList.addAll(acvariuStored);
            }
        });

        ListView listView = findViewById(R.id.LVAcvariuList);
        adapter = new AcvariuAdapter(getApplicationContext(), acvariuList, R.layout.acvariu_adapter);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent modifyItemIntent = new Intent(getApplicationContext(), AcvariuAdd.class);
                modifyItemIntent.putExtra("acvariu", acvariuList.get(position));
                idModified = position;
                startActivityForResult(modifyItemIntent, 301);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences sharedPreferences = getSharedPreferences("acvariiFavourite", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(String.valueOf(acvariuList.get(position).getIdAcvariu()), acvariuList.get(position).toString());
                editor.apply();
                return false;
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode==301){
            acvariuList.set(idModified, data.getParcelableExtra("acvariu"));
            Toast.makeText(getApplicationContext(), acvariuList.get(0).toString(), Toast.LENGTH_LONG).show();
            adapter.notifyDataSetChanged();
        }
    }
}