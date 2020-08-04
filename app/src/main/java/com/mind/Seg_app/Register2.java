package com.mind.Seg_app;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.mind.Seg_app.R;
import com.mind.Seg_app.SegContract.SegEntry;


import java.util.ArrayList;

public class Register2 extends AppCompatActivity implements View.OnClickListener {

    /**
     * EditText field to enter the member's fullname
     */
    private EditText mFullNameEditText;

    /**
     * EditText field to enter the member's gender
     */
    private Spinner mGenderSpinner;
    /**
     * EditText field to enter the member's age
     */
    private EditText mAgeEditText;

    /**
     * Gender of the pet. The possible valid values are in the PetContract.java file:
     * {@link SegEntry#GENDER_UNKNOWN}, {@link SegEntry#GENDER_MALE}, or
     * {@link SegEntry#GENDER_FEMALE}.
     */
    private int mGender = SegEntry.GENDER_UNKNOWN;


    // خاص بتعريف ادوات استقبال الحركة مثلا rlayout لتحديد مكان الحركة
    private RelativeLayout rlayout;
    private ImageView image1, image;
    // وهذا التعريف الخاص بكلاس الحركه نفسه
    private Animation animation, animation1;

    private Button btNxt2;
    private TextView tvpersonal;

    /**
     * Content URI for the existing member (null if it's a new member)
     */
    private Uri mCurrentPetUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        //هنا استقبال حركة تكست التول بار وجعل العنوان كأنه يتحرك

        Toolbar toolbar = findViewById(R.id.bgHeaderـreg2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // استقبال حركة وتحديدها في أي أداه بالضبط
        rlayout = findViewById(R.id.rlayoutـR2);
        // هنا ستكون الحركة فقظ في اداة rLayout
        animation = AnimationUtils.loadAnimation(this, R.anim.trytrans);
        rlayout.setAnimation(animation);

        // استقبال حركة وتحديدها في أي أداه بالضبط
        image1 = findViewById(R.id.circle3);
        // هنا ستكون الحركة فقظ في اداة circle3
        animation1 = AnimationUtils.loadAnimation(this, R.anim.uptodowndiagonal);
        image1.setAnimation(animation1);

        // استقبال حركة وتحديدها في أي أداه بالضبط
        image = findViewById(R.id.circle1);
        // هنا ستكون الحركة فقظ في اداة circle1
        animation1 = AnimationUtils.loadAnimation(this, R.anim.uptodowndiagonal);
        image.setAnimation(animation1);


        //خاص بتعاريف الادوات ارسال الحركة
        btNxt2 = findViewById(R.id.btn_next2);
        tvpersonal = findViewById(R.id.tvPersonal);

        btNxt2.setOnClickListener(this);

        // Find all relevant views that we will need to read user input from
        mFullNameEditText = (EditText) findViewById(R.id.EdtFull_Name);
        mAgeEditText = (EditText) findViewById(R.id.EdtAge);
        mGenderSpinner = (Spinner) findViewById(R.id.sp_gender);
        setupgenderSpinner();

        //data =new data();
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

        save_Register2();

        if (v == btNxt2) {
            Intent intent = new Intent(Register2.this, Register3_Contact.class);
            Pair[] pairs = new Pair[1];
            pairs[0] = new Pair<View, String>(tvpersonal, "tvpersonal");
            ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(Register2.this, pairs);
            startActivity(intent, activityOptions.toBundle());
        }
    }

    /**
     * Setup the dropdown spinner that allows the user to select the gender of the pet.
     */
    private void setupgenderSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_gender_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        mGenderSpinner.setAdapter(genderSpinnerAdapter);

        // Set the integer mSelected to the constant values
        mGenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.gender_male))) {
                        mGender = SegEntry.GENDER_MALE;
                    } else if (selection.equals(getString(R.string.gender_female))) {
                        mGender = SegEntry.GENDER_FEMALE;
                    } else {
                        mGender = SegEntry.GENDER_UNKNOWN;
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mGender = SegEntry.GENDER_UNKNOWN;
            }
        });
    }

   /* private void update_member(){

        // Read from input fields
        // Use trim to eliminate leading or trailing white space
        String fullnamel = mFullNameEditText.getText().toString().trim();
        String ageString = mAgeEditText.getText().toString().trim();

        // Create a ContentValues object where column names are the keys,
        // and pet attributes from the editor are the values.
        ContentValues values = new ContentValues();
        values.put(SegEntry.COLUMN_MEMBERFULLNAME, fullnamel);
        values.put(SegEntry.COLUMN_USER_GENDER, mGender);
        values.put(SegEntry.COLUMN_USER_AGE, ageString);


        // Otherwise this is an EXISTING member, so update the pet with content URI: mCurrentmemberUri
        // and pass in the new ContentValues. Pass in null for the selection and selection args
        // because mCurrentmemberUri will already identify the correct row in the database that
        // we want to modify.
        int rowsAffected = getContentResolver().update(mCurrentPetUri, values, null, null);
        // Show a toast message depending on whether or not the update was successful.
        if (rowsAffected == 0) {
            // If no rows were affected, then there was an error with the update.
            Toast.makeText(this, getString(R.string.editor_update_member_failed),
                    Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the update was successful and we can display a toast.
            Toast.makeText(this, getString(R.string.editor_update_member_successful),
                    Toast.LENGTH_SHORT).show();
        }
    }

    */

    /**
     * Get user input from editor and save new pet into SegInputValue.
     */
    private void save_Register2() {
        // Read from input fields
        // Use trim to eliminate leading or trailing white space
        String fullname = mFullNameEditText.getText().toString().trim();

        String ageString = mAgeEditText.getText().toString().trim();


        SharedPreferences shrd = getSharedPreferences("regester1", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shrd.edit();
        editor.putString("fullname", fullname);
        editor.putInt("gender", mGender);
        editor.putString("age", ageString);
        editor.apply();
        //  SharedPreferences shrd=getSharedPreferences("regester1", Context.MODE_PRIVATE);
        //  String email=shrd.getString("email","no_data");
        //Toast.makeText(this, email,
              //  Toast.LENGTH_SHORT).show();

    }


}