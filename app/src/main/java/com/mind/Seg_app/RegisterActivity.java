package com.mind.Seg_app;

import android.app.ActivityOptions;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.mind.Seg_app.R;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * EditText field to enter the member's email
     */
    private EditText mEmailEditText;


    /**
     * EditText field to enter the member's password
     */
    private EditText mPasswordEditText;

    /**
     * EditText field to enter the member's confirm password
     */
    private EditText mConfirmPasswordEditText;

    // خاص بتعريف ادوات استقبال الحركة مثلا rlayout لتحديد مكان الحركة
    private RelativeLayout rlayout;
    // وهذا التعريف الخاص بكلاس الحركه نفسه
    private Animation animation;

    private Button btNxt1;
    private TextView tvsignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = findViewById(R.id.bgHeaderـReg1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // استقبال حركة وتحديدها في أي أداه بالضبط
        rlayout = findViewById(R.id.rlayout);
        // هنا ستكون الحركة فقظ في اداة rLayout
        animation = AnimationUtils.loadAnimation(this, R.anim.uptodowndiagonal);
        rlayout.setAnimation(animation);

        //خاص بتعاريف الادوات ارسال الحركة
        btNxt1 = findViewById(R.id.btn_next1);
        tvsignup = findViewById(R.id.tvSignUp);

        btNxt1.setOnClickListener(this);

        // Find all relevant views that we will need to read user input from
        mEmailEditText = (EditText) findViewById(R.id.edit_members_email);

        mPasswordEditText = (EditText) findViewById(R.id.edit_members_password);
        mConfirmPasswordEditText = (EditText) findViewById(R.id.edit_members_confirmpassword);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {

        save_RegisterActivity();
        if (v == btNxt1) {
            Intent intent = new Intent(RegisterActivity.this, Register2.class);
            Pair[] pairs = new Pair[1];
            pairs[0] = new Pair<View, String>(tvsignup, "tvsignup");
            ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(RegisterActivity.this, pairs);
            startActivity(intent, activityOptions.toBundle());
        }


    }

    /**
     * Get user input from editor and save new pet into SegInputValue.
     */
    private void save_RegisterActivity() {


        // Read from input fields
        // Use trim to eliminate leading or trailing white space
        String emailString = mEmailEditText.getText().toString().trim();

        String passwrdString = mPasswordEditText.getText().toString().trim();


        //String confirmpasswrdString = mConfirmPasswordEditText.getText().toString().trim();

        // Create a list of words
        //final ArrayList<data> Data = new ArrayList<data>();
      //  Data.add(new data(emailString, passwrdString));

        SharedPreferences shrd=getSharedPreferences("regester1", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=shrd.edit();
        editor.putString("email",emailString);
        editor.putString("password",passwrdString);

        editor.apply();
        Toast.makeText(this, getString(R.string.editor_insert_member_successful), Toast.LENGTH_SHORT).show();

    }


}
