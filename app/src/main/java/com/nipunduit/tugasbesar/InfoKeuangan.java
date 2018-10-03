package com.nipunduit.tugasbesar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

public class InfoKeuangan extends AppCompatActivity {
    private static final int mode = 0;
    private String Pendapatan = "";
    private String TargetTabungan = "";
    private int budget_bulanan;
    private int frekuensi;
    private Button mBatal;
    private Button mDone;
    private EditText mPendapatan;
    private EditText mTargetTabungan;
    private Spinner mfrekuensi;
    private Bundle nBundle;
    private String nEmail;
    private final String name = "myShared";
    private String sFrek;
    private SharedPreferences sp;
    private int target_bulanan;

    /* renamed from: com.nipunduit.tugasbesar.InfoKeuangan$1 */
    class C03721 implements OnClickListener {
        C03721() {
        }

        public void onClick(View v) {
            Intent i = new Intent(InfoKeuangan.this, HomeActivity.class);
            Bundle mBundle = new Bundle();
            mBundle.putString(NotificationCompat.CATEGORY_EMAIL, InfoKeuangan.this.nEmail);
            i.putExtra("login", mBundle);
            InfoKeuangan.this.startActivity(i);
        }
    }

    /* renamed from: com.nipunduit.tugasbesar.InfoKeuangan$2 */
    class C03732 implements OnClickListener {
        C03732() {
        }

        public void onClick(View v) {
            InfoKeuangan.this.onClickAddBudget();
        }
    }

    /* renamed from: com.nipunduit.tugasbesar.InfoKeuangan$3 */
    class C05423 implements Callback<InfoKeuanganDAO> {
        C05423() {
        }

        public void onResponse(Call<InfoKeuanganDAO> call, Response<InfoKeuanganDAO> response) {
            if (response.isSuccessful()) {
                if (((InfoKeuanganDAO) response.body()).getError().equals("true")) {
                    Toast.makeText(InfoKeuangan.this, ((InfoKeuanganDAO) response.body()).getError_msg(), 0).show();
                } else {
                    Toast.makeText(InfoKeuangan.this, "Penambahan budget berhasil", 0).show();
                    InfoKeuangan.this.startIntent();
                }
                return;
            }
            Toast.makeText(InfoKeuangan.this, "Penambahan budget gagal", 0).show();
        }

        public void onFailure(Call<InfoKeuanganDAO> call, Throwable t) {
            Toast.makeText(InfoKeuangan.this, "Gagal terkoneksi dengan server", 0).show();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0376R.layout.activity_info_keuangan);
        this.nBundle = getIntent().getBundleExtra("login");
        this.nEmail = this.nBundle.getString(NotificationCompat.CATEGORY_EMAIL);
        this.mPendapatan = (EditText) findViewById(C0376R.id.mPendapatan);
        this.mTargetTabungan = (EditText) findViewById(C0376R.id.mTarget);
        this.mfrekuensi = (Spinner) findViewById(C0376R.id.spinnerFrekuensi);
        this.mBatal = (Button) findViewById(C0376R.id.mBatal);
        this.mDone = (Button) findViewById(C0376R.id.mDone);
        this.mBatal.setOnClickListener(new C03721());
        this.mDone.setOnClickListener(new C03732());
    }

    public void startIntent() {
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putString(NotificationCompat.CATEGORY_EMAIL, this.nEmail);
        intent.putExtra("login", mBundle);
        startActivity(intent);
    }

    public void onClickAddBudget() {
        if (!(this.nEmail.isEmpty() || this.mPendapatan.getText().toString().isEmpty())) {
            if (!this.mTargetTabungan.getText().toString().isEmpty()) {
                this.sFrek = this.mfrekuensi.getSelectedItem().toString();
                this.budget_bulanan = Integer.parseInt(this.mPendapatan.getText().toString());
                this.target_bulanan = Integer.parseInt(this.mTargetTabungan.getText().toString());
                this.frekuensi = Integer.parseInt(this.sFrek);
                ((ApiClient) new Builder().baseUrl("https://nipunduit.000webhostapp.com/api/").addConverterFactory(GsonConverterFactory.create()).build().create(ApiClient.class)).addBudget(this.nEmail, this.budget_bulanan, this.target_bulanan, this.frekuensi).enqueue(new C05423());
                return;
            }
        }
        Toast.makeText(this, "Data tidak boleh ada yang kosong", 0).show();
    }
}
