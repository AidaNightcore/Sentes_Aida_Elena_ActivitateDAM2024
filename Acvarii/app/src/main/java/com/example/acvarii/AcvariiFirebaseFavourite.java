package com.example.acvarii;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AcvariiFirebaseFavourite extends AppCompatActivity {
    private List<Acvariu> acvariuFirebaseFavouriteList = new ArrayList<>();
    private AcvariuAdapter acvariuAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acvarii_firebase_favourite);

        ListView listView = findViewById(R.id.LVFirebaseFavourites);
        acvariuAdapter = new AcvariuAdapter(this, acvariuFirebaseFavouriteList, R.layout.acvariu_adapter);
        listView.setAdapter(acvariuAdapter);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("acvarii");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                acvariuFirebaseFavouriteList.clear();
                for (DataSnapshot data : snapshot.getChildren()) {
                    Acvariu acvariu = data.getValue(Acvariu.class);
                    acvariuFirebaseFavouriteList.add(acvariu);
                }
                acvariuAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AcvariiFirebaseFavourite.this, "Eroare: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}