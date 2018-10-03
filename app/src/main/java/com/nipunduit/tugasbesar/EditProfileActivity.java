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

public class EditProfileActivity extends AppCompatActivity {
    private Button mBatal;
    private EditText mConfPassword;
    private Button mDone;
    private EditText mName;
    private EditText mPassword;
    private EditText mTelp;
    private Bundle nBundle;
    private String nEmail;

    /* renamed from: com.nipunduit.tugasbesar.EditProfileActivity$1 */
    class C03641 implements OnClickListener {
        C03641() {
        }

        public void onClick(View v) {
            EditProfileActivity.this.onClickRegister();
        }
    }

    /* renamed from: com.nipunduit.tugasbesar.EditProfileActivity$2 */
    class C03652 implements OnClickListener {
        C03652() {
        }

        public void onClick(View v) {
            Intent intent = new Intent(EditProfileActivity.this, HomeActivity.class);
            Bundle mBundle = new Bundle();
            mBundle.putString(NotificationCompat.CATEGORY_EMAIL, EditProfileActivity.this.nEmail);
            intent.putExtra("login", mBundle);
            EditProfileActivity.this.startActivity(intent);
        }
    }

    /* renamed from: com.nipunduit.tugasbesar.EditProfileActivity$3 */
    class C05393 implements Callback<UserDAO> {
        C05393() {
        }

        public void onResponse(Call<UserDAO> call, Response<UserDAO> response) {
            if (response.isSuccessful()) {
                if (((UserDAO) response.body()).getError().equals("true")) {
                    Toast.makeText(EditProfileActivity.this, ((UserDAO) response.body()).getError_msg(), 0).show();
                } else {
                    Toast.makeText(EditProfileActivity.this, "Edit akun berhasil", 0).show();
                    EditProfileActivity.this.startIntent();
                }
                return;
            }
            Toast.makeText(EditProfileActivity.this, "Edit akun gagal", 0).show();
        }

        public void onFailure(Call<UserDAO> call, Throwable t) {
            Toast.makeText(EditProfileActivity.this, "Gagal terkoneksi dengan database", 0).show();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0376R.layout.activity_edit_profile);
        this.nBundle = getIntent().getBundleExtra("login");
        this.nEmail = this.nBundle.getString(NotificationCompat.CATEGORY_EMAIL);
        setAtribut();
        this.mDone.setOnClickListener(new C03641());
        this.mBatal.setOnClickListener(new C03652());
    }

    private void setAtribut() {
        this.mName = (EditText) findViewById(C0376R.id.mName);
        this.mTelp = (EditText) findViewById(C0376R.id.mTelp);
        this.mPassword = (EditText) findViewById(C0376R.id.mPassword);
        this.mConfPassword = (EditText) findViewById(C0376R.id.mConfPassword);
        this.mBatal = (Button) findViewById(C0376R.id.mBatal);
        this.mDone = (Button) findViewById(C0376R.id.mDone);
    }

    private void onClickRegister() {
        if (!(this.mName.getText().toString().isEmpty() || this.nEmail.isEmpty() || this.mTelp.getText().toString().isEmpty() || this.mPassword.getText().toString().isEmpty())) {
            if (!this.mConfPassword.getText().toString().isEmpty()) {
                if (this.mPassword.getText().toString().equals(this.mConfPassword.getText().toString())) {
                    ((ApiClient) new Builder().baseUrl("https://nipunduit.000webhostapp.com/api/").addConverterFactory(GsonConverterFactory.create()).build().create(ApiClient.class)).editUser(this.mName.getText().toString(), this.nEmail.toString(), this.mTelp.getText().toString(), this.mPassword.getText().toString()).enqueue(new C05393());
                    return;
                } else {
                    this.mConfPassword.setError("Konfirmasi Password Tidak Sesuai");
                    return;
                }
            }
        }
        Toast.makeText(this, "Data tidak boleh ada yang kosong", 0).show();
    }

    private void startIntent() {
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putString(NotificationCompat.CATEGORY_EMAIL, this.nEmail);
        mBundle.putString("password", this.mPassword.getText().toString());
        intent.putExtra("login", mBundle);
        startActivity(intent);
    }
}
