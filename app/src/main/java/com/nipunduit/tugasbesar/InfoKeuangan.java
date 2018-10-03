package com.nipunduit.tugasbesar;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InfoKeuangan extends AppCompatActivity {

    private SharedPreferences sp;
    private final String name = "myShared";
    private static final int mode = Activity.MODE_PRIVATE;

    private String Pendapatan= "";
    private String TargetTabungan= "";

    private EditText mPendapatan;
    private EditText mTargetTabungan;
    private Spinner mfrekuensi;
    private Button mBatal;
    private Button mDone;

    private String nEmail;
    private String sFrek;
    private int budget_bulanan;
    private int target_bulanan;
    private int frekuensi;
    private Bundle nBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_keuangan);

        nBundle=getIntent().getBundleExtra("login");
        nEmail= nBundle.getString("email");

        mPendapatan= (EditText)findViewById(R.id.mPendapatan);
        mTargetTabungan=(EditText)findViewById(R.id.mTarget);
        mfrekuensi = (Spinner) findViewById(R.id.spinnerFrekuensi);
        mBatal=(Button) findViewById(R.id.mBatal);
        mDone=(Button) findViewById(R.id.mDone);

        //mPendapatan.setText(Pendapatan);
        //mTargetTabungan.setText(TargetTabungan);

        mBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (InfoKeuangan.this,HomeActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putString("email",nEmail);
                i.putExtra("login",mBundle);
                startActivity(i);
            }
        });
        mDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickAddBudget();
            }
        });
    }

    public void startIntent(){
        Intent  intent =  new Intent(getApplicationContext(), HomeActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putString("email",nEmail);
        intent.putExtra("login",mBundle);
        startActivity(intent);
    }

    public void onClickAddBudget(){
        if(nEmail.isEmpty() || mPendapatan.getText().toString().isEmpty()
                || mTargetTabungan.getText().toString().isEmpty()){
            Toast.makeText(this, "Data tidak boleh ada yang kosong", Toast.LENGTH_SHORT).show();
        }
        else{
            sFrek = mfrekuensi.getSelectedItem().toString();
            budget_bulanan = Integer.parseInt(mPendapatan.getText().toString());
            target_bulanan = Integer.parseInt(mTargetTabungan.getText().toString());
            frekuensi = Integer.parseInt(sFrek);
            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl("https://nipunduit.000webhostapp.com/api/")
                    .addConverterFactory(GsonConverterFactory.create());
            Retrofit retrofit=builder.build();
            ApiClient apiClient = retrofit.create(ApiClient.class);
            Call<InfoKeuanganDAO> infoKeuanganDAOCall = apiClient.addBudget(nEmail, budget_bulanan, target_bulanan, frekuensi);
            infoKeuanganDAOCall.enqueue(new Callback<InfoKeuanganDAO>() {
                @Override
                public void onResponse(Call<InfoKeuanganDAO> call, Response<InfoKeuanganDAO> response) {
                    if(response.isSuccessful()){
                        String error = response.body().getError();
                        if(error.equals("true")){
                            String error_message = response.body().getError_msg();
                            Toast.makeText(InfoKeuangan.this, error_message, Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(InfoKeuangan.this, "Penambahan budget berhasil", Toast.LENGTH_SHORT).show();
                            startIntent();
                        }
                    }
                    else{
                        Toast.makeText(InfoKeuangan.this,"Penambahan budget gagal", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<InfoKeuanganDAO> call, Throwable t) {
                    Toast.makeText(InfoKeuangan.this,"Gagal terkoneksi dengan server", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
