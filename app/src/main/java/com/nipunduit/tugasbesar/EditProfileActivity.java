package com.nipunduit.tugasbesar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditProfileActivity extends AppCompatActivity {
    private EditText mName;
    private EditText mPassword;
    private EditText mConfPassword;
    private EditText mTelp;
    private Button mBatal;
    private Button mDone;
    private String nEmail;

    private Bundle nBundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        nBundle = getIntent().getBundleExtra("login");
        nEmail = nBundle.getString("email");

        setAtribut();
        mDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRegister();
            }
        });
        mBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditProfileActivity.this, HomeActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putString("email",nEmail);
                intent.putExtra("login",mBundle);
                startActivity(intent);
            }
        });
    }

    private void setAtribut() {
        mName =  findViewById(R.id.mName);
        mTelp = findViewById(R.id.mTelp);
        mPassword = findViewById(R.id.mPassword);
        mConfPassword = findViewById(R.id.mConfPassword);
        mBatal = findViewById(R.id.mBatal);
        mDone = findViewById(R.id.mDone);
    }

    private void onClickRegister() {
        if(mName.getText().toString().isEmpty() ||  nEmail.isEmpty() || mTelp.getText().toString().isEmpty()
                || mPassword.getText().toString().isEmpty() || mConfPassword.getText().toString().isEmpty()){
            Toast.makeText(this, "Data tidak boleh ada yang kosong", Toast.LENGTH_SHORT).show();
        }
        else{
            String mPass = mPassword.getText().toString();
            String mConfPass = mConfPassword.getText().toString();
            if(mPass.equals(mConfPass)){
                Retrofit.Builder builder = new Retrofit.Builder()
                        .baseUrl("https://nipunduit.000webhostapp.com/api/")
                        .addConverterFactory(GsonConverterFactory.create());
                Retrofit retrofit=builder.build();
                ApiClient apiClient = retrofit.create(ApiClient.class);
                Call<UserDAO> userDAOCall = apiClient.editUser(mName.getText().toString(), nEmail.toString(),
                        mTelp.getText().toString(),mPassword.getText().toString());
                userDAOCall.enqueue(new Callback<UserDAO>() {
                    @Override
                    public void onResponse(Call<UserDAO> call, Response<UserDAO> response) {
                        if(response.isSuccessful()){
                            String error = response.body().getError();
                            if(error.equals("true")){
                                String error_message = response.body().getError_msg();
                                Toast.makeText(EditProfileActivity.this, error_message, Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(EditProfileActivity.this, "Edit akun berhasil", Toast.LENGTH_SHORT).show();
                                startIntent();
                            }
                        }
                        else{
                            Toast.makeText(EditProfileActivity.this,"Edit akun gagal", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserDAO> call, Throwable t) {
                        Toast.makeText(EditProfileActivity.this,"Gagal terkoneksi dengan database", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else{
                mConfPassword.setError("Konfirmasi Password Tidak Sesuai");
            }
        }
    }

    private void startIntent() {
        Intent  intent =  new Intent(getApplicationContext(), HomeActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putString("email",nEmail);
        mBundle.putString("password",mPassword.getText().toString());
        intent.putExtra("login",mBundle);
        startActivity(intent);
    }
}
