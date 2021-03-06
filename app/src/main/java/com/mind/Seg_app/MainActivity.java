package com.mind.Seg_app;


import android.app.ActivityOptions;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mind.Seg_app.SegContract.SegEntry;

import com.mind.Seg_app.R;

public class MainActivity extends AppCompatActivity {
    SegDbHelper db;
    private ImageButton btRegister;
    private TextView tvLogin;
    private EditText mEmail;
    private EditText mPassword;

    private Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEmail = findViewById(R.id.edt_loginEmail);
        tvLogin = findViewById(R.id.tvLogin);
        mPassword = findViewById(R.id.Edt_loginPassword);
        btRegister = findViewById(R.id.btRegister);
        btnlogin = findViewById(R.id.btn_login);
        // btRegister.setOnClickListener(this);
        //  btnlogin.setOnClickListener(this);
        db = new SegDbHelper(this);

        /*
         * code it works one time only while instull the app
         */

        if (getPreferences(MODE_PRIVATE).getBoolean("is_first_run", true)) {
            /*
             * your code here
             */
            insertadmin();
            //Toast.makeText(this, "successfully create admin", Toast.LENGTH_LONG).show();


            getPreferences(MODE_PRIVATE).edit().putBoolean("is_first_run", false).commit();
        }
    }


    private void insertadmin() {
        // Create a ContentValues object where column names are the keys,
        // and pet attributes from the editor are the values.
        ContentValues values = new ContentValues();
        values.put(SegEntry.COLUMN_USER_EMAIL, "yasir99as@gmail.com");
        values.put(SegEntry.COLUMN_MEMBERFULLNAME, "Yasir Alnamer");
        values.put(SegEntry.COLUMN_USER_PASSWORD, "ahtahs");
        values.put(SegEntry.COLUMN_USER_GENDER, 1);
        values.put(SegEntry.COLUMN_USER_AGE, "25");
        values.put(SegEntry.COLUMN_USER_PHONENUMBER, "00967717171460");
        values.put(SegEntry.COLUMN_USER_CUNTRY, "Yemen");
        values.put(SegEntry.COLUMN_USER_CITY, "Ibb");
        values.put(SegEntry.COLUMN_USER_LEVEL, "6");
        values.put(SegEntry.COLUMN_USER_SPECIALIZATION, "Android Developer");
        values.put(SegEntry.COLUMN_USER_APPRECIATION, "65");
        values.put(SegEntry.COLUMN_MEMBER_YEAR, "3 Year");
        values.put(SegEntry.COLUMN_USER_HOUR, 20);
        values.put(SegEntry.COLUMN_USER_MEETING, 1);
        values.put(SegEntry.COLUMN_USER_ENGLISH, 1);
        values.put(SegEntry.COLUMN_USER_STATE, 0);
        values.put(SegEntry.COLUMN_MEMBER_ADMIN, 0);
        //values.put(SegEntry.COLUMN_USER_STATE, state);

        // Insert a new pet into the provider, returning the content URI for the new pet.
        Uri newUri = getContentResolver().insert(SegContract.SegEntry.CONTENT_URI, values);

        // Show a toast message depending on whether or not the insertion was successful
        if (newUri == null) {
            // If the new content URI is null, then there was an error with insertion.
            Toast.makeText(this, "dont create admin",
                    Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast.
            Toast.makeText(this, "successfully create admin", Toast.LENGTH_SHORT).show();
        }
    }

    public void login(View view) {

        String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();


        boolean login = db.checkMemberLogine(email, password);
        boolean state = db.checkMemberState(email, password, "0");
        boolean admin = db.checkMemberadmin(email, password, "0");

        if (login == true) {

            if (state == true) {

                if (admin == true) {
                    Toast.makeText(this, "successfully Logged in .", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, Home.class);
                    startActivity(intent);

                    //حفظ ايميل المستخدم عند الدخول مؤقتا لكي يظهر على الشاشة الرئيسية
                    SharedPreferences shrd=getSharedPreferences("save_name", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=shrd.edit();
                    editor.putString("email",email);
                    editor.apply();

                } else {
                    Toast.makeText(this, "successfully Logged in .", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, Reception.class);
                    startActivity(intent);


                    //حفظ ايميل المستخدم عند الدخول مؤقتا لكي يظهر على الشاشة الرئيسية
                    SharedPreferences shrd=getSharedPreferences("save_name", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=shrd.edit();
                    editor.putString("email",email);
                    editor.apply();

                }
            } else {
                Toast.makeText(this, "لم يتم قبول طلبك بعد", Toast.LENGTH_SHORT).show();

            }

        } else {
            Toast.makeText(this, "the email or password  Uncorrected.", Toast.LENGTH_SHORT).show();

        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void bttn_regester(View view) {

        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(tvLogin, "tvLogin");
        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);
        startActivity(intent, activityOptions.toBundle());

    }
}
