//package com.example.acvarii;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Spinner;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//
//public class AdaugaAcvariu extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_adaugare);
//
//        Spinner spinnerCapacitate = findViewById(R.id.spinnerCapacitate);
//
//        spinnerCapacitate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerCapacitate.setAdapter(adapter);
//
//        Button btnAdaugare = findViewById(R.id.btnAdaugare);
//        btnAdaugare.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                EditText etForma = findViewById(R.id.etForma);
//                String forma = etForma.getText().toString();
//
//                EditText etGrosime = findViewById(R.id.etGrosime);
//                float greutate = etGrosime.getNumber().toString();
//
//                EditText etGrosimeSticla = findViewById(R.id.etGrosimeSticla);
//                int grosimeSticla = Integer.parseInt(etGrosimeSticla.getText().toString());
//
//                EditText etPret = findViewById(R.id.etPret);
//                float pret = etPret.getNumber().toString();
//
//                String capacitate = spinnerCapacitate.getSelectedItem().toString();
//
//                Acvariu acvariu = new Acvariu(forma, greutate, capacitate, grosimeSticla, pret);
//                Intent it = new Intent();
//                it.putExtra("acvariu", acvariu);
//                setResult(RESULT_OK, it);
//                finish();
//
//            }
//        });
//    }
//}