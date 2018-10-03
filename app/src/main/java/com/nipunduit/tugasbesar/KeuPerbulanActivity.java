package com.nipunduit.tugasbesar;

import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;

public class KeuPerbulanActivity extends AppCompatActivity {
    private Bundle nBundle;
    private String nEmail;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0376R.layout.activity_keu_perbulan);
        this.nBundle = getIntent().getBundleExtra("login");
        this.nEmail = this.nBundle.getString(NotificationCompat.CATEGORY_EMAIL);
    }
}
