package com.nipunduit.tugasbesar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
        recycleAdapterHari = new RecycleAdapterHari(mListPengeluaranHarian);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        setRecycleView();
        recyclerView.setAdapter(recycleAdapterHari);

        for(int i=0; i<mListPengeluaranHarian.size(); i++)
        {
            totalPrice +=  mListPengeluaranHarian.get(i).getJumlah();
        }
        String t = totalPrice.toString();
        mTotal.setText(t);
        mDate.setText(formattedDate);

    }

    private void setRecycleView(){
        List<PengeluaranDAO> mList;

        mList= PengeluaranDAO.listAll(PengeluaranDAO.class);

        mListPengeluaranHarian.addAll(mList);
        recycleAdapterHari.notifyDataSetChanged();
    }
}
