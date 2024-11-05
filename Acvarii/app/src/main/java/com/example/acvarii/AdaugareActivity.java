package com.example.acvarii;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class AdaugareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adaugare);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Spinner spinnerCapacitate = findViewById(R.id.spinnerCapacitate);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                AdaugareAcvariu.this, // Specifici contextul activității
                R.array.capacitate,
                android.R.layout.simple_spinner_item
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCapacitate.setAdapter(adapter);

        Button btnAdaugare = findViewById(R.id.btnAdaugare);
        btnAdaugare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText etForma = findViewById(R.id.etForma);
                String forma = etForma.getText().toString();

                EditText etGrosime = findViewById(R.id.etGrosime);
                float greutate = etGrosime.getNumber().toString();

                EditText etGrosimeSticla = findViewById(R.id.etGrosimeSticla);
                int grosimeSticla = Integer.parseInt(etGrosimeSticla.getText().toString());

                EditText etPret = findViewById(R.id.etPret);
                float pret = etPret.getNumber().toString();

                String capacitate = spinnerCapacitate.getSelectedItem().toString();

                Acvariu acvariu = new Acvariu(forma, greutate, capacitate, grosimeSticla, pret);
                Intent it = new Intent();
                it.putExtra("acvariu", acvariu);
                setResult(RESULT_OK, it);
                finish();

            }
        });
    }
}