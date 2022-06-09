package com.example.kelompok3;


import android.app.DatePickerDialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class InputDataActivity extends AppCompatActivity {

    DataHelper dbHelper;
    Button buttonSimpan;
    EditText editTextNomor, editTextNama, editTextTanggalLahir, editTextJenisKelamin, editTextAlamat;
    String edit;
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
        editTextJenisKelamin = findViewById(R.id.edit_text_jenis_kelamin);
        editTextAlamat = findViewById(R.id.edit_text_alamat);

        textViewNomor = findViewById(R.id.text_view_nomor);
        textViewNama = findViewById(R.id.text_view_nama);
        textViewTanggalLahir = findViewById(R.id.text_view_tanggal_lahir);
        textViewJenisKelamin = findViewById(R.id.text_view_jenis_kelamin);
        textViewAlamat = findViewById(R.id.text_view_alamat);

        buttonSimpan = findViewById(R.id.button_simpan);

        buttonSimpan.setOnClickListener(v -> {
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            edit = editTextNomor.getText().toString();
            edit = editTextNama.getText().toString();
            edit = editTextTanggalLahir.getText().toString();
            edit = editTextJenisKelamin.getText().toString();
            edit = editTextAlamat.getText().toString();

            if (edit.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Kolom tidak boleh kosong...", Toast.LENGTH_SHORT).show();
            } else {
                db.execSQL("insert into biodata(no, nama, tgl, jk, alamat) values('" +
                        editTextNomor.getText().toString() + "','" +
                        editTextNama.getText().toString() +"','" +
                        editTextTanggalLahir.getText().toString() + "','" +
                        editTextJenisKelamin.getText().toString() + "','" +
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