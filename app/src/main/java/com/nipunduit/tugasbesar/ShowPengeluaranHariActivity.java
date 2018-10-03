// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.nipunduit.tugasbesar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.GsonBuilder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// Referenced classes of package com.nipunduit.tugasbesar:
//            RecycleAdapterHari, ApiClient, PengeluaranDAO

public class ShowPengeluaranHariActivity extends AppCompatActivity
{

    private android.support.v7.widget.RecyclerView.LayoutManager layoutManager;
    private TextView mDate;
    private List mListPengeluaranHarian;
    private TextView mTotal;
    private Bundle nBundle;
    private String nEmail;
    private String nTanggal;
    private RecycleAdapterHari recycleAdapterHari;
    private RecyclerView recyclerView;
    private Integer totalPrice;

    public ShowPengeluaranHariActivity()
    {
        mListPengeluaranHarian = new ArrayList();
        totalPrice = Integer.valueOf(0);
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f0c0024);
        nBundle = getIntent().getBundleExtra("login");
        nEmail = nBundle.getString("email");
        bundle = Calendar.getInstance().getTime();
        (new SimpleDateFormat("dd-MMM-yyyy")).format(bundle);
        mTotal = (TextView)findViewById(0x7f090085);
        mDate = (TextView)findViewById(0x7f09006c);
        mDate.setText(nTanggal);
        mListPengeluaranHarian = new ArrayList();
        recyclerView = (RecyclerView)findViewById(0x7f0900a7);
        bundle = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(bundle);
        recycleAdapterHari = new RecycleAdapterHari(getApplicationContext(), mListPengeluaranHarian);
        recyclerView.setAdapter(recycleAdapterHari);
        bundle = (new GsonBuilder()).setLenient().create();
        ((ApiClient)(new retrofit2.Retrofit.Builder()).baseUrl("https://nipunduit.000webhostapp.com/api/").addConverterFactory(GsonConverterFactory.create(bundle)).build().create(com/nipunduit/tugasbesar/ApiClient)).getPengeluaranHari(nEmail).enqueue(new Callback() {

            final ShowPengeluaranHariActivity this$0;

            public void onFailure(Call call, Throwable throwable)
            {
                Toast.makeText(ShowPengeluaranHariActivity.this, "Network Connection Error", 0).show();
                Toast.makeText(ShowPengeluaranHariActivity.this, throwable.toString(), 0).show();
                throwable.printStackTrace();
            }

            public void onResponse(Call call, Response response)
            {
                call = ShowPengeluaranHariActivity.this;
                int j = 0;
                Toast.makeText(call, "Done", 0).show();
                mListPengeluaranHarian = (List)response.body();
                call = new StringBuilder();
                call.append("Response = ");
                call.append(mListPengeluaranHarian.toString());
                Log.d("TAG", call.toString());
                recycleAdapterHari.setPengeluaranlist(mListPengeluaranHarian);
                recycleAdapterHari.notifyDataSetChanged();
                for (; j < mListPengeluaranHarian.size(); j++)
                {
                    totalPrice = Integer.valueOf(totalPrice.intValue() + ((PengeluaranDAO)mListPengeluaranHarian.get(j)).getJumlah().intValue());
                }

                call = totalPrice.toString();
                mTotal.setText(call);
            }

            
            {
                this$0 = ShowPengeluaranHariActivity.this;
                super();
            }
        });
        for (int i = 0; i < mListPengeluaranHarian.size(); i++)
        {
            totalPrice = Integer.valueOf(totalPrice.intValue() + ((PengeluaranDAO)mListPengeluaranHarian.get(i)).getJumlah().intValue());
        }

        bundle = totalPrice.toString();
        mTotal.setText(bundle);
        mDate.setText(nTanggal);
    }



/*
    static List access$002(ShowPengeluaranHariActivity showpengeluaranhariactivity, List list)
    {
        showpengeluaranhariactivity.mListPengeluaranHarian = list;
        return list;
    }

*/




/*
    static Integer access$202(ShowPengeluaranHariActivity showpengeluaranhariactivity, Integer integer)
    {
        showpengeluaranhariactivity.totalPrice = integer;
        return integer;
    }

*/

}
