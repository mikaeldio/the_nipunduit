package com.nipunduit.tugasbesar;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;


public class MainActivity extends AppCompatActivity {

    private Button mLogin;
    private Button mRegister;
    private EditText mUsername;
    private EditText mPassword;
    private static String cryptoPass = "" ;
    String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mUsername=(EditText)findViewById(R.id.mUsername);
<<<<<<< HEAD
        mPassword=(EditText)findViewById(R.id.mOldPassword);
        mLogin=(Button) findViewById(R.id.mLogin_login);
        mRegister=(Button) findViewById(R.id.mDone);

=======
        mPassword=(EditText)findViewById(R.id.mPassword);
        mLogin=(Button) findViewById(R.id.mLogin);
        mRegister=(Button) findViewById(R.id.mDaftar);
>>>>>>> cf65a5f5bea7830721b3036e4773d129aa1f0706
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (MainActivity.this,HomeActivity.class);
                startActivity(i);
            }
        });
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (MainActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });

    }
    public static String encryptIt(String value) {
        try {
            DESKeySpec keySpec = new DESKeySpec(cryptoPass.getBytes("UTF8"));

            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey key = keyFactory.generateSecret(keySpec); byte[] clearText = value.getBytes("UTF8"); // Cipher is not thread safe
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            String encrypedValue = Base64.encodeToString(cipher.doFinal(clearText), Base64.DEFAULT);
            Log.d("TAG", "Encrypted: " + value + " -> " + encrypedValue); return encrypedValue; } catch (InvalidKeyException e)
        { e.printStackTrace(); }
        catch (UnsupportedEncodingException e)
        { e.printStackTrace(); }
        catch (InvalidKeySpecException e)
        { e.printStackTrace(); }
        catch (NoSuchAlgorithmException e)
        { e.printStackTrace(); }
        catch (BadPaddingException e)
        { e.printStackTrace(); }
        catch (NoSuchPaddingException e)
        { e.printStackTrace(); }
        catch (IllegalBlockSizeException e)
        { e.printStackTrace(); }
        return value; };

}
