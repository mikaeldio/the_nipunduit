package com.nipunduit.tugasbesar;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InfoKeuangan extends AppCompatActivity {

    private SharedPreferences sp;
    private final String name = "myShared";
    private static final int mode = Activity.MODE_PRIVATE;

    private String Pendapatan= "";
    private String TargetTabungan= "";

    private EditText mPendapatan;
    private EditText mTargetTabungan;
    private Button mBatal;
    private Button mDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_keuangan);

        mPendapatan= (EditText)findViewById(R.id.mPendapatan);
        mTargetTabungan=(EditText)findViewById(R.id.mTarget);
        mBatal=(Button) findViewById(R.id.mBatal);
        mDone=(Button) findViewById(R.id.mDone);

        mPendapatan.setText(Pendapatan);
        mTargetTabungan.setText(TargetTabungan);

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
