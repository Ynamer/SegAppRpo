package com.mind.Seg_app;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.mind.Seg_app.SegContract.SegEntry;
import java.util.ArrayList;

public class Re_LernSelf extends AppCompatActivity implements View.OnClickListener {
   // data data;
    private int mHour = SegEntry.TIME_UNKNOWN;
    private int mMeeting = SegEntry.MEETING_UNKNOWN;
    private int mEnglish = SegEntry.ENGLISH_UNKNOWN;
    /**
     * EditText field to enter the user's hour
     */
    private Spinner mHourSpinner;

    /**
     * EditText field to enter the user's Meeting
     */
    private Spinner mMeetingSpinner;

    /**
     * EditText field to enter the user's English
     */
    private Spinner mEnglishSpinner;
    /**
     * EditText field to enter the user's English
     */

    private Button mbtnSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re__lern_self);
        // Find all relevant views that we will need to read user input from
        mHourSpinner = (Spinner) findViewById(R.id.Sp_timlern);
        mMeetingSpinner = (Spinner) findViewById(R.id.Sp_meeting);
        mEnglishSpinner = (Spinner) findViewById(R.id.Sp_english);
       mbtnSave = (Button) findViewById(R.id.btn_save);

        mbtnSave.setOnClickListener(this);
        setupHourSpinner();
        setupMeetingSpinner();
        setupEnglishSpinner();

       // data=new data();
    }

    /**
     * Setup the dropdown spinner that allows the user to select the Hour of the user.
     */
    private void setupHourSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter hourSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_hours_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        hourSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        mHourSpinner.setAdapter(hourSpinnerAdapter);

        // Set the integer mSelected to the constant values
        mHourSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.tenhours))) {
                        mHour = SegEntry.TENHOUR;
                    } else if (selection.equals(getString(R.string.fifteenhours))) {
                        mHour = SegEntry.FIFTEENHOUR;
                    } else if (selection.equals(getString(R.string.twentyhours))) {
                        mHour = SegEntry.TWENTYHOUR;
                    }

                } else {
                    mHour = SegEntry.ABOVETWENTYHOUR;
                }
            }


            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mHour = SegEntry.TIME_UNKNOWN;
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {



        insertmember();
       //Toast.makeText(this, "jjjj", Toast.LENGTH_LONG).show();


    }


    private void setupMeetingSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter meetingSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_meetings_option, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        meetingSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        mMeetingSpinner.setAdapter(meetingSpinnerAdapter);

        // Set the integer mSelected to the constant values
        mMeetingSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.can_meeting))) {
                        mMeeting = SegEntry.CANMEETING;
                    } else {
                        mMeeting = SegEntry.CANOTMEETING;
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mMeeting = SegEntry.MEETING_UNKNOWN;
            }
        });
    }


    /**
     * Setup the dropdown spinner that allows the user to select the English Level of the user.
     */
    private void setupEnglishSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter englishSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_english_option, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        englishSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        mEnglishSpinner.setAdapter(englishSpinnerAdapter);

        // Set the integer mSelected to the constant values
        mEnglishSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.Beginnerenglish))) {
                        mEnglish = SegEntry.ENGLISHBEGINNER;
                    } else if (selection.equals(getString(R.string.intermidateenglish))) {
                        mEnglish = SegEntry.ENGLISHINTERMEDIATE;
                    } else if (selection.equals(getString(R.string.advance_english))) {
                        mEnglish = SegEntry.ENGLISHADVANCED;
                    }

                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mEnglish = SegEntry.ENGLISH_UNKNOWN;
            }
        });
    }


    /**
     * Get user input from editor and save new pet into database.
     */
    private void insertmember() {

        SharedPreferences shrd = getSharedPreferences("regester1", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shrd.edit();
        editor.putInt("hour", mHour);
        editor.putInt("meeting", mMeeting);
        editor.putInt("english", mEnglish);
        editor.apply();


        SharedPreferences shrd_receve=getSharedPreferences("regester1", Context.MODE_PRIVATE);

        String emailString =shrd_receve.getString("email","");

        String passwrdString =shrd_receve.getString("password","");
        String fullname = shrd_receve.getString("fullname","");
        int gender = shrd_receve.getInt("gender",0);
        String age = shrd_receve.getString("age","");
        String phone = shrd_receve.getString("phone","");
        String country = shrd_receve.getString("country","");
        String city = shrd_receve.getString("city","");
        String level =shrd_receve.getString("level","");
        String specialization =shrd_receve.getString("specialization","");
        String appreciation = shrd_receve.getString("appreciation","");
        String yearexp = shrd_receve.getString("year","");

        int hour = shrd_receve.getInt("hour",0);
        int meeting = shrd_receve.getInt("meeting",0);

        int english = shrd_receve.getInt("english",0);


        // Create a ContentValues object where column names are the keys,
        // and pet attributes from the editor are the values.
        ContentValues values = new ContentValues();
        values.put(SegEntry.COLUMN_USER_EMAIL, emailString);
        values.put(SegEntry.COLUMN_MEMBERFULLNAME, fullname);
        values.put(SegEntry.COLUMN_USER_PASSWORD, passwrdString);
        values.put(SegEntry.COLUMN_USER_GENDER, gender);
        values.put(SegEntry.COLUMN_USER_AGE, age);
        values.put(SegEntry.COLUMN_USER_PHONENUMBER, phone);
        values.put(SegEntry.COLUMN_USER_CUNTRY, country);
        values.put(SegEntry.COLUMN_USER_CITY, city);
        values.put(SegEntry.COLUMN_USER_LEVEL, level);
        values.put(SegEntry.COLUMN_USER_SPECIALIZATION, specialization);
        values.put(SegEntry.COLUMN_USER_APPRECIATION, appreciation);
        values.put(SegEntry.COLUMN_MEMBER_YEAR, yearexp);
        values.put(SegEntry.COLUMN_USER_HOUR, hour);
        values.put(SegEntry.COLUMN_USER_MEETING, meeting);
        values.put(SegEntry.COLUMN_USER_ENGLISH, english);
        values.put(SegEntry.COLUMN_USER_STATE, 1);
        values.put(SegEntry.COLUMN_MEMBER_ADMIN,1);

        // Insert a new pet into the provider, returning the content URI for the new pet.
        Uri newUri = getContentResolver().insert(SegEntry.CONTENT_URI, values);

        // Show a toast message depending on whether or not the insertion was successful
        if (newUri == null) {
            // If the new content URI is null, then there was an error with insertion.
            Toast.makeText(this, getString(R.string.editor_insert_fember_failed),
                    Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast.
            Toast.makeText(this, getString(R.string.editor_insert_member_successful),
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

}