package com.example.acvarii;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Acvariu> Acvarii;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Acvarii = new ArrayList<>();


        Button btnAdaugare = findViewById(R.id.adaugaAcvariuMeniu);
        btnAdaugare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, AdaugaAcvariu.class);
                startActivityForResult(it, 403);
            }
        });


        Button btnLista = findViewById(R.id.openList);
        btnLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, ListAcvarii.class);
                it.putParcelableArrayListExtra("Acvarii", (ArrayList<? extends Parcelable>) Acvarii);
                startActivity(it);
            }
        });


        Button btnImagini = findViewById(R.id.openImageList);
        btnImagini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, ListImages.class);
                startActivity(it);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 403) {
            if (resultCode == RESULT_OK && data != null) {
                Acvariu acvariu = data.getParcelableExtra("acvariu");
                if (acvariu != null) {
                    Acvarii.add(acvariu);
                    Toast.makeText(getApplicationContext(), "Adaugat: " + acvariu.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
