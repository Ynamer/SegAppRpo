package com.mind.Seg_app;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
public class member_show extends AppCompatActivity   {
    /**
     * EditText field to enter the pet's name
     */
    private TextView mNameEditText;

    /**
     * EditText field to enter the pet's breed
     */
    private TextView mSummrayEditText;
    SegDbHelper db;

    private int mGender = SegEntry.GENDER_UNKNOWN;

    private final static int EXISTING_PET_LOADER = -1;

    /** Content URI for the existing pet (null if it's a new pet) */
    private Uri mCurrentPetUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_show);

        mNameEditText =  (TextView) findViewById(R.id.Text_name);
        mSummrayEditText = (TextView) findViewById(R.id.Text_sp);
       db=new SegDbHelper(this);
       // getSupportLoaderManager().initLoader(EXISTING_PET_LOADER, null, this);

        Toast.makeText(this,mCurrentPetUri.toString(), Toast.LENGTH_SHORT).show();
    }

    /*@NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {


        // Since the editor shows all pet attributes, define a projection that contains
        // all columns from the pet table
        String[] projection = {
                SegEntry._ID,
                SegEntry.COLUMN_MEMBERFULLNAME,
                SegEntry.COLUMN_USER_SPECIALIZATION

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
            int breedColumnIndex = cursor.getColumnIndex(SegEntry.COLUMN_USER_SPECIALIZATION);


            // Extract out the value from the Cursor for the given column index
            String name = cursor.getString(nameColumnIndex);
            String breed = cursor.getString(breedColumnIndex);

            // Update the views on the screen with the values from the database
            mNameEditText.setText(name);
            mSummrayEditText.setText(breed);

        }


    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }

     */
   // Cursor cursor=db.GetSingleRow();
}