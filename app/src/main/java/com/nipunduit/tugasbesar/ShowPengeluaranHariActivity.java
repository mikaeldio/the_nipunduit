package com.nipunduit.tugasbesar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ShowPengeluaranHariActivity extends AppCompatActivity {
    private List<PengeluaranDAO> mListPengeluaranHarian = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecycleAdapter recycleAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private TextView mTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_pengeluaran_hari);

        mTotal=(TextView) findViewById(R.id.mTotal);
        recyclerView = findViewById(R.id.recycler_view_hari);
        recycleAdapter = new RecycleAdapter(mListPengeluaranHarian);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        setRecycleView();
        recyclerView.setAdapter(recycleAdapter);
        float totalPrice = 0;
        for (int i = 0; i<mListPengeluaranHarian.size(); i++)
        {
            totalPrice += Float.parseFloat(mListPengeluaranHarian.get(i).getJumlah());
        }
        mTotal.setText((int) totalPrice);
    }

    private void setRecycleView(){
        List<PengeluaranDAO> mList;

        mList= PengeluaranDAO.listAll(PengeluaranDAO.class);

        mListPengeluaranHarian.addAll(mList);
        recycleAdapter.notifyDataSetChanged();
    }
}
