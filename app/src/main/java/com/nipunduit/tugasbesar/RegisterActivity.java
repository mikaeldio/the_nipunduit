package com.nipunduit.tugasbesar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    private EditText mName;
    private EditText mEmail;
    private EditText mPassword;
    private EditText mConfPassword;
    private EditText mTelp;

    private Button mBatal;
    private Button mDaftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mName=(EditText)findViewById(R.id.mUsername);
        mPassword=(EditText)findViewById(R.id.mPassword);
        mEmail=(EditText) findViewById(R.id.mEmail);
        mConfPassword=(EditText) findViewById(R.id.mConfPassword);
        mTelp=(EditText) findViewById(R.id.mTelp);
        mBatal=(Button)findViewById(R.id.mBatal);
        mDaftar=(Button)findViewById(R.id.mDaftar);

        mBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
