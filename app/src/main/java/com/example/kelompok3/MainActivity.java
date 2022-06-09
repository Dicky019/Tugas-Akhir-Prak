package com.example.kelompok3;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonLihatData, buttonInputData, buttonInformasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLihatData = findViewById(R.id.button_lihat_data);
        buttonInputData = findViewById(R.id.button_input_data);
        buttonInformasi = findViewById(R.id.button_informasi);

        buttonLihatData.setOnClickListener(this);
        buttonInputData.setOnClickListener(this);
        buttonInformasi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == buttonLihatData) {
            Intent intentLihatData = new Intent(MainActivity.this, DataMahasiswa.class);
            startActivity(intentLihatData);
        } else if (v == buttonInputData) {
            Intent intentInputData = new Intent(MainActivity.this, InputDataActivity.class);
            startActivity(intentInputData);
        } else if (v == buttonInformasi) {
            Intent intentInformasi = new Intent(MainActivity.this, InformasiActivity.class);
            startActivity(intentInformasi);
        }
    }
}