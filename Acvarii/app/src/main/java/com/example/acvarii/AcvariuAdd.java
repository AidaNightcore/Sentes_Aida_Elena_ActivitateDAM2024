package com.example.acvarii;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AcvariuAdd extends AppCompatActivity {
    List<Acvariu> acvariuList = new ArrayList<>();
    public AcvariuDatabase acvariuDatabase = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acvariu_add);

        Intent intent = getIntent();

        if(intent.hasExtra("acvariu")){
            Acvariu acvariu = intent.getParcelableExtra("acvariu");
            EditText etNumePesti = findViewById(R.id.ETNumePesti);
            EditText etNrPesti = findViewById(R.id.ETnumarPesti);
            EditText etMarimeAcvariu = findViewById(R.id.ETMarimeAcvariu);
            Spinner spBrandAcvariu = findViewById(R.id.SPBrandAcvariu);

            String [] brandsArray = getResources().getStringArray(R.array.brandAcvariuArray);
            int brandAles = Arrays.asList(brandsArray).indexOf(acvariu.getBrandAcvariu());
            spBrandAcvariu.setSelection(brandAles);

            etNumePesti.setText(String.valueOf(acvariu.getNumePesti()));
            etNrPesti.setText(String.valueOf(acvariu.getNrPesti()));
            etMarimeAcvariu.setText(String.valueOf(acvariu.getMarimeAcvariu()));
        }

        acvariuDatabase = Room.databaseBuilder(getApplicationContext(), AcvariuDatabase.class, "AcvariuDatabase").build();

        Button BTNAdaugaAcvariu = findViewById(R.id.BTNAdauga);
        BTNAdaugaAcvariu.setOnClickListener((view)->{
            EditText etNumePesti = findViewById(R.id.ETNumePesti);
            String numePesti = etNumePesti.getText().toString();

            EditText etNrPesti = findViewById(R.id.ETnumarPesti);
            int nrPesti = Integer.parseInt(etNrPesti.getText().toString());

            EditText etMarimeAcvariu = findViewById(R.id.ETMarimeAcvariu);
            int marimeAcvariu = Integer.parseInt(etMarimeAcvariu.getText().toString());

            Spinner spBrandAcvariu = findViewById(R.id.SPBrandAcvariu);
            String brandAvariu = spBrandAcvariu.getSelectedItem().toString();

            Acvariu acvariu = new Acvariu(numePesti, nrPesti, marimeAcvariu, brandAvariu);

            // Write a message to the database
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("message");

            myRef.setValue("Hello, World!");

            Executor executor = Executors.newSingleThreadExecutor();
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    acvariuDatabase.acvariuDAO().insertAcvariu(acvariu);
                }
            });

            Intent it = new Intent();
            it.putExtra("acvariu", acvariu);
            setResult(RESULT_OK, it);
            finish();
        });
    }
}