package com.example.acvarii;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AcvariuList extends AppCompatActivity {
    private AcvariuAdapter adapter = null;
    public List<Acvariu> acvariuList = new ArrayList<>();
    public AcvariuDatabase acvariuDatabase = null;

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
    }
}