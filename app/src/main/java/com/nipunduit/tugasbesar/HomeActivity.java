package com.nipunduit.tugasbesar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity {
    private Button mAturMakan;
    private TextView mBudgetBulanan;
    private Button mEditProfil;
    private Button mKeluar;
    private TextView mNama;
    private Button mTambahBudget;
    private Button mTambahPengeluaran;
    private Button mTampilPengeluaran;
    private TextView mTargetTabungan;
    private Bundle nBundle;
    private String nEmail;

    /* renamed from: com.nipunduit.tugasbesar.HomeActivity$3 */
    class C03663 implements OnClickListener {
        C03663() {
        }

        public void onClick(View v) {
            Intent i = new Intent(HomeActivity.this, ShowPengeluaranHariActivity.class);
            Bundle mBundle = new Bundle();
            mBundle.putString(NotificationCompat.CATEGORY_EMAIL, HomeActivity.this.nEmail);
            i.putExtra("login", mBundle);
            HomeActivity.this.startActivity(i);
        }
    }

    /* renamed from: com.nipunduit.tugasbesar.HomeActivity$4 */
    class C03674 implements OnClickListener {
        C03674() {
        }

        public void onClick(View v) {
            Intent i = new Intent(HomeActivity.this, TambahPengeluaran.class);
            Bundle mBundle = new Bundle();
            mBundle.putString(NotificationCompat.CATEGORY_EMAIL, HomeActivity.this.nEmail);
            i.putExtra("login", mBundle);
            HomeActivity.this.startActivity(i);
        }
    }

    /* renamed from: com.nipunduit.tugasbesar.HomeActivity$5 */
    class C03685 implements OnClickListener {
        C03685() {
        }

        public void onClick(View v) {
            Intent i = new Intent(HomeActivity.this, AturJamMakan.class);
            Bundle mBundle = new Bundle();
            mBundle.putString(NotificationCompat.CATEGORY_EMAIL, HomeActivity.this.nEmail);
            i.putExtra("login", mBundle);
            HomeActivity.this.startActivity(i);
        }
    }

    /* renamed from: com.nipunduit.tugasbesar.HomeActivity$6 */
    class C03696 implements OnClickListener {
        C03696() {
        }

        public void onClick(View v) {
            Intent i = new Intent(HomeActivity.this, MainActivity.class);
            Bundle mBundle = new Bundle();
            mBundle.putString(NotificationCompat.CATEGORY_EMAIL, HomeActivity.this.nEmail);
            i.putExtra("login", mBundle);
            HomeActivity.this.startActivity(i);
        }
    }

    /* renamed from: com.nipunduit.tugasbesar.HomeActivity$7 */
    class C03707 implements OnClickListener {
        C03707() {
        }

        public void onClick(View v) {
            Intent i = new Intent(HomeActivity.this, EditProfileActivity.class);
            Bundle mBundle = new Bundle();
            mBundle.putString(NotificationCompat.CATEGORY_EMAIL, HomeActivity.this.nEmail);
            i.putExtra("login", mBundle);
            HomeActivity.this.startActivity(i);
        }
    }

    /* renamed from: com.nipunduit.tugasbesar.HomeActivity$8 */
    class C03718 implements OnClickListener {
        C03718() {
        }

        public void onClick(View v) {
            Intent i = new Intent(HomeActivity.this, InfoKeuangan.class);
            Bundle mBundle = new Bundle();
            mBundle.putString(NotificationCompat.CATEGORY_EMAIL, HomeActivity.this.nEmail);
            i.putExtra("login", mBundle);
            HomeActivity.this.startActivity(i);
        }
    }

    /* renamed from: com.nipunduit.tugasbesar.HomeActivity$1 */
    class C05401 implements Callback<UserDAO> {
        C05401() {
        }

        public void onResponse(Call<UserDAO> call, Response<UserDAO> response) {
            UserDAO user = (UserDAO) response.body();
            TextView access$000 = HomeActivity.this.mNama;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Selamat datang, ");
            stringBuilder.append(user.getNama());
            access$000.setText(stringBuilder.toString());
        }

        public void onFailure(Call<UserDAO> call, Throwable t) {
            Toast.makeText(HomeActivity.this, "Tidak bisa mengambil data user", 0).show();
        }
    }

    /* renamed from: com.nipunduit.tugasbesar.HomeActivity$2 */
    class C05412 implements Callback<InfoKeuanganDAO> {
        C05412() {
        }

        public void onResponse(Call<InfoKeuanganDAO> call, Response<InfoKeuanganDAO> response) {
            InfoKeuanganDAO info = (InfoKeuanganDAO) response.body();
            HomeActivity.this.mBudgetBulanan.setText(new Integer(info.budget_bulanan).toString());
            HomeActivity.this.mTargetTabungan.setText(new Integer(info.target_tabungan).toString());
        }

        public void onFailure(Call<InfoKeuanganDAO> call, Throwable t) {
            Toast.makeText(HomeActivity.this, "Tidak bisa mengambil data user", 0).show();
            t.printStackTrace();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0376R.layout.activity_home);
        this.mTambahPengeluaran = (Button) findViewById(C0376R.id.mTambah);
        this.mTampilPengeluaran = (Button) findViewById(C0376R.id.mTampil);
        this.mAturMakan = (Button) findViewById(C0376R.id.mJamMakan);
        this.mKeluar = (Button) findViewById(C0376R.id.mKeluar);
        this.mNama = (TextView) findViewById(C0376R.id.mNama);
        this.mEditProfil = (Button) findViewById(C0376R.id.btnEdit);
        this.mTambahBudget = (Button) findViewById(C0376R.id.btnBudget);
        this.mBudgetBulanan = (TextView) findViewById(C0376R.id.mBB);
        this.mTargetTabungan = (TextView) findViewById(C0376R.id.mTT);
        this.nBundle = getIntent().getBundleExtra("login");
        this.nEmail = this.nBundle.getString(NotificationCompat.CATEGORY_EMAIL);
        ((ApiClient) new Builder().baseUrl("https://nipunduit.000webhostapp.com/api/").addConverterFactory(GsonConverterFactory.create()).build().create(ApiClient.class)).getProfil(this.nEmail).enqueue(new C05401());
        ((ApiClient) new Builder().baseUrl("https://nipunduit.000webhostapp.com/api/").addConverterFactory(GsonConverterFactory.create()).build().create(ApiClient.class)).getInfoKeuangan(this.nEmail).enqueue(new C05412());
        this.mTampilPengeluaran.setOnClickListener(new C03663());
        this.mTambahPengeluaran.setOnClickListener(new C03674());
        this.mAturMakan.setOnClickListener(new C03685());
        this.mKeluar.setOnClickListener(new C03696());
        this.mEditProfil.setOnClickListener(new C03707());
        this.mTambahBudget.setOnClickListener(new C03718());
    }
}
