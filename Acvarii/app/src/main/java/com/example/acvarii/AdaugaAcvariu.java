package com.example.acvarii;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileOutputStream;
import java.io.IOException;

public class AdaugaAcvariu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adaugare);


        Spinner spinnerCapacitate = findViewById(R.id.spinnerCapacitate);

        String[] optiuniCapacitate = {"10L", "20L", "50L"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, optiuniCapacitate);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCapacitate.setAdapter(adapter);

        Button btnAdaugare = findViewById(R.id.btnAdaugare);
        btnAdaugare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    EditText etForma = findViewById(R.id.etForma);
                    String forma = etForma.getText().toString();

                    EditText etGreutate = findViewById(R.id.etGreutate);
                    float greutate = Float.parseFloat(etGreutate.getText().toString());

                    EditText etGrosimeSticla = findViewById(R.id.etGrosimeSticla);
                    int grosimeSticla = Integer.parseInt(etGrosimeSticla.getText().toString());

                    EditText etPret = findViewById(R.id.etPret);
                    float pret = Float.parseFloat(etPret.getText().toString());

                    String capacitate = spinnerCapacitate.getSelectedItem().toString();

                    Acvariu acvariu = new Acvariu(forma, greutate, capacitate, grosimeSticla, pret);


                    String fileName = "acvarii.txt";
                    String acvariuData = acvariu.toString() + "\n";
                    FileOutputStream fos = openFileOutput(fileName, MODE_APPEND);
                    fos.write(acvariuData.getBytes());
                    fos.close();

                    Intent it = new Intent();
                    it.putExtra("acvariu", acvariu);
                    setResult(RESULT_OK, it);
                    finish();
                } catch (NumberFormatException | IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
