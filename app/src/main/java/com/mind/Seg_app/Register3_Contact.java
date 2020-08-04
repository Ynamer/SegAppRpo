package com.mind.Seg_app;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.mind.Seg_app.R;

import java.util.ArrayList;

public class Register3_Contact extends AppCompatActivity implements View.OnClickListener {


    /**
     * EditText field to enter the member's PhoneNumber
     */
    private EditText mPhoneNumberEditText;


    /**
     * EditText field to enter the member's Country
     */
    private EditText mCountryEditText;

    /**
     * EditText field to enter the member's City
     */
    private EditText mCityEditText;


    // خاص بتعريف ادوات استقبال الحركة مثلا rlayout لتحديد مكان الحركة
    private RelativeLayout rlayout;
    private ImageView image1, image;
    // وهذا التعريف الخاص بكلاس الحركه نفسه
    private Animation animation, animation1, animation2;

    private Button btNxt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register3__contact);


        Toolbar toolbar = findViewById(R.id.bgHeaderـreg3_contact);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // استقبال حركة وتحديدها في أي أداه بالضبط
        rlayout = findViewById(R.id.rlayoutـR3_contact);

        //  و circle3 هنا ستكون الحركة في اداة rLayout
        animation = AnimationUtils.loadAnimation(this, R.anim.translate_up_down);
        rlayout.setAnimation(animation);

        image1 = findViewById(R.id.circle1);
        // هنا ستكون الحركة فقظ في اداة circle3
        animation1 = AnimationUtils.loadAnimation(this, R.anim.trytrans);
        image1.setAnimation(animation1);

        image = findViewById(R.id.circle3);
        // هنا ستكون الحركة فقظ في اداة circle3
        animation2 = AnimationUtils.loadAnimation(this, R.anim.trytrans);
        image.setAnimation(animation2);


        //خاص بتعاريف الادوات ارسال الحركة
        btNxt3 = findViewById(R.id.Next3);
        btNxt3.setOnClickListener(this);


        // Find all relevant views that we will need to read user input from
        mPhoneNumberEditText = (EditText) findViewById(R.id.EdtPhone_Number);

        mCountryEditText = (EditText) findViewById(R.id.Edtcountry);
        mCityEditText = (EditText) findViewById(R.id.Edtcity);


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

        save_Register3_Contact();
        if (v == btNxt3) {
            Intent intent = new Intent(Register3_Contact.this, Re4_qualification.class);

            startActivity(intent);
        }


    }

    /**
     * Get user input from editor and save new pet into SegInputValue.
     */
    private void save_Register3_Contact() {
        // Read from input fields
        // Use trim to eliminate leading or trailing white space
        String phonenumberString = mPhoneNumberEditText.getText().toString().trim();
        String countryString = mCountryEditText.getText().toString().trim();
        String cityString = mCityEditText.getText().toString().trim();

        SharedPreferences shrd = getSharedPreferences("regester1", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shrd.edit();
        editor.putString("phone", phonenumberString);
        editor.putString("country", countryString);
        editor.putString("city", cityString);

        editor.apply();


        Toast.makeText(this, getString(R.string.editor_insert_member_successful),
                Toast.LENGTH_SHORT).show();

    }
}



