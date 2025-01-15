package com.example.acvarii;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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