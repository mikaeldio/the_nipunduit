package com.nipunduit.tugasbesar;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import android.app.Fragment;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;
//tesjose
public class HomeActivity extends AppCompatActivity {

    private TextView mNama;
    private TextView mTotalBudget;
    private TextView mSisaBudget;
    private TextView mTargetTabungan;
    private TextView mBudgetSekarang;
    private TextView mSisaBudgetSekarang;

    private Button mTambahPengeluaran;
    private Button mTampilPengeluaran;
    private Button mAturMakan;
    private Button mKeluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mTambahPengeluaran = (Button) findViewById(R.id.mTambah);
        mTampilPengeluaran = (Button) findViewById(R.id.mTampil);
        mAturMakan = (Button) findViewById(R.id.mJamMakan);
        mKeluar = (Button) findViewById(R.id.mKeluar);

        mTampilPengeluaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, ShowPengeluaranHariActivity.class);
                startActivity(i);
            }
        });

        mTambahPengeluaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, TambahPengeluaran.class);
                startActivity(i);
            }
        });

        mAturMakan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, AturJamMakan.class);
                startActivity(i);
            }
        });

        mKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

}
