package com.mind.Seg_app;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mind.Seg_app.R;

import java.util.ArrayList;

public class Re4_qualification extends AppCompatActivity implements View.OnClickListener{

    /**
     * EditText field to enter the member's level
     */
    private EditText mEdtlevel;

    /**
     * EditText field to enter the member's specialization
     */
    private EditText mEdtSpecialization;

    /**
     * EditText field to enter the member's confirm EdtAppreciation
     */
    private EditText mEdtAppreciation;

    /**
     * EditText field to enter the member's confirm EdtYearExp
     */
    private EditText mEdtYearExp;

    // خاص بتعريف ادوات استقبال الحركة مثلا rlayout لتحديد مكان الحركة
    private RelativeLayout rlayout;

    // وهذا التعريف الخاص بكلاس الحركه نفسه
    private Animation animation , animation1;

    private Button btNxt4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re4_qualification);



        // استقبال حركة وتحديدها في أي أداه بالضبط
        rlayout = findViewById(R.id.rlayout_R4_qualification);

        //  و circle3 هنا ستكون الحركة في اداة rLayout
        animation = AnimationUtils.loadAnimation(this, R.anim.translate1);
        rlayout.setAnimation(animation);

        /*Toolbar toolbar = findViewById(R.id.bgHeaderـRe4quali);        // هنا ستكون الحركة فقظ في اداة toolbar
        animation1 = AnimationUtils.loadAnimation(this, R.anim.trytrans);
        Toolbar.setAnimation(animation1);

         */
        //خاص بتعاريف الادوات ارسال الحركة
        btNxt4 = findViewById(R.id.btn_next4);
        btNxt4.setOnClickListener(this);

        // Find all relevant views that we will need to read user input from
        mEdtlevel = (EditText) findViewById(R.id.Edtlevel);
        mEdtSpecialization = (EditText) findViewById(R.id.EdtSpecialization);
        mEdtAppreciation = (EditText) findViewById(R.id.EdtAppreciation);
        mEdtYearExp = (EditText) findViewById(R.id.EdtYearExp);



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
        save_Re4_qualification();

        if (v == btNxt4) {
            Intent intent = new Intent(Re4_qualification.this, Re_LernSelf.class);

            startActivity(intent);
        }


    }

    /**
     * Get user input from editor and save new pet into SegInputValue.
     */
    private void save_Re4_qualification() {
        // Read from input fields
        // Use trim to eliminate leading or trailing white space
        String levelString = mEdtlevel.getText().toString().trim();
        String specializationString = mEdtSpecialization.getText().toString().trim();
        String appreciationString = mEdtAppreciation.getText().toString().trim();
        String yearString = mEdtYearExp.getText().toString().trim();


        SharedPreferences shrd = getSharedPreferences("regester1", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shrd.edit();
        editor.putString("level", levelString);
        editor.putString("specialization", specializationString);
        editor.putString("appreciation", appreciationString);
        editor.putString("year", yearString);
        editor.apply();


        Toast.makeText(this, getString(R.string.editor_insert_member_successful),
                Toast.LENGTH_SHORT).show();

    }
}