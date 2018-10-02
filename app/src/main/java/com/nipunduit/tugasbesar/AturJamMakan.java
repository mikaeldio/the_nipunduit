package com.nipunduit.tugasbesar;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AturJamMakan extends AppCompatActivity {

    EditText time, time2, time3;
    private String txtjudul = "Tambah pengeluaran", txtpesan = "Silahkan Atur Tambahan Pengeluaran";
    private SharedPreferences ma;
    private final String name="myShared";
    public static final int mode = Activity.MODE_PRIVATE;
    public static final int notifikasi = 1;
    private String Time="";
    private String Time2="";
    private String Time3="";
    private String nEmail;

    private Bundle nBundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aturjammakan);

        nBundle=getIntent().getBundleExtra("login");
        nEmail= nBundle.getString("email");

        // membuat komponen Inten
        Intent intent = new Intent(getApplicationContext(), TambahPengeluaran.class);
        // memanggil method untuk menampilkan notifikasi
        // dengan mengirimkan data yang dikirim dari komponen EditText
        tampilNotifikasi(txtjudul.toString()
                , txtpesan.toString(), intent);

        loadPreferences();
        //  initiate the edit text
        time = (EditText) findViewById(R.id.txtTime);
        time2 = (EditText) findViewById(R.id.txtTime1);
        time3 = (EditText) findViewById(R.id.txtTime2);
        time.setText(Time);
        time2.setText(Time2);
        time3.setText(Time3);



        // perform click event listener on edit text

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AturJamMakan.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        time.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        time2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AturJamMakan.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        time2.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });
        time3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AturJamMakan.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        time3.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        Button btnsave = (Button) findViewById(R.id.btnSaveJamMakan);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                savePreferences();
                Toast.makeText(getApplicationContext(),"Jam Makan Berhasil Diatur...",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(AturJamMakan.this,HomeActivity.class);
                startActivity(i);
            }
        });
        Button btnbatal = (Button)findViewById(R.id.btnBatalJamMakan);
        btnbatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time.setText("");
                time2.setText("");
                time3.setText("");
                Toast.makeText(getApplicationContext(),"Jam Makan Batal Diatur...",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(AturJamMakan.this,HomeActivity.class);
                startActivity(i);
            }
        });
        long msTime = System.currentTimeMillis();
        if (time.equals(msTime))
        {
            Intent intn = new Intent(getApplicationContext(), MainActivity.class);
            // memanggil method untuk menampilkan notifikasi
            // dengan mengirimkan data yang dikirim dari komponen EditText
            tampilNotifikasi(txtjudul.toString()
                    , txtpesan.toString(), intn);
        }
    }


    private void loadPreferences()
    {
        ma = getSharedPreferences(name,mode);
        if(ma!=null)
        {
            Time = ma.getString("time","");
            Time2=ma.getString("time2","");
            Time3=ma.getString("time3","");

        }
    }

    private void savePreferences()
    {
        time = (EditText) findViewById(R.id.txtTime);
        time2 = (EditText) findViewById(R.id.txtTime1);
        time3 = (EditText) findViewById(R.id.txtTime2);
        SharedPreferences.Editor editor = ma.edit();
        editor.putString("time",time.getText().toString());
        editor.putString("time2",time2.getText().toString());
        editor.putString("time3",time3.getText().toString());
        editor.apply();

    }


    private void tampilNotifikasi(String s, String s1, Intent intent) {
        // membuat komponen pending intent
        PendingIntent pendingIntent = PendingIntent.getActivity(AturJamMakan.this
                , notifikasi, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // membuat komponen notifikasi
        NotificationCompat.Builder builder = new NotificationCompat.Builder(AturJamMakan.this);
        Notification notification;
        notification = builder.setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setContentTitle(s)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(AturJamMakan.this.getResources()
                        , R.mipmap.ic_launcher))
                .setContentText(s1)
                .build();

        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        NotificationManager notificationManager = (NotificationManager) AturJamMakan.this
                .getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notifikasi, notification);
    }
}
