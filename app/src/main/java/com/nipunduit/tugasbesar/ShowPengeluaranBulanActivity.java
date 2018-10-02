package com.nipunduit.tugasbesar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static java.time.format.DateTimeFormatter.*;

public class ShowPengeluaranBulanActivity extends AppCompatActivity {
    private List<PengeluaranBulananDAO> mListPengeluaranBulan = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecycleAdapterBulan RecycleAdapterBulan;
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

        mTotal=(TextView) findViewById(R.id.mTotal);
        recyclerView = findViewById(R.id.recycler_view_bulan);
        mDate =(TextView) findViewById(R.id.mDate);
        RecycleAdapterBulan = new RecycleAdapterBulan(mListPengeluaranBulan);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        setRecycleView();
        recyclerView.setAdapter(RecycleAdapterBulan);

        for (int i = 0; i < mListPengeluaranBulan.size(); i++) {
          totalPrice += mListPengeluaranBulan.get(i).getJumlah();
        }
        String t = totalPrice.toString();
        mTotal.setText(t);
        mDate.setText(month);

    }

    private void setRecycleView(){
        List<PengeluaranBulananDAO> mList;

        mList= PengeluaranBulananDAO.listAll(PengeluaranBulananDAO.class);

        mListPengeluaranBulan.addAll(mList);
        RecycleAdapterBulan.notifyDataSetChanged();
    }
}
