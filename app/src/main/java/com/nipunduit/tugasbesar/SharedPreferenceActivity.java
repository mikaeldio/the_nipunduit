package com.nipunduit.tugasbesar;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class SharedPreferenceActivity extends AppCompatActivity {
    private SharedPreferences sp;
    private final String name="myShared";
    public static final int mode = Activity.MODE_PRIVATE;

    private String budgetBulanan = "";
    private String targetBulanan = "";

    private void setForm() {
        EditText mPendapatan = (EditText) findViewById(R.id.mPendapatan);
        EditText mTarget = (EditText) findViewById(R.id.mTarget);
        mPendapatan.setText(budgetBulanan);
        mTarget.setText(targetBulanan);
    }

    private  void loadPreferences()
    {
        sp = getSharedPreferences(name,mode);
        if(sp!=null)
        {
            budgetBulanan=sp.getString("budgetBulanan","");
            targetBulanan=sp.getString("targetBulanan","");
        }
    }
    private void savePreferences(){
        EditText mPendapatan = (EditText) findViewById(R.id.mPendapatan);
        EditText mTarget= (EditText) findViewById(R.id.mTarget);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("budgetBulanan",mPendapatan.getText().toString());
        editor.putString("targetBulanan",mTarget.getText().toString());
        editor.apply();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_preferences);
        loadPreferences();
        setForm();
    }
    public void buttonDone(View v)
    {
        savePreferences();
    }

}
