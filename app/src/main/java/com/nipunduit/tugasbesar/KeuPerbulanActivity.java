package com.nipunduit.tugasbesar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class KeuPerbulanActivity extends AppCompatActivity {

    private String nEmail;

    private Bundle nBundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keu_perbulan);

        nBundle=getIntent().getBundleExtra("login");
        nEmail= nBundle.getString("email");
    }
}
