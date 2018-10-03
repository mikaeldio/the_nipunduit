package com.nipunduit.tugasbesar;

import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
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
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowPengeluaranHariActivity extends AppCompatActivity {
    private LayoutManager layoutManager;
    private TextView mDate;
    private List<PengeluaranDAO> mListPengeluaranHarian = new ArrayList();
    private TextView mTotal;
    private Bundle nBundle;
    private String nEmail;
    private String nTanggal;
    private RecycleAdapterHari recycleAdapterHari;
    private RecyclerView recyclerView;
    private Integer totalPrice = Integer.valueOf(0);

    /* renamed from: com.nipunduit.tugasbesar.ShowPengeluaranHariActivity$1 */
    class C05461 implements Callback<List<PengeluaranDAO>> {
        C05461() {
        }

        public void onResponse(Call<List<PengeluaranDAO>> call, Response<List<PengeluaranDAO>> response) {
            int i = 0;
            Toast.makeText(ShowPengeluaranHariActivity.this, "Done", 0).show();
            ShowPengeluaranHariActivity.this.mListPengeluaranHarian = (List) response.body();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Response = ");
            stringBuilder.append(ShowPengeluaranHariActivity.this.mListPengeluaranHarian.toString());
            Log.d("TAG", stringBuilder.toString());
            ShowPengeluaranHariActivity.this.recycleAdapterHari.setPengeluaranlist(ShowPengeluaranHariActivity.this.mListPengeluaranHarian);
            ShowPengeluaranHariActivity.this.recycleAdapterHari.notifyDataSetChanged();
            while (true) {
                int i2 = i;
                if (i2 < ShowPengeluaranHariActivity.this.mListPengeluaranHarian.size()) {
                    ShowPengeluaranHariActivity.this.totalPrice = Integer.valueOf(ShowPengeluaranHariActivity.this.totalPrice.intValue() + ((PengeluaranDAO) ShowPengeluaranHariActivity.this.mListPengeluaranHarian.get(i2)).getJumlah().intValue());
                    i = i2 + 1;
                } else {
                    ShowPengeluaranHariActivity.this.mTotal.setText(ShowPengeluaranHariActivity.this.totalPrice.toString());
                    return;
                }
            }
        }

        public void onFailure(Call<List<PengeluaranDAO>> call, Throwable t) {
            Toast.makeText(ShowPengeluaranHariActivity.this, "Network Connection Error", 0).show();
            Toast.makeText(ShowPengeluaranHariActivity.this, t.toString(), 0).show();
            t.printStackTrace();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0376R.layout.activity_show_pengeluaran_hari);
        this.nBundle = getIntent().getBundleExtra("login");
        this.nEmail = this.nBundle.getString(NotificationCompat.CATEGORY_EMAIL);
        String formattedDate = new SimpleDateFormat("dd-MMM-yyyy").format(Calendar.getInstance().getTime());
        this.mTotal = (TextView) findViewById(C0376R.id.mTotal);
        this.mDate = (TextView) findViewById(C0376R.id.mDate);
        this.mDate.setText(this.nTanggal);
        this.mListPengeluaranHarian = new ArrayList();
        this.recyclerView = (RecyclerView) findViewById(C0376R.id.recycler_view_hari);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.recycleAdapterHari = new RecycleAdapterHari(getApplicationContext(), this.mListPengeluaranHarian);
        this.recyclerView.setAdapter(this.recycleAdapterHari);
        ((ApiClient) new Builder().baseUrl("https://nipunduit.000webhostapp.com/api/").addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create())).build().create(ApiClient.class)).getPengeluaranHari(this.nEmail).enqueue(new C05461());
        for (int i = 0; i < this.mListPengeluaranHarian.size(); i++) {
            this.totalPrice = Integer.valueOf(this.totalPrice.intValue() + ((PengeluaranDAO) this.mListPengeluaranHarian.get(i)).getJumlah().intValue());
        }
        this.mTotal.setText(this.totalPrice.toString());
        this.mDate.setText(this.nTanggal);
    }
}
