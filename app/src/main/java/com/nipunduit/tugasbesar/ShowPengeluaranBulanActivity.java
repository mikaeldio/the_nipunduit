package com.nipunduit.tugasbesar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.time.format.DateTimeFormatter.*;

public class ShowPengeluaranBulanActivity extends AppCompatActivity {
    private List<PengeluaranBulananDAO> mListPengeluaranBulan = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecycleAdapterBulan recycleAdapterBulan;
    private RecyclerView.LayoutManager layoutManager;
    private TextView mTotal,mDate;
    private Integer totalPrice = 0;
    private String nEmail;

    private Bundle nBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_pengeluaran_bulan);

        nBundle=getIntent().getBundleExtra("login");
        nEmail= nBundle.getString("email");

        //===> Month taker
        Calendar c = Calendar.getInstance();
        String[]monthName={"January","February","March", "April", "May", "June", "July",
                "August", "September", "October", "November",
                "December"};
        String month=monthName[c.get(Calendar.MONTH)];
        //<===
        /*
        mTotal=(TextView) findViewById(R.id.mTotal);
        recyclerView = findViewById(R.id.recycler_view_bulan);
        mDate =(TextView) findViewById(R.id.mDate);
        RecycleAdapterBulan = new RecycleAdapterBulan(mListPengeluaranBulan);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        setRecycleView();
        recyclerView.setAdapter(RecycleAdapterBulan);
        */

        //recylcer
        mListPengeluaranBulan = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_bulan);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recycleAdapterBulan = new RecycleAdapterBulan(getApplicationContext(),mListPengeluaranBulan);
        recyclerView.setAdapter(recycleAdapterBulan);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit.Builder builder=new Retrofit
                .Builder()
                .baseUrl("https://nipunduit.000webhostapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create(gson));
        Retrofit retrofit=builder.build();
        ApiClient apiClient=retrofit.create(ApiClient.class);
        Call<List<PengeluaranBulananDAO>> studentDAOCall=apiClient.getPengeluaranBulan(nEmail);

        studentDAOCall.enqueue(new Callback<List<PengeluaranBulananDAO>>() {
            @Override
            public void onResponse(Call<List<PengeluaranBulananDAO>> call, Response<List<PengeluaranBulananDAO>> response) {
                Toast.makeText(ShowPengeluaranBulanActivity.this,"Done",Toast.LENGTH_SHORT).show();
                mListPengeluaranBulan = response.body();
                Log.d("TAG","Response = "+mListPengeluaranBulan.toString());
                recycleAdapterBulan.setPengeluaranBulanlist(mListPengeluaranBulan);
                recycleAdapterBulan.notifyDataSetChanged();
                for(int i=0; i<mListPengeluaranBulan.size(); i++)
                {
                    totalPrice +=  mListPengeluaranBulan.get(i).getJumlah();
                }
                String t = totalPrice.toString();
                mTotal.setText(t);
            }

            @Override
            public void onFailure(Call<List<PengeluaranBulananDAO>> call, Throwable t) {
                Toast.makeText(ShowPengeluaranBulanActivity.this,"Network Connection Error",Toast.LENGTH_SHORT).show();
                Toast.makeText(ShowPengeluaranBulanActivity.this,t.toString(),Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });

    }
}
