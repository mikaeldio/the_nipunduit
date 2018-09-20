package com.nipunduit.tugasbesar;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
//test lupuz;
    private Button mLogin;
    private Button mRegister;
    private EditText mEmail;
    private EditText mPassword;
    ProgressDialog loading;

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAtribut();

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLogin();
            }
        });
    }

    private void setAtribut() {
        mEmail = findViewById(R.id.mEmail);
        mPassword = findViewById(R.id.mPassword);
        mLogin = findViewById(R.id.mLogin_login);
        mRegister = findViewById(R.id.mDone);
    }

    public void startIntent(){
        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putString("email",mEmail.getText().toString());
        mBundle.putString("password",mPassword.getText().toString());
        intent.putExtra("login",mBundle);
        startActivity(intent);
    }

    public void onClickLogin(){
        if(mEmail.getText().toString().isEmpty() || mPassword.getText().toString().isEmpty()){
            Toast.makeText(this, "Email atau Password tidak boleh ada yang kosong", Toast.LENGTH_SHORT).show();
        }
        else{
            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl("https://nipunduit.000webhostapp.com/api/")
                    .addConverterFactory(GsonConverterFactory.create());
            Retrofit retrofit=builder.build();
            ApiClient apiClient=retrofit.create(ApiClient.class);
            Call<UserDAO> userDAOCall=apiClient.loginUser(mEmail.getText().toString(), mPassword.getText().toString());
            userDAOCall.enqueue(new Callback<UserDAO>() {
                @Override
                public void onResponse(Call<UserDAO> call, Response<UserDAO> response) {
                    Toast.makeText(MainActivity.this, "Login  berhasil", Toast.LENGTH_SHORT).show();
                    startIntent();
                }
                @Override
                public void onFailure(Call<UserDAO> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Terjadi kesalahan saat login", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
