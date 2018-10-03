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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private EditText mEmail;
    private Button mLogin;
    private EditText mPassword;
    private Button mRegister;

    /* renamed from: com.nipunduit.tugasbesar.MainActivity$1 */
    class C03741 implements OnClickListener {
        C03741() {
        }

        public void onClick(View v) {
            MainActivity.this.startActivity(new Intent(MainActivity.this, RegisterActivity.class));
        }
    }

    /* renamed from: com.nipunduit.tugasbesar.MainActivity$2 */
    class C03752 implements OnClickListener {
        C03752() {
        }

        public void onClick(View v) {
            MainActivity.this.onClickLogin();
        }
    }

    /* renamed from: com.nipunduit.tugasbesar.MainActivity$3 */
    class C05433 implements Callback<UserDAO> {
        C05433() {
        }

        public void onResponse(Call<UserDAO> call, Response<UserDAO> response) {
            Toast.makeText(MainActivity.this, "Login  berhasil", 0).show();
            MainActivity.this.startIntent();
        }

        public void onFailure(Call<UserDAO> call, Throwable t) {
            Toast.makeText(MainActivity.this, "Terjadi kesalahan saat login", 0).show();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0376R.layout.activity_main);
        setAtribut();
        this.mRegister.setOnClickListener(new C03741());
        this.mLogin.setOnClickListener(new C03752());
    }

    private void setAtribut() {
        this.mEmail = (EditText) findViewById(C0376R.id.mEmail);
        this.mPassword = (EditText) findViewById(C0376R.id.mPassword);
        this.mLogin = (Button) findViewById(C0376R.id.mLogin_login);
        this.mRegister = (Button) findViewById(C0376R.id.mDone);
    }

    public void startIntent() {
        Intent intent = new Intent(this, HomeActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putString(NotificationCompat.CATEGORY_EMAIL, this.mEmail.getText().toString());
        intent.putExtra("login", mBundle);
        startActivity(intent);
    }

    public void onClickLogin() {
        if (!this.mEmail.getText().toString().isEmpty()) {
            if (!this.mPassword.getText().toString().isEmpty()) {
                ((ApiClient) new Builder().baseUrl("https://nipunduit.000webhostapp.com/api/").addConverterFactory(GsonConverterFactory.create()).build().create(ApiClient.class)).loginUser(this.mEmail.getText().toString(), this.mPassword.getText().toString()).enqueue(new C05433());
                return;
            }
        }
        Toast.makeText(this, "Email atau Password tidak boleh ada yang kosong", 0).show();
    }
}
