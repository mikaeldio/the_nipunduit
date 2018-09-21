package com.nipunduit.tugasbesar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TambahPengeluaran extends AppCompatActivity {

    private EditText mKeterangan;
    private EditText mJumlah;
    private Button mBatal;
    private Button mTambah;
    PengeluaranDAO pengeluaranDAO = new PengeluaranDAO();
    private String nEmail;

    private Bundle nBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_pengeluaran);

        nBundle=getIntent().getBundleExtra("login");
        nEmail= nBundle.getString("email");

        mKeterangan=(EditText)findViewById(R.id.mKeterangan);
        mJumlah=(EditText)findViewById(R.id.mJumlah);
        mBatal=(Button) findViewById(R.id.mBatal);
        mTambah=(Button) findViewById(R.id.mTambah);

        mBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (TambahPengeluaran.this,HomeActivity.class);
                startActivity(i);
            }
        });
        mTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value= mJumlah.getText().toString();
                int finalValue=Integer.parseInt(value);
                pengeluaranDAO = new PengeluaranDAO(mKeterangan.getText().toString(),finalValue);
                pengeluaranDAO.save();
                Intent i = new Intent (TambahPengeluaran.this,ShowPengeluaranHariActivity.class);
                startActivity(i);
            }
        });
    }
}
