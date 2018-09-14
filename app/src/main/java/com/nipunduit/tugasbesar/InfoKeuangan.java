package com.nipunduit.tugasbesar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InfoKeuangan extends AppCompatActivity {

    private EditText mPendapatan;
    private EditText mTargetTabungan;
    private Button mBatal;
    private Button mDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_keuangan);

        mPendapatan=(EditText)findViewById(R.id.mPendapatan);
        mTargetTabungan=(EditText)findViewById(R.id.mTarget);
        mBatal=(Button) findViewById(R.id.mBatal);
        mDone=(Button) findViewById(R.id.mDone);

        mBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (InfoKeuangan.this,HomeActivity.class);
                startActivity(i);
            }
        });
        mDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (InfoKeuangan.this,HomeActivity.class);
                startActivity(i);
            }
        });
    }


}
