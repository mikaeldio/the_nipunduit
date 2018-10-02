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
import android.widget.Toast;

import org.json.JSONObject;
import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
    private Button mEditProfil;
    private Button mTambahBudget;

    private String nEmail;
    private Bundle nBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mTambahPengeluaran = (Button) findViewById(R.id.mTambah);
        mTampilPengeluaran = (Button) findViewById(R.id.mTampil);
        mAturMakan = (Button) findViewById(R.id.mJamMakan);
        mKeluar = (Button) findViewById(R.id.mKeluar);
        mNama = (TextView) findViewById(R.id.mNama);
        mEditProfil  = (Button) findViewById(R.id.btnEdit);
        mTambahBudget = (Button) findViewById(R.id.btnBudget);

        nBundle=getIntent().getBundleExtra("login");
        nEmail= nBundle.getString("email");

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://nipunduit.000webhostapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();
        ApiClient apiClient=retrofit.create(ApiClient.class);
        Call<UserDAO> userDAOCall=apiClient.getProfil(nEmail);
        userDAOCall.enqueue(new Callback<UserDAO>() {
            @Override
            public void onResponse(Call<UserDAO> call, Response<UserDAO> response) {
                //Toast.makeText(HomeActivity.this, "Loading user data", Toast.LENGTH_SHORT).show();
                UserDAO user = response.body();
                mNama.setText("Selamat datang, "+user.getNama());
            }
            @Override
            public void onFailure(Call<UserDAO> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Tidak bisa mengambil data user", Toast.LENGTH_SHORT).show();
            }
        });

<<<<<<< HEAD
=======
        //untuk nampilin view di home (BB dan TT)
        Retrofit.Builder builderBudget = new Retrofit.Builder()
                .baseUrl("https://nipunduit.000webhostapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofitBudget=builderBudget.build();
        ApiClient apiClientBudget=retrofitBudget.create(ApiClient.class);
        Call<InfoKeuanganDAO> infoKeuanganDAOCall=apiClientBudget.getInfoKeuangan(nEmail);
        infoKeuanganDAOCall.enqueue(new Callback<InfoKeuanganDAO>() {
            @Override
            public void onResponse(Call<InfoKeuanganDAO> call, Response<InfoKeuanganDAO> response) {
                //Toast.makeText(HomeActivity.this, "Loading user data", Toast.LENGTH_SHORT).show();
                InfoKeuanganDAO info = response.body();
<<<<<<< HEAD
                mBudgetBulanan.setText(info.getBudget());
                mTargetTabungan.setText(info.getTabungan());
=======
                mBudgetBulanan.setText(new Integer(info.budget_bulanan).toString());
                mTargetTabungan.setText(new Integer(info.target_tabungan).toString());
>>>>>>> parent of 6ed8ed9... Revert "Merge branch 'master' of https://github.com/mikaeldio/the_nipunduit"

            }
            @Override
            public void onFailure(Call<InfoKeuanganDAO> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Tidak bisa mengambil data user", Toast.LENGTH_SHORT).show();
<<<<<<< HEAD
=======
                t.printStackTrace();
>>>>>>> parent of 6ed8ed9... Revert "Merge branch 'master' of https://github.com/mikaeldio/the_nipunduit"
            }
        });

>>>>>>> 60a8ef80ecd86764f3416e262b990b61f0608204
        mTampilPengeluaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, ShowPengeluaranHariActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putString("email",nEmail);
                i.putExtra("login",mBundle);
                startActivity(i);
            }
        });

        mTambahPengeluaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, TambahPengeluaran.class);
                Bundle mBundle = new Bundle();
                mBundle.putString("email",nEmail);
                i.putExtra("login",mBundle);
                startActivity(i);
            }
        });

        mAturMakan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, AturJamMakan.class);
                Bundle mBundle = new Bundle();
                mBundle.putString("email",nEmail);
                i.putExtra("login",mBundle);
                startActivity(i);
            }
        });

        mKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, MainActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putString("email",nEmail);
                i.putExtra("login",mBundle);
                startActivity(i);
            }
        });

        mEditProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, EditProfileActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putString("email",nEmail);
                i.putExtra("login",mBundle);
                startActivity(i);
            }
        });

        mTambahBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, InfoKeuangan.class);
                Bundle mBundle = new Bundle();
                mBundle.putString("email",nEmail);
                i.putExtra("login",mBundle);
                startActivity(i);
            }
        });
    }

}
