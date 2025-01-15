package com.example.acvarii;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Acvariu> acvariuList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAdaugaAcvariu = findViewById(R.id.BTNAdaugaAcvariu);
        btnAdaugaAcvariu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), AcvariuAdd.class);
                startActivityForResult(it, 403);
            }
        });

        Button btnAcvariuLista = findViewById(R.id.BTNListaAcvariu);
        btnAcvariuLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), AcvariuList.class);
                it.putParcelableArrayListExtra("acvarii", (ArrayList<? extends Parcelable>) acvariuList);
                startActivity(it);
            }
        });

        Button btnImageActivity = findViewById(R.id.BTNImaginiAcvariu);
        btnImageActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), ImageActivity.class);
                startActivity(it);
            }
        });

        Button btnJsonActivity = findViewById(R.id.BTNJsonAcvariu);
        btnJsonActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), JsonAcvitity.class);
                startActivity(it);
            }
        });

        Button btnXmlActivity = findViewById(R.id.BTNXMLAcvariu);
        btnXmlActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), XmlActivity.class);
                startActivity(it);
            }
        });

        Button btnFavouriteActivity = findViewById(R.id.BTNAcvariuFavourites);
        btnFavouriteActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), AcvariiFavourite.class);
                startActivity(it);
            }
        });

        Button btnFirebaseActivity = findViewById(R.id.BTNAcvariuFirebase);
        btnFirebaseActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), AcvariiFirebaseFavourite.class);
                startActivity(it);
            }
        });


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("acvarii");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Toast.makeText(MainActivity.this, "Modificări detectate în Firebase!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Eroare: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode==403){
            Acvariu acvariu = data.getParcelableExtra("acvariu");
            acvariuList.add(acvariu);
            Toast.makeText(getApplicationContext(), acvariu.toString(), Toast.LENGTH_LONG).show();
        }
    }
}