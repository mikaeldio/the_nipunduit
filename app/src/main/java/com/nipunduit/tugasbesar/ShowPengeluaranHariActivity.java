package com.nipunduit.tugasbesar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowPengeluaranHariActivity extends AppCompatActivity {
    private List<PengeluaranDAO> mListPengeluaranHarian = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecycleAdapterHari recycleAdapterHari;
    private RecyclerView.LayoutManager layoutManager;
    private TextView mTotal,mDate;
    private Integer totalPrice = 0;
    private String nEmail;

    private Bundle nBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_pengeluaran_hari);

        nBundle=getIntent().getBundleExtra("login");
        nEmail= nBundle.getString("email");

        //===> Date taker
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);
        //<===

        mTotal=(TextView) findViewById(R.id.mTotal);

        recyclerView = findViewById(R.id.recycler_view_hari);
        mDate =(TextView) findViewById(R.id.mDate);
        recycleAdapterHari = new RecycleAdapterHari(this, mListPengeluaranHarian);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recycleAdapterHari);
        setRecycleView();

        for(int i=0; i<mListPengeluaranHarian.size(); i++)
        {
            totalPrice +=  mListPengeluaranHarian.get(i).getJumlah();
        }
        String t = totalPrice.toString();
        mTotal.setText(t);
        mDate.setText(formattedDate);

    }
    private void setRecycleView(){
        Retrofit.Builder builder=new Retrofit
                .Builder()
                .baseUrl("https://nipunduit.000webhostapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();
        ApiClient apiClient=retrofit.create(ApiClient.class);
        Call<PengeluaranDAO.Value> studentDAOCall=apiClient.getPengeluaranHari(nEmail);

        studentDAOCall.enqueue(new Callback<PengeluaranDAO.Value>() {
            @Override
            public void onResponse(Call<PengeluaranDAO.Value> call, Response<PengeluaranDAO.Value> response) {
                recycleAdapterHari.notifyDataSetChanged();
                recycleAdapterHari=new RecycleAdapterHari(ShowPengeluaranHariActivity.this, response.body().getResult());
                recyclerView.setAdapter(recycleAdapterHari);
                Toast.makeText(ShowPengeluaranHariActivity.this,"Welcome",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<PengeluaranDAO.Value> call, Throwable t) {
                Toast.makeText(ShowPengeluaranHariActivity.this,"Network COnnection Error",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
