package com.example.kelompok2;


import android.app.DatePickerDialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class InputDataActivity extends AppCompatActivity {

    DataHelper dbHelper;
    Button buttonSimpan;
    EditText editTextNomor, editTextNama, editTextTanggalLahir, editTextAlamat;
    Spinner spinnerJenisKelamin;
    TextView textViewNomor, textViewNama, textViewTanggalLahir, textViewJenisKelamin, textViewAlamat;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);

        dbHelper = new DataHelper(this);

        editTextNomor = findViewById(R.id.edit_text_nomor);
        editTextNama = findViewById(R.id.edit_text_nama);
        editTextTanggalLahir = findViewById(R.id.edit_text_tanggal_lahir);
        spinnerJenisKelamin = findViewById(R.id.spinner_jenis_kelamin);
        editTextAlamat = findViewById(R.id.edit_text_alamat);

        textViewNomor = findViewById(R.id.text_view_nomor);
        textViewNama = findViewById(R.id.text_view_nama);
        textViewTanggalLahir = findViewById(R.id.text_view_tanggal_lahir);
        textViewJenisKelamin = findViewById(R.id.text_view_jenis_kelamin);
        textViewAlamat = findViewById(R.id.text_view_alamat);

        buttonSimpan = findViewById(R.id.button_simpan);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.jenis_kelamin, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerJenisKelamin.setAdapter(adapter);

        String gender = "Laki-Laki";

        buttonSimpan.setOnClickListener(v -> {
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            if (editTextAlamat.getText().toString().isEmpty() || editTextNomor.getText().toString().isEmpty() || editTextNama.getText().toString().isEmpty() || editTextTanggalLahir.getText().toString().isEmpty() || spinnerJenisKelamin.getSelectedItem().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "Kolom tidak boleh kosong...", Toast.LENGTH_SHORT).show();
            } else {

                db.execSQL("insert into biodata(stb, nama, tgl, jk, alamat) values('" +
                        editTextNomor.getText().toString() + "','" +
                        editTextNama.getText().toString() +"','" +
                        editTextTanggalLahir.getText().toString() + "','" +
                        spinnerJenisKelamin.getSelectedItem().toString() + "','" +
                        editTextAlamat.getText().toString() + "')");
                Toast.makeText(getApplicationContext(), "Data Tersimpan...", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        editTextTanggalLahir.setOnClickListener(v -> showDateDialog());

    }
    private void showDateDialog() {
        datePickerDialog = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> editTextTanggalLahir.setText(year + "-" + (month + 1) + "-" + dayOfMonth), 2001, 7, 12);
        datePickerDialog.show();
    }
}