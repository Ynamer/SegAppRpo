package com.mind.Seg_app;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;


import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.util.Log;
import android.widget.Toast;
import com.mind.Seg_app.SegContract.SegEntry;


import com.mind.Seg_app.R;

public class Home extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private final static int SEG_LOADER = 1;
    SegCursorAdapter mCursorAdapter;

    private int mGender = SegEntry.GENDER_UNKNOWN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        // Find the ListView which will be populated with the pet data
        final ListView SegListView = (ListView) findViewById(R.id.listView_seg);


        // set up an Adapter to create a list item for each row of pet data in the cursor
        // there is no pet data yet(Until the loader finishes ) so pass in null for the cursor
        mCursorAdapter = new SegCursorAdapter(this, null);
        SegListView.setAdapter(mCursorAdapter);
        // Setup the iem clik listener
        SegListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //Creat new intent to go to {@Link EditorActivty}
                Intent intent = new Intent(Home.this, member_show.class);
                //From the content URI that represents the specific pet that was clicked on,
                //Fer exaple , the URI would bee "content//cim.example.android.pets/pets/2
                //if the pet with ID 2 was clicked on.
               Uri currentpeturi = ContentUris.withAppendedId(SegEntry.CONTENT_URI, id);
                //Toast.makeText(Home.this, position+"   " +id, Toast.LENGTH_SHORT).show();

                // Set the URI on the data field of the intent
                intent.setData(currentpeturi);
                // Lanuch the {@link EditorActivty}to display the data for the current pet.
                startActivity(intent);


                // TODO Auto-generated method stub

                // Getting listview click value into String variable.
                // String TempListViewClickedValue = petListView.getItemAtPosition(position).toString();
                // Toast.makeText(this,"2",Toast.LENGTH_LONG).show();

                // Sending value to another activity using intent.
                // intent.putExtra("ListViewClickedValue", TempListViewClickedValue);

                //startActivity(intent);

            }
        });
        // Kick off the loader
        //getLoaderManager().initLoader(PET_LOADER,null, CatalogActivity);
        getSupportLoaderManager().initLoader(SEG_LOADER, null, this);

    }






    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
        //Define a projection that a specifies the columns from the table we care abut.
        String[] projection = {
                SegEntry._ID,
                SegEntry.COLUMN_MEMBERFULLNAME,
                SegEntry.COLUMN_USER_SPECIALIZATION
        };
        // This loader will execute the ContentProviders query Method on a background thread
        return new CursorLoader(this,
                SegEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);

    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        //Update {@link PetCursorAdapter} with this new cursor containing Updated pet data
        mCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        //callback called when the data needs to be deleted
        mCursorAdapter.swapCursor(null);

    }
}