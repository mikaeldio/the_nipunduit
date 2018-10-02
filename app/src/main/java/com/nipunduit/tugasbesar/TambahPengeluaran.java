package com.nipunduit.tugasbesar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

        //===> Date taker
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        final String formattedDate = df.format(c);
        //<===

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
                String keterangan = mKeterangan.getText().toString();
                String email = nEmail;
                String tanggal = formattedDate;
                int finalValue=Integer.parseInt(value);
                Retrofit.Builder builder = new Retrofit.Builder()
                        .baseUrl("https://nipunduit.000webhostapp.com/api/")
                        .addConverterFactory(GsonConverterFactory.create());
                Retrofit retrofit=builder.build();
                ApiClient apiClient=retrofit.create(ApiClient.class);
                Call<PengeluaranDAO> userDAOCall=apiClient.addPengeluaran(email, keterangan, finalValue, tanggal);
                userDAOCall.enqueue(new Callback<PengeluaranDAO>() {
                    @Override
                    public void onResponse(Call<PengeluaranDAO> call, Response<PengeluaranDAO> response) {
                        if(response.isSuccessful()){
                            String error = response.body().getError();
                            if(error.equals("true")){
                                String error_message = response.body().getError_msg();
                                Toast.makeText(TambahPengeluaran.this, error_message, Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(TambahPengeluaran.this, "Penambahan berhasil", Toast.LENGTH_SHORT).show();
                                //Intent i = new Intent(TambahPengeluaran.this,ShowPengeluaranHariActivity.class);
                                //startActivity(i);
                            }
                        }
                        else{
                            Toast.makeText(TambahPengeluaran.this,"penambahan gagal", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<PengeluaranDAO> call, Throwable t) {
                        Toast.makeText(TambahPengeluaran.this,"Gagal terkoneksi dengan database", Toast.LENGTH_SHORT).show();
                        Toast.makeText(TambahPengeluaran.this,t.toString(), Toast.LENGTH_SHORT).show();
                    }

                });
                Intent i = new Intent (TambahPengeluaran.this,ShowPengeluaranHariActivity.class);
                startActivity(i);
            }
        });
    }
}
