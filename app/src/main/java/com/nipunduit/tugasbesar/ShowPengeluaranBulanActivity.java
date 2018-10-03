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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowPengeluaranBulanActivity extends AppCompatActivity {
    private LayoutManager layoutManager;
    private TextView mDate;
    private List<PengeluaranBulananDAO> mListPengeluaranBulan = new ArrayList();
    private TextView mTotal;
    private Bundle nBundle;
    private String nEmail;
    private RecycleAdapterBulan recycleAdapterBulan;
    private RecyclerView recyclerView;
    private Integer totalPrice = Integer.valueOf(0);

    /* renamed from: com.nipunduit.tugasbesar.ShowPengeluaranBulanActivity$1 */
    class C05451 implements Callback<List<PengeluaranBulananDAO>> {
        C05451() {
        }

        public void onResponse(Call<List<PengeluaranBulananDAO>> call, Response<List<PengeluaranBulananDAO>> response) {
            int i = 0;
            Toast.makeText(ShowPengeluaranBulanActivity.this, "Done", 0).show();
            ShowPengeluaranBulanActivity.this.mListPengeluaranBulan = (List) response.body();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Response = ");
            stringBuilder.append(ShowPengeluaranBulanActivity.this.mListPengeluaranBulan.toString());
            Log.d("TAG", stringBuilder.toString());
            ShowPengeluaranBulanActivity.this.recycleAdapterBulan.setPengeluaranBulanlist(ShowPengeluaranBulanActivity.this.mListPengeluaranBulan);
            ShowPengeluaranBulanActivity.this.recycleAdapterBulan.notifyDataSetChanged();
            while (true) {
                int i2 = i;
                if (i2 < ShowPengeluaranBulanActivity.this.mListPengeluaranBulan.size()) {
                    ShowPengeluaranBulanActivity.this.totalPrice = Integer.valueOf(ShowPengeluaranBulanActivity.this.totalPrice.intValue() + ((PengeluaranBulananDAO) ShowPengeluaranBulanActivity.this.mListPengeluaranBulan.get(i2)).getJumlah().intValue());
                    i = i2 + 1;
                } else {
                    ShowPengeluaranBulanActivity.this.mTotal.setText(ShowPengeluaranBulanActivity.this.totalPrice.toString());
                    return;
                }
            }
        }

        public void onFailure(Call<List<PengeluaranBulananDAO>> call, Throwable t) {
            Toast.makeText(ShowPengeluaranBulanActivity.this, "Network Connection Error", 0).show();
            Toast.makeText(ShowPengeluaranBulanActivity.this, t.toString(), 0).show();
            t.printStackTrace();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0376R.layout.activity_show_pengeluaran_bulan);
        this.nBundle = getIntent().getBundleExtra("login");
        this.nEmail = this.nBundle.getString(NotificationCompat.CATEGORY_EMAIL);
        String month = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}[Calendar.getInstance().get(2)];
        this.mListPengeluaranBulan = new ArrayList();
        this.recyclerView = (RecyclerView) findViewById(C0376R.id.recycler_view_bulan);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.recycleAdapterBulan = new RecycleAdapterBulan(getApplicationContext(), this.mListPengeluaranBulan);
        this.recyclerView.setAdapter(this.recycleAdapterBulan);
        ((ApiClient) new Builder().baseUrl("https://nipunduit.000webhostapp.com/api/").addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create())).build().create(ApiClient.class)).getPengeluaranBulan(this.nEmail).enqueue(new C05451());
    }
}
