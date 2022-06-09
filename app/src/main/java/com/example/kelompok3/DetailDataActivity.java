package com.example.kelompok3;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailDataActivity extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbHelper;
    TextView textViewNomor, textViewNama, textViewTanggalLahir, textViewJenisKelamin, textViewAlamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data);

        dbHelper = new DataHelper(this);

        textViewNomor = findViewById(R.id.text_view_nomor_detail);
        textViewNama = findViewById(R.id.text_view_nama_detail);
        textViewTanggalLahir = findViewById(R.id.text_view_tanggal_lahir_detail);
        textViewJenisKelamin = findViewById(R.id.text_view_jenis_kelamin_detail);
        textViewAlamat = findViewById(R.id.text_view_alamat_detail);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM biodata WHERE nama = '" + getIntent().getStringExtra("nama") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            textViewNomor.setText(cursor.getString(0).toString());
            textViewNama.setText(cursor.getString(1).toString());
            textViewTanggalLahir.setText(cursor.getString(2).toString());
            textViewJenisKelamin.setText(cursor.getString(3).toString());
            textViewAlamat.setText(cursor.getString(4).toString());
        }
    }
}