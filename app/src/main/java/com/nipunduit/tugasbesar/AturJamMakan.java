package com.nipunduit.tugasbesar;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.Calendar;

public class AturJamMakan extends AppCompatActivity {
    public static final int mode = 0;
    public static final int notifikasi = 1;
    private String Time = "";
    private String Time2 = "";
    private String Time3 = "";
    private SharedPreferences ma;
    private Bundle nBundle;
    private String nEmail;
    private final String name = "myShared";
    EditText time;
    EditText time2;
    EditText time3;
    private String txtjudul = "Tambah pengeluaran";
    private String txtpesan = "Silahkan Atur Tambahan Pengeluaran";

    /* renamed from: com.nipunduit.tugasbesar.AturJamMakan$1 */
    class C03571 implements OnClickListener {

        /* renamed from: com.nipunduit.tugasbesar.AturJamMakan$1$1 */
        class C03561 implements OnTimeSetListener {
            C03561() {
            }

            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                EditText editText = AturJamMakan.this.time;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(selectedHour);
                stringBuilder.append(":");
                stringBuilder.append(selectedMinute);
                editText.setText(stringBuilder.toString());
            }
        }

        C03571() {
        }

        public void onClick(View v) {
            Calendar mcurrentTime = Calendar.getInstance();
            TimePickerDialog mTimePicker = new TimePickerDialog(AturJamMakan.this, new C03561(), mcurrentTime.get(11), mcurrentTime.get(12), true);
            mTimePicker.setTitle("Select Time");
            mTimePicker.show();
        }
    }

    /* renamed from: com.nipunduit.tugasbesar.AturJamMakan$2 */
    class C03592 implements OnClickListener {

        /* renamed from: com.nipunduit.tugasbesar.AturJamMakan$2$1 */
        class C03581 implements OnTimeSetListener {
            C03581() {
            }

            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                EditText editText = AturJamMakan.this.time2;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(selectedHour);
                stringBuilder.append(":");
                stringBuilder.append(selectedMinute);
                editText.setText(stringBuilder.toString());
            }
        }

        C03592() {
        }

        public void onClick(View v) {
            Calendar mcurrentTime = Calendar.getInstance();
            TimePickerDialog mTimePicker = new TimePickerDialog(AturJamMakan.this, new C03581(), mcurrentTime.get(11), mcurrentTime.get(12), true);
            mTimePicker.setTitle("Select Time");
            mTimePicker.show();
        }
    }

    /* renamed from: com.nipunduit.tugasbesar.AturJamMakan$3 */
    class C03613 implements OnClickListener {

        /* renamed from: com.nipunduit.tugasbesar.AturJamMakan$3$1 */
        class C03601 implements OnTimeSetListener {
            C03601() {
            }

            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                EditText editText = AturJamMakan.this.time3;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(selectedHour);
                stringBuilder.append(":");
                stringBuilder.append(selectedMinute);
                editText.setText(stringBuilder.toString());
            }
        }

        C03613() {
        }

        public void onClick(View v) {
            Calendar mcurrentTime = Calendar.getInstance();
            TimePickerDialog mTimePicker = new TimePickerDialog(AturJamMakan.this, new C03601(), mcurrentTime.get(11), mcurrentTime.get(12), true);
            mTimePicker.setTitle("Select Time");
            mTimePicker.show();
        }
    }

    /* renamed from: com.nipunduit.tugasbesar.AturJamMakan$4 */
    class C03624 implements OnClickListener {
        C03624() {
        }

        public void onClick(View v) {
            AturJamMakan.this.savePreferences();
            Toast.makeText(AturJamMakan.this.getApplicationContext(), "Jam Makan Berhasil Diatur...", 0).show();
            Intent intent = new Intent(AturJamMakan.this.getApplicationContext(), HomeActivity.class);
            Bundle mBundle = new Bundle();
            mBundle.putString(NotificationCompat.CATEGORY_EMAIL, AturJamMakan.this.nEmail);
            intent.putExtra("login", mBundle);
            AturJamMakan.this.startActivity(intent);
        }
    }

    /* renamed from: com.nipunduit.tugasbesar.AturJamMakan$5 */
    class C03635 implements OnClickListener {
        C03635() {
        }

        public void onClick(View v) {
            AturJamMakan.this.time.setText("");
            AturJamMakan.this.time2.setText("");
            AturJamMakan.this.time3.setText("");
            Toast.makeText(AturJamMakan.this.getApplicationContext(), "Jam Makan Batal Diatur...", 0).show();
            Intent intent = new Intent(AturJamMakan.this.getApplicationContext(), HomeActivity.class);
            Bundle mBundle = new Bundle();
            mBundle.putString(NotificationCompat.CATEGORY_EMAIL, AturJamMakan.this.nEmail);
            intent.putExtra("login", mBundle);
            AturJamMakan.this.startActivity(intent);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0376R.layout.activity_aturjammakan);
        this.nBundle = getIntent().getBundleExtra("login");
        this.nEmail = this.nBundle.getString(NotificationCompat.CATEGORY_EMAIL);
        tampilNotifikasi(this.txtjudul.toString(), this.txtpesan.toString(), new Intent(getApplicationContext(), TambahPengeluaran.class));
        loadPreferences();
        this.time = (EditText) findViewById(C0376R.id.txtTime);
        this.time2 = (EditText) findViewById(C0376R.id.txtTime1);
        this.time3 = (EditText) findViewById(C0376R.id.txtTime2);
        this.time.setText(this.Time);
        this.time2.setText(this.Time2);
        this.time3.setText(this.Time3);
        this.time.setOnClickListener(new C03571());
        this.time2.setOnClickListener(new C03592());
        this.time3.setOnClickListener(new C03613());
        ((Button) findViewById(C0376R.id.btnSaveJamMakan)).setOnClickListener(new C03624());
        ((Button) findViewById(C0376R.id.btnBatalJamMakan)).setOnClickListener(new C03635());
        if (this.time.equals(Long.valueOf(System.currentTimeMillis()))) {
            tampilNotifikasi(this.txtjudul.toString(), this.txtpesan.toString(), new Intent(getApplicationContext(), MainActivity.class));
        }
    }

    private void loadPreferences() {
        this.ma = getSharedPreferences("myShared", 0);
        if (this.ma != null) {
            this.Time = this.ma.getString("time", "");
            this.Time2 = this.ma.getString("time2", "");
            this.Time3 = this.ma.getString("time3", "");
        }
    }

    private void savePreferences() {
        this.time = (EditText) findViewById(C0376R.id.txtTime);
        this.time2 = (EditText) findViewById(C0376R.id.txtTime1);
        this.time3 = (EditText) findViewById(C0376R.id.txtTime2);
        Editor editor = this.ma.edit();
        editor.putString("time", this.time.getText().toString());
        editor.putString("time2", this.time2.getText().toString());
        editor.putString("time3", this.time3.getText().toString());
        editor.apply();
    }

    private void tampilNotifikasi(String s, String s1, Intent intent) {
        Notification notification = new Builder(this).setSmallIcon(C0376R.mipmap.ic_launcher).setAutoCancel(true).setContentIntent(PendingIntent.getActivity(this, 1, intent, 134217728)).setContentTitle(s).setSmallIcon(C0376R.mipmap.ic_launcher).setLargeIcon(BitmapFactory.decodeResource(getResources(), C0376R.mipmap.ic_launcher)).setContentText(s1).build();
        notification.flags |= 16;
        ((NotificationManager) getSystemService("notification")).notify(1, notification);
    }
}
