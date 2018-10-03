package com.nipunduit.tugasbesar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

public class TambahPengeluaran extends AppCompatActivity {
    private Button mBatal;
    private EditText mJumlah;
    private EditText mKeterangan;
    private Button mTambah;
    private Bundle nBundle;
    private String nEmail;
    PengeluaranDAO pengeluaranDAO = new PengeluaranDAO();

    /* renamed from: com.nipunduit.tugasbesar.TambahPengeluaran$1 */
    class C03801 implements OnClickListener {
        C03801() {
        }

        public void onClick(View v) {
            Intent intent = new Intent(TambahPengeluaran.this.getApplicationContext(), HomeActivity.class);
            Bundle mBundle = new Bundle();
            mBundle.putString(NotificationCompat.CATEGORY_EMAIL, TambahPengeluaran.this.nEmail);
            intent.putExtra("login", mBundle);
            TambahPengeluaran.this.startActivity(intent);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0376R.layout.activity_tambah_pengeluaran);
        this.nBundle = getIntent().getBundleExtra("login");
        this.nEmail = this.nBundle.getString(NotificationCompat.CATEGORY_EMAIL);
        this.mKeterangan = (EditText) findViewById(C0376R.id.mKeterangan);
        this.mJumlah = (EditText) findViewById(C0376R.id.mJumlah);
        this.mBatal = (Button) findViewById(C0376R.id.mBatal);
        this.mTambah = (Button) findViewById(C0376R.id.mTambah);
        final String formattedDate = new SimpleDateFormat("dd-MMM-yyyy").format(Calendar.getInstance().getTime());
        this.mBatal.setOnClickListener(new C03801());
        this.mTambah.setOnClickListener(new OnClickListener() {

            /* renamed from: com.nipunduit.tugasbesar.TambahPengeluaran$2$1 */
            class C05471 implements Callback<PengeluaranDAO> {
                C05471() {
                }

                public void onResponse(Call<PengeluaranDAO> call, Response<PengeluaranDAO> response) {
                    if (response.isSuccessful()) {
                        if (((PengeluaranDAO) response.body()).getError().equals("true")) {
                            Toast.makeText(TambahPengeluaran.this, ((PengeluaranDAO) response.body()).getError_msg(), 0).show();
                        } else {
                            Toast.makeText(TambahPengeluaran.this, "Penambahan berhasil", 0).show();
                        }
                        return;
                    }
                    Toast.makeText(TambahPengeluaran.this, "penambahan gagal", 0).show();
                }

                public void onFailure(Call<PengeluaranDAO> call, Throwable t) {
                    Toast.makeText(TambahPengeluaran.this, "Gagal terkoneksi dengan database", 0).show();
                    Toast.makeText(TambahPengeluaran.this, t.toString(), 0).show();
                }
            }

            public void onClick(View v) {
                String value = TambahPengeluaran.this.mJumlah.getText().toString();
                String keterangan = TambahPengeluaran.this.mKeterangan.getText().toString();
                String email = TambahPengeluaran.this.nEmail;
                String tanggal = formattedDate;
                ((ApiClient) new Builder().baseUrl("https://nipunduit.000webhostapp.com/api/").addConverterFactory(GsonConverterFactory.create()).build().create(ApiClient.class)).addPengeluaran(email, keterangan, Integer.parseInt(value), tanggal).enqueue(new C05471());
                Intent intent = new Intent(TambahPengeluaran.this.getApplicationContext(), ShowPengeluaranHariActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putString(NotificationCompat.CATEGORY_EMAIL, TambahPengeluaran.this.nEmail);
                intent.putExtra("login", mBundle);
                TambahPengeluaran.this.startActivity(intent);
            }
        });
    }
}
