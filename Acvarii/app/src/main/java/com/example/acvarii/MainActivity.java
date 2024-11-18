package com.example.acvarii;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Acvariu> Acvarii = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


//        Button btnAdaugare = findViewById(R.id.adaugaAcvariuMeniu);
//        btnAdaugare.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent it = new Intent(getApplicationContext(), AdaugaAcvariu.class);
//                startActivityForResult(it, 403);
//            }
//        });
        Button btnLista = findViewById(R.id.openList);
        btnLista.setOnClickListener((View view)->{
            Intent it = new Intent(getApplicationContext(), ListAcvarii.class);
            it.putParcelableArrayListExtra("Acvarii", (ArrayList<? extends Parcelable>) Acvarii);
            startActivity(it);
        });

        Button btnImagini = findViewById(R.id.openImageList);
        btnImagini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(), ListImages.class);
                startActivity(it);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 345){
            if(resultCode == RESULT_OK){
                Acvariu Acvariu = data.getParcelableExtra("Acvariu");
                Acvarii.add(Acvariu);
                Toast.makeText(getApplicationContext(), Acvariu.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }
}