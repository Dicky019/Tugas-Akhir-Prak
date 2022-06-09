package com.example.kelompok2;


import android.app.DatePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateData extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbHelper;
    Button buttonSimpan;
    EditText editTextNomor, editTextNama, editTextTanggalLahir, editTextJenisKelamin, editTextAlamat;
    TextView textViewNomor, textViewNama, textViewTanggalLahir, textViewJenisKelamin, textViewAlamat;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

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

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM biodata WHERE nama = '" + getIntent().getStringExtra("nama") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            editTextNomor.setText(cursor.getString(1).toString());
            editTextNama.setText(cursor.getString(2).toString());
            editTextTanggalLahir.setText(cursor.getString(3).toString());
            editTextJenisKelamin.setText(cursor.getString(4).toString());
            editTextAlamat.setText(cursor.getString(5).toString());
        }

        buttonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                if (editTextAlamat.getText().toString().isEmpty() || editTextNomor.getText().toString().isEmpty() || editTextNama.getText().toString().isEmpty() || editTextTanggalLahir.getText().toString().isEmpty() || editTextJenisKelamin.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Kolom tidak boleh kosong...", Toast.LENGTH_SHORT).show();
                } else {
                    db.execSQL("update biodata set stb='"  +
                            editTextNomor.getText().toString() + "',  nama='" +
                            editTextNama.getText().toString() + "', tgl='" +
                            editTextTanggalLahir.getText().toString() + "', jk='" +
                            editTextJenisKelamin.getText().toString() + "', alamat='" +
                            editTextAlamat.getText().toString() + "' where no='" +
                            cursor.getString(0).toString() + "'");
                    Toast.makeText(getApplicationContext(), "Perubahan Tersimpan...", Toast.LENGTH_LONG).show();
                    finish();
                }
                DataMahasiswa.dataMahasiswa.refreshList();
            }
        });
        editTextTanggalLahir.setOnClickListener(v -> showDateDialog());
    }
    private void showDateDialog() {
        datePickerDialog = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> editTextTanggalLahir.setText(year + "-" + (month + 1) + "-" + dayOfMonth), 2001, 7, 12);
        datePickerDialog.show();
    }
}