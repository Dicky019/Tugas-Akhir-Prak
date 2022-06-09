package com.example.kelompok3;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class DataMahasiswa extends AppCompatActivity {

    String[] daftar;
    ListView listView;
    protected Cursor cursor;
    DataHelper dbCenter;
    public static DataMahasiswa dataMahasiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_mahasiswa);

        dataMahasiswa = this;
        dbCenter = new DataHelper(this);
        refreshList();
    }

    public void refreshList() {
        SQLiteDatabase db = dbCenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM biodata", null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            daftar[i] = cursor.getString(1).toString();
        }

        listView = findViewById(R.id.list_view_data_mahasiswa);
        listView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
        listView.setSelected(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final  String selection = daftar[position];
                final CharSequence[] dialogItem = {"Lihat Data", "Update Data", "Hapus Data"};
                AlertDialog.Builder builder = new AlertDialog.Builder(DataMahasiswa.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                Intent intentDetailData = new Intent(getApplicationContext(), DetailDataActivity.class);
                                intentDetailData.putExtra("nama", selection);
                                startActivity(intentDetailData);
                                break;
                            case  1:
                                Intent intentUpdateData = new Intent(getApplicationContext(), UpdateData.class);
                                intentUpdateData.putExtra("nama", selection);
                                startActivity(intentUpdateData);
                                break;
                            case 2:
                                SQLiteDatabase db = dbCenter.getWritableDatabase();
                                db.execSQL("delete from biodata where nama = '"+selection+"'");
                                refreshList();
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        ((ArrayAdapter) listView.getAdapter()).notifyDataSetInvalidated();
    }
}