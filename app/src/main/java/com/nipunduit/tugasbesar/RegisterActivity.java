package com.nipunduit.tugasbesar;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.regex.Pattern;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {
    ProgressDialog loading;
    private Button mBatal;
    private EditText mConfPassword;
    Context mContext;
    private Button mDaftar;
    private EditText mEmail;
    private EditText mName;
    private EditText mPassword;
    private EditText mTelp;

    /* renamed from: com.nipunduit.tugasbesar.RegisterActivity$2 */
    class C03792 implements OnClickListener {
        C03792() {
        }

        public void onClick(View v) {
            RegisterActivity.this.startActivity(new Intent(RegisterActivity.this, MainActivity.class));
        }
    }

    /* renamed from: com.nipunduit.tugasbesar.RegisterActivity$3 */
    class C05443 implements Callback<UserDAO> {
        C05443() {
        }

        public void onResponse(Call<UserDAO> call, Response<UserDAO> response) {
            if (response.isSuccessful()) {
                if (((UserDAO) response.body()).getError().equals("true")) {
                    Toast.makeText(RegisterActivity.this, ((UserDAO) response.body()).getError_msg(), 0).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "Pendaftaran akun berhasil", 0).show();
                    RegisterActivity.this.startIntent();
                }
                return;
            }
            Toast.makeText(RegisterActivity.this, "Pendaftaran akun gagal", 0).show();
        }

        public void onFailure(Call<UserDAO> call, Throwable t) {
            Toast.makeText(RegisterActivity.this, "Gagal terkoneksi dengan database", 0).show();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0376R.layout.activity_register);
        final EditText email = (EditText) findViewById(C0376R.id.mEmail);
        EditText password = (EditText) findViewById(C0376R.id.mPassword);
        Button mDaftar = (Button) findViewById(C0376R.id.mDone);
        setAtribut();
        mDaftar.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (RegisterActivity.this.validateEmail(email.getText().toString())) {
                    UserDAO userDAO = new UserDAO(RegisterActivity.this.mName.getText().toString(), RegisterActivity.this.mEmail.getText().toString(), RegisterActivity.this.mTelp.getText().toString(), RegisterActivity.this.mPassword.getText().toString());
                    RegisterActivity.this.onClickRegister();
                    return;
                }
                email.setError("Invalid Email!");
            }
        });
        this.mBatal.setOnClickListener(new C03792());
    }

    private boolean validateEmail(String email) {
        return Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+").matcher(email).matches();
    }

    private void setAtribut() {
        this.mName = (EditText) findViewById(C0376R.id.mName);
        this.mEmail = (EditText) findViewById(C0376R.id.mEmail);
        this.mTelp = (EditText) findViewById(C0376R.id.mTelp);
        this.mPassword = (EditText) findViewById(C0376R.id.mPassword);
        this.mConfPassword = (EditText) findViewById(C0376R.id.mConfPassword);
        this.mBatal = (Button) findViewById(C0376R.id.mBatal);
        this.mDaftar = (Button) findViewById(C0376R.id.mDone);
    }

    public void startIntent() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        Toast.makeText(this, "Silahkan login menggunakan akun anda", 0).show();
        startActivity(intent);
    }

    public void onClickRegister() {
        if (!(this.mName.getText().toString().isEmpty() || this.mEmail.getText().toString().isEmpty() || this.mTelp.getText().toString().isEmpty() || this.mPassword.getText().toString().isEmpty())) {
            if (!this.mConfPassword.getText().toString().isEmpty()) {
                if (this.mPassword.getText().toString().equals(this.mConfPassword.getText().toString())) {
                    ((ApiClient) new Builder().baseUrl("https://nipunduit.000webhostapp.com/api/").addConverterFactory(GsonConverterFactory.create()).build().create(ApiClient.class)).registerUser(this.mName.getText().toString(), this.mEmail.getText().toString(), this.mTelp.getText().toString(), this.mPassword.getText().toString()).enqueue(new C05443());
                    return;
                } else {
                    this.mConfPassword.setError("Konfirmasi Password Tidak Sesuai");
                    return;
                }
            }
        }
        Toast.makeText(this, "Data tidak boleh ada yang kosong", 0).show();
    }
}
