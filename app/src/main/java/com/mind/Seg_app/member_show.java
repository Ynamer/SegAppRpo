package com.mind.Seg_app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.NavUtils;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MotionEvent;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;

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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.mind.Seg_app.SegContract.SegEntry;

public class member_show extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    /**
     * EditText field to enter the pet's name
     */
    private TextView mNameEditText;

    /**
     * EditText field to enter the pet's breed
     */
    private TextView mGenderEditText;

    /**
     * EditText field to enter the member age
     */
    private TextView mAgeText;

    /**
     * EditText field to enter the member phone
     */
    private TextView mPhoneNumberText;

    /**
     * EditText field to enter the member country
     */
    private TextView mCuntryText;

    /**
     * EditText field to enter the member city
     */
    private TextView mCityText;

    /**
     * EditText field to enter the member level
     */
    private TextView mLevelText;


    /**
     * EditText field to enter the member specialization
     */
    private TextView mSpecializationText;


    /**
     * EditText field to enter the member appreciation
     */
    private TextView mAppreciatonText;


    /**
     * EditText field to enter the member year
     */
    private TextView mYearText;

    /**
     * EditText field to enter the member hour
     */
    private TextView mHourText;

    /**
     * Spinner field to enter the member's state
     */
    private Spinner mStateSpinner;


    /**
     * Spinner field to enter the member's admin
     */
    private Spinner mAdminSpinner;

    /**
     * EditText field to enter the member meeting
     */
    private TextView mMeetingText;


    /**
     * Boolean flag that keeps track of whether the pet has been edited (true) or not (false)
     */
    private boolean mMemberHasChanged = false;


    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            mMemberHasChanged = true;
            return false;
        }
    };
    /**
     * EditText field to enter the member level
     */

    private int mState = SegEntry.STATE_UNACCEPTABLE;
    private int mAdmin = SegEntry.NOADMIN;
    private TextView mEngishText;
    SegDbHelper db;

    private int mGender = SegEntry.GENDER_UNKNOWN;

    private final static int EXISTING_PET_LOADER = -1;

    /**
     * Content URI for the existing pet (null if it's a new pet)
     */
    private Uri mCurrentPetUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_show);

        Intent intent = getIntent();
        Uri currentseguri = intent.getData();
        mCurrentPetUri = currentseguri;
        mNameEditText = findViewById(R.id.txt_name);
        mGenderEditText = findViewById(R.id.txt_gender);
        mAgeText = findViewById(R.id.txt_age);
        mPhoneNumberText = findViewById(R.id.txt_phone);
        mCuntryText = findViewById(R.id.txt_cuontry);
        mCityText = findViewById(R.id.txt_city);
        mLevelText = findViewById(R.id.txt_level);
        mSpecializationText = findViewById(R.id.txt_specialization);
        mAppreciatonText = findViewById(R.id.txt_appreciation);
        mYearText = findViewById(R.id.txt_Year);
        mHourText = findViewById(R.id.txt_hour);
        mMeetingText = findViewById(R.id.txt_meeting);
        mEngishText = findViewById(R.id.txt_English);
        mStateSpinner = (Spinner) findViewById(R.id.state_sppiner);
        mAdminSpinner = (Spinner) findViewById(R.id.admin_sppiner);
        db = new SegDbHelper(this);
        getSupportLoaderManager().initLoader(EXISTING_PET_LOADER, null, this);
        //Toast.makeText(this,currentseguri.toString(), Toast.LENGTH_SHORT).show();
        mStateSpinner.setOnTouchListener(mTouchListener);
        mAdminSpinner.setOnTouchListener(mTouchListener);
        setupStateSpinner();
        setupAdminSpinner();
    }


    /**
     * Setup the dropdown spinner that allows the user to select the state of the membeer.
     */
    private void setupStateSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter stateSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_state_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        stateSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        mStateSpinner.setAdapter(stateSpinnerAdapter);

        // Set the integer mSelected to the constant values
        mStateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.state_accept))) {
                        mState = SegEntry.STATE_ACCEPTABLE;
                    } else {
                        mState = SegEntry.STATE_UNACCEPTABLE;
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mState = SegEntry.STATE_UNACCEPTABLE;
            }
        });
    }

    /**
     * Setup the dropdown spinner that allows the user to select the admin of the membeer.
     */
    private void setupAdminSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter adminSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_admin_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        adminSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        mAdminSpinner.setAdapter(adminSpinnerAdapter);

        // Set the integer mSelected to the constant values
        mAdminSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.admin))) {
                        mAdmin = SegEntry.ADMIN;
                    } else {
                        mAdmin = SegEntry.NOADMIN;
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mAdmin = SegEntry.NOADMIN;
            }
        });
    }

    private void updateMember() {

        ContentValues values = new ContentValues();
        values.put(SegEntry.COLUMN_USER_STATE, mState);
        values.put(SegEntry.COLUMN_MEMBER_ADMIN, mAdmin);

        // Otherwise this is an EXISTING pet, so update the pet with content URI: mCurrentPetUri
        // and pass in the new ContentValues. Pass in null for the selection and selection args
        // because mCurrentPetUri will already identify the correct row in the database that
        // we want to modify.
        int rowsAffected = getContentResolver().update(mCurrentPetUri, values, null, null);
        // Show a toast message depending on whether or not the update was successful.
        if (rowsAffected == 0) {
            // If no rows were affected, then there was an error with the update.
            Toast.makeText(this, getString(R.string.update_Member_failed),
                    Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the update was successful and we can display a toast.
            Toast.makeText(this, getString(R.string.update_Member_successful),
                    Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_member_shwo, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_update:

                // Save pet to database
                //Toast.makeText(this,"action aupdate", Toast.LENGTH_SHORT).show();
                updateMember();
                // Exit activity
                 finish();
                return true;
            case R.id.action_delete:
                // Pop up confirmation dialog for deletion
                showDeleteConfirmationDialog();
                // Close the activity
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // If the pet hasn't changed, continue with navigating up to parent activity
                // which is the {@link CatalogActivity}.
                if (!mMemberHasChanged) {
                    NavUtils.navigateUpFromSameTask(member_show.this);
                    return true;
                }

                // Otherwise if there are unsaved changes, setup a dialog to warn the user.
                // Create a click listener to handle the user confirming that
                // changes should be discarded.
                DialogInterface.OnClickListener discardButtonClickListener =
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // User clicked "Discard" button, navigate to parent activity.
                                NavUtils.navigateUpFromSameTask(member_show.this);
                            }
                        };

                // Show a dialog that notifies the user they have unsaved changes
                showUnsavedChangesDialog(discardButtonClickListener);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * This method is called when the back button is pressed.
     */
    @Override
    public void onBackPressed() {
        // If the pet hasn't changed, continue with handling back button press
        if (!mMemberHasChanged) {
            super.onBackPressed();
            return;
        }

        // Otherwise if there are unsaved changes, setup a dialog to warn the user.
        // Create a click listener to handle the user confirming that changes should be discarded.
        DialogInterface.OnClickListener discardButtonClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // User clicked "Discard" button, close the current activity.
                        finish();
                    }
                };

        // Show dialog that there are unsaved changes
        showUnsavedChangesDialog(discardButtonClickListener);
    }


    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {


        // Since the editor shows all pet attributes, define a projection that contains
        // all columns from the pet table
        String[] projection = {
                SegEntry._ID,
                SegEntry.COLUMN_MEMBERFULLNAME,
                SegEntry.COLUMN_USER_GENDER,
                SegEntry.COLUMN_USER_AGE,
                SegEntry.COLUMN_USER_PHONENUMBER,
                SegEntry.COLUMN_USER_CUNTRY,
                SegEntry.COLUMN_USER_CITY,

                SegEntry.COLUMN_USER_LEVEL,
                SegEntry.COLUMN_USER_SPECIALIZATION,
                SegEntry.COLUMN_USER_APPRECIATION,
                SegEntry.COLUMN_MEMBER_YEAR,
                SegEntry.COLUMN_USER_HOUR,
                SegEntry.COLUMN_USER_MEETING,
                SegEntry.COLUMN_USER_ENGLISH,
                SegEntry.COLUMN_USER_STATE,
                SegEntry.COLUMN_MEMBER_ADMIN

        };

        // This loader will execute the ContentProvider's query method on a background thread
        return new CursorLoader(this,   // Parent activity context
                mCurrentPetUri,         // Query the content URI for the current pet
                projection,             // Columns to include in the resulting Cursor
                null,                   // No selection clause
                null,                   // No selection arguments
                null);                  // Default sort order
    }


    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
        // Proceed with moving to the first row of the cursor and reading data from it
        // (This should be the only row in the cursor)


        if (cursor.moveToFirst()) {
            // Find the columns of pet attributes that we're interested in
            int nameColumnIndex = cursor.getColumnIndex(SegEntry.COLUMN_MEMBERFULLNAME);
            int genderColumnIndex = cursor.getColumnIndex(SegEntry.COLUMN_USER_GENDER);
            int ageColumnIndex = cursor.getColumnIndex(SegEntry.COLUMN_USER_AGE);

            int phoneColumnIndex = cursor.getColumnIndex(SegEntry.COLUMN_USER_PHONENUMBER);
            int countryColumnIndex = cursor.getColumnIndex(SegEntry.COLUMN_USER_CUNTRY);
            int cityColumnIndex = cursor.getColumnIndex(SegEntry.COLUMN_USER_CITY);
            int levelColumnIndex = cursor.getColumnIndex(SegEntry.COLUMN_USER_LEVEL);
            int specializationColumnIndex = cursor.getColumnIndex(SegEntry.COLUMN_USER_SPECIALIZATION);
            int aappreciationColumnIndex = cursor.getColumnIndex(SegEntry.COLUMN_USER_APPRECIATION);

            int yearColumnIndex = cursor.getColumnIndex(SegEntry.COLUMN_MEMBER_YEAR);
            int hourColumnIndex = cursor.getColumnIndex(SegEntry.COLUMN_USER_HOUR);
            int meetingColumnIndex = cursor.getColumnIndex(SegEntry.COLUMN_USER_MEETING);
            int engishColumnIndex = cursor.getColumnIndex(SegEntry.COLUMN_USER_ENGLISH);
            int stateColumnIndex = cursor.getColumnIndex(SegEntry.COLUMN_USER_STATE);
            int adminColumnIndex = cursor.getColumnIndex(SegEntry.COLUMN_MEMBER_ADMIN);

            // Extract out the value from the Cursor for the given column index
            String name = cursor.getString(nameColumnIndex);
            int gender = cursor.getInt(genderColumnIndex);
            String age = cursor.getString(ageColumnIndex);

            String phone = cursor.getString(phoneColumnIndex);
            String country = cursor.getString(countryColumnIndex);
            String city = cursor.getString(cityColumnIndex);

            String level = cursor.getString(levelColumnIndex);
            String specialization = cursor.getString(specializationColumnIndex);
            String appreciation = cursor.getString(aappreciationColumnIndex);

            String year = cursor.getString(yearColumnIndex);
            int hour = cursor.getInt(hourColumnIndex);
            int meeting = cursor.getInt(meetingColumnIndex);

            int english = cursor.getInt(engishColumnIndex);
            int stat = cursor.getInt(stateColumnIndex);
            int admin = cursor.getInt(adminColumnIndex);

            // Update the views on the screen with the values from the database
            mNameEditText.setText(name);
            mAgeText.setText(age);
            mPhoneNumberText.setText(phone);
            mCuntryText.setText(country);
            mCityText.setText(city);
            mLevelText.setText(level);
            mSpecializationText.setText(specialization);
            mAppreciatonText.setText(appreciation);
            mYearText.setText(year);

            // mHourText.setText(hour);
           /* mNameEditText.setText(name);
            mAgeText.setText(age);
            mNameEditText.setText(name);
            mAgeText.setText(age);
            mNameEditText.setText(name);
            mAgeText.setText(age);

            */

            // Gender is a dropdown spinner, so map the constant value from the database
            // into one of the dropdown options (0 is Unknown, 1 is Male, 2 is Female).
            // Then call setSelection() so that option is displayed on screen as the current selection.
            switch (gender) {
                case SegEntry.GENDER_MALE:
                    mGenderEditText.setText("Male");
                    break;
                case SegEntry.GENDER_FEMALE:
                    mGenderEditText.setText("Female");
                    break;
                default:
                    mGenderEditText.setText("Unknown");
                    break;


            }
            // Gender is a dropdown spinner, so map the constant value from the database
            // into one of the dropdown options (0 is Unknown, 1 is Male, 2 is Female).
            // Then call setSelection() so that option is displayed on screen as the current selection.
            switch (hour) {
                case SegEntry.TENHOUR:
                    mHourText.setText("10 hour");
                    break;
                case SegEntry.FIFTEENHOUR:
                    mHourText.setText("15 hour");
                    break;
                case SegEntry.TWENTYHOUR:
                    mHourText.setText("20 hour");
                    break;
                default:
                    mHourText.setText("above 20 hour");
                    break;


            }

            // Gender is a dropdown spinner, so map the constant value from the database
            // into one of the dropdown options (0 is Unknown, 1 is Male, 2 is Female).
            // Then call setSelection() so that option is displayed on screen as the current selection.
            switch (meeting) {
                case SegEntry.MEETING_UNKNOWN:
                    mMeetingText.setText("Unknown");
                    break;
                case SegEntry.CANMEETING:
                    mMeetingText.setText("He Can Attend");
                    break;
                default:
                    mMeetingText.setText("He Cant Attend");
                    break;
            }


            // Gender is a dropdown spinner, so map the constant value from the database
            // into one of the dropdown options (0 is Unknown, 1 is Male, 2 is Female).
            // Then call setSelection() so that option is displayed on screen as the current selection.
            switch (english) {
                case SegEntry.ENGLISH_UNKNOWN:
                    mEngishText.setText("Unknown");
                    break;
                case SegEntry.ENGLISHBEGINNER:
                    mEngishText.setText("Beginner");
                    break;
                case SegEntry.ENGLISHINTERMEDIATE:
                    mEngishText.setText("Intermediate");
                    break;
                default:
                    mEngishText.setText("Advanced");
                    break;
            }


            // Gender is a dropdown spinner, so map the constant value from the database
            // into one of the dropdown options (0 is Unknown, 1 is Male, 2 is Female).
            // Then call setSelection() so that option is displayed on screen as the current selection.
            switch (stat) {
                case SegEntry.STATE_ACCEPTABLE:
                    mStateSpinner.setSelection(0);
                    break;

                default:
                    mStateSpinner.setSelection(1);
                    break;
            }
            // Gender is a dropdown spinner, so map the constant value from the database
            // into one of the dropdown options (0 is Unknown, 1 is Male, 2 is Female).
            // Then call setSelection() so that option is displayed on screen as the current selection.
            switch (admin) {
                case SegEntry.ADMIN:
                    mAdminSpinner.setSelection(0);
                    break;

                default:
                    mAdminSpinner.setSelection(1);
                    break;
            }


        }
    }


    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }


    private void showDeleteConfirmationDialog() {
        // Create an AlertDialog.Builder and set the message, and click listeners
        // for the postivie and negative buttons on the dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_dialog_msg);
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Delete" button, so delete the pet.
                deletePet();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Cancel" button, so dismiss the dialog
                // and continue editing the pet.
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    private void showUnsavedChangesDialog(
            DialogInterface.OnClickListener discardButtonClickListener) {
        // Create an AlertDialog.Builder and set the message, and click listeners
        // for the positive and negative buttons on the dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.unsaved_changes_dialog_msg);
        builder.setPositiveButton(R.string.discard, discardButtonClickListener);
        builder.setNegativeButton(R.string.keep_editing, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Keep editing" button, so dismiss the dialog
                // and continue editing the pet.
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }




    /**
     * Perform the deletion of the member in the database.
     */
    private void deletePet() {
        // Only perform the delete if this is an existing pet.
        if (mCurrentPetUri != null) {
            // Call the ContentResolver to delete the pet at the given content URI.
            // Pass in null for the selection and selection args because the mCurrentPetUri
            // content URI already identifies the pet that we want.
            int rowsDeleted = getContentResolver().delete(mCurrentPetUri, null, null);

// Show a toast message depending on whether or not the delete was successful.
            if (rowsDeleted == 0) {
                // If no rows were deleted, then there was an error with the delete.
                Toast.makeText(this, getString(R.string.editor_delete_member_failed),
                        Toast.LENGTH_SHORT).show();
            } else {
                // Otherwise, the delete was successful and we can display a toast.
                Toast.makeText(this, getString(R.string.editor_delete_member_successful),
                        Toast.LENGTH_SHORT).show();

                finish();
            }
        }
    }



}