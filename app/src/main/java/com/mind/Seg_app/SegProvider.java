package com.mind.Seg_app;

import com.mind.Seg_app.SegContract.SegEntry;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;


public class SegProvider extends ContentProvider {

    /**
     * Tag for the log messages
     */
    public static final String LOG_TAG = SegProvider.class.getSimpleName();

    /**
     * Database helper object
     */
    private SegDbHelper mDbHelper;
    /**
     * URI matcher code for the content URI for the pets table
     */
    private static final int USERS = 100;

    /**
     * URI matcher code for the content URI for a single pet in the pets table
     */
    private static final int USER_ID = 101;

    /**
     * UriMatcher object to match a content URI to a corresponding code.
     * The input passed into the constructor represents the code to return for the root URI.
     * It's common to use NO_MATCH as the input for this case.
     */
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    // Static initializer. This is run the first time anything is called from this class.
    static {
        // The calls to addURI() go here, for all of the content URI patterns that the provider
        // should recognize. All paths added to the UriMatcher have a corresponding code to return
        // when a match is found.

        sUriMatcher.addURI(SegContract.CONTENT_AUTHORITY, SegContract.PATH_USERS, USERS);
        sUriMatcher.addURI(SegContract.CONTENT_AUTHORITY, SegContract.PATH_USERS + "/#", USER_ID);
    }

    @Override
    public boolean onCreate() {
        mDbHelper = new SegDbHelper(getContext());
        return true;
    }

    /**
     * Perform the query for the given URI. Use the given projection, selection, selection arguments, and sort order.
     */
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        // Get readable database
        SQLiteDatabase database = mDbHelper.getReadableDatabase();

        // This cursor will hold the result of the query
        Cursor cursor = null;


        // Figure out if the URI matcher can match the URI to a specific code
        int match = sUriMatcher.match(uri);
        switch (match) {
            case USERS:
                // For the PETS code, query the pets table directly with the given
                // projection, selection, selection arguments, and sort order. The cursor
                // could contain multiple rows of the pets table.
                cursor = database.query(SegContract.SegEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case USER_ID:
                // For the PET_ID code, extract out the ID from the URI.
                // For an example URI such as "content://com.example.android.pets/pets/3",
                // the selection will be "_id=?" and the selection argument will be a
                // String array containing the actual ID of 3 in this case.
                //
                // For every "?" in the selection, we need to have an element in the selection
                // arguments that will fill in the "?". Since we have 1 question mark in the
                // selection, we have 1 String in the selection arguments' String array.
                selection = SegContract.SegEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};

                // This will perform a query on the pets table where the _id equals 3 to return a
                // Cursor containing that row of the table.
                cursor = database.query(SegEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }


        // Set notification URI on the Cursor,
        // so we know what content URI the Cursor was created for.
        // If the data at this URI changes, then we know we need to update the Cursor.
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        // Return the cursor
        return cursor;
    }

    /**
     * Insert new data into the provider with the given ContentValues.
     */

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {

        final int match = sUriMatcher.match(uri);
        switch (match) {
            case USERS:
                return insertmember(uri, contentValues);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }

    /**
     * Insert a pet into the database with the given content values. Return the new content URI
     * for that specific row in the database.
     */
    private Uri insertmember(Uri uri, ContentValues values) {
        // Check that the email is not null
        String email = values.getAsString(SegEntry.COLUMN_USER_EMAIL);
        if (email == null) {
            throw new IllegalArgumentException("member requires a email");
        }

        // Check that the password is not null
        String password = values.getAsString(SegEntry.COLUMN_USER_PASSWORD);
        if (password == null) {
            throw new IllegalArgumentException("member requires a password");
        }

        // Check that the fullname is not null
        String fullname = values.getAsString(SegEntry.COLUMN_MEMBERFULLNAME);
        if (fullname == null) {
            throw new IllegalArgumentException("member requires a fullname");
        }


        // Check that the gender is valid
        Integer gender = values.getAsInteger(SegEntry.COLUMN_USER_GENDER);
        if (gender == null || !SegEntry.isValidGender(gender)) {
            throw new IllegalArgumentException("Pet requires valid gender");
        }

        // Check that the age is not null
        String age = values.getAsString(SegEntry.COLUMN_USER_AGE);
        if (age == null) {
            throw new IllegalArgumentException("member requires a age");
        }

        // Check that the phone number is not null
        String phonnumber = values.getAsString(SegEntry.COLUMN_USER_PHONENUMBER);
        if (phonnumber == null) {
            throw new IllegalArgumentException("member requires a phonnumber");
        }


        // Check that the member cuntry is not null
        String cuntry = values.getAsString(SegEntry.COLUMN_USER_CUNTRY);
        if (cuntry == null) {
            throw new IllegalArgumentException("member requires a cuntry");
        }

        // Check that the member city is not null
        String city = values.getAsString(SegEntry.COLUMN_USER_CITY);
        if (city == null) {
            throw new IllegalArgumentException("member requires a city");
        }


        // Check that the member level is not null
        String level = values.getAsString(SegEntry.COLUMN_USER_LEVEL);
        if (level == null) {
            throw new IllegalArgumentException("member requires a level");
        }

        // Check that the member Specialization is not null
        String Specialization = values.getAsString(SegEntry.COLUMN_USER_SPECIALIZATION);
        if (Specialization == null) {
            throw new IllegalArgumentException("member requires a Specialization");
        }

        // Check that the member Appreciation is not null
        String Appreciation = values.getAsString(SegEntry.COLUMN_USER_APPRECIATION);
        if (Appreciation == null) {
            throw new IllegalArgumentException("member requires a Appreciation");
        }


        // Check that the hour is valid
        Integer hour = values.getAsInteger(SegEntry.COLUMN_USER_HOUR);
        if (hour == null || !SegEntry.isValidHour(hour)) {
            throw new IllegalArgumentException("Pet requires valid hour");
        }

        // Check that the meeting is valid
        Integer meeting = values.getAsInteger(SegEntry.COLUMN_USER_MEETING);
        if (meeting == null || !SegEntry.isValidMeeting(meeting)) {
            throw new IllegalArgumentException("Pet requires valid meeting");
        }

        // Check that the english is valid
        Integer english = values.getAsInteger(SegEntry.COLUMN_USER_ENGLISH);
        if (english == null || !SegEntry.isValidEnglish(english)) {
            throw new IllegalArgumentException("Pet requires valid english");
        }

        // Check that the status is valid
        Integer status = values.getAsInteger(SegEntry.COLUMN_USER_STATE);
        if (status == null || !SegEntry.isValidState(status)){
            throw new IllegalArgumentException("member requires a status");
        }

        // Check that the admin is valid
        Integer admin = values.getAsInteger(SegEntry.COLUMN_MEMBER_ADMIN);
        if (admin == null || !SegEntry.isValidAdmin(admin)) {
            throw new IllegalArgumentException("member requires a admin");
        }

        // Get writeable database
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        // Insert the new pet with the given values
        long id = database.insert(SegEntry.TABLE_NAME, null, values);
        // If the ID is -1, then the insertion failed. Log an error and return null.
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }
        // Notify all listeners that the data has changed for the pet content URI
        getContext().getContentResolver().notifyChange(uri, null);

        // Return the new URI with the ID (of the newly inserted row) appended at the end
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection,
                      String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case USERS:
                return updateMember(uri, contentValues, selection, selectionArgs);
            case USER_ID:
                // For the PET_ID code, extract out the ID from the URI,
                // so we know which row to update. Selection will be "_id=?" and selection
                // arguments will be a String array containing the actual ID.
                selection = SegEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updateMember(uri, contentValues, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
    }


    /**
     * Update pets in the database with the given content values. Apply the changes to the rows
     * specified in the selection and selection arguments (which could be 0 or 1 or more pets).
     * Return the number of rows that were successfully updated.
     */
    private int updateMember(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        //If the {@link SegEntry#COLUMN_USER_STATE} key is present,
        //check that the state value is valid.
        if (values.containsKey(SegEntry.COLUMN_USER_STATE)) {
            Integer state = values.getAsInteger(SegEntry.COLUMN_USER_STATE);
            if (state == null || !SegEntry.isValidState(state)) {
                throw new IllegalArgumentException("member requires valid state");
            }
        }


        //If the {@link SegEntry#COLUMN_MEMBER_ADMIN} key is present,
        //check that the admin value is valid.
        if (values.containsKey(SegEntry.COLUMN_MEMBER_ADMIN)) {
            Integer admin = values.getAsInteger(SegEntry.COLUMN_MEMBER_ADMIN);
            if (admin == null || !SegEntry.isValidState(admin)) {
                throw new IllegalArgumentException("member requires valid state");
            }
        }

// No need to check the breed, any value is valid (including null).

        // If there are no values to update, then don't try to update the database
        if (values.size() == 0) {
            return 0;
        }

        // Otherwise, get writeable database to update the data
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        // Perform the update on the database and get the number of rows affected
        int rowsUpdated = database.update(SegEntry.TABLE_NAME, values, selection, selectionArgs);

        // If 1 or more rows were updated, then notify all listeners that the data at the
        // given URI has changed
        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
            // Return the number of rows updated
        }
        return rowsUpdated;
    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Get writeable database
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        // Track the number of rows that were deleted
        int rowsDeleted;
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case USERS:
                // Delete all rows that match the selection and selection args
                return database.delete(SegEntry.TABLE_NAME, selection, selectionArgs);
            case USER_ID:
                // Delete a single row given by the ID in the URI
                selection = SegEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(SegEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }
        // If 1 or more rows were deleted, then notify all listeners that the data at the
        // given URI has changed
        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        // Return the number of rows deleted
        return rowsDeleted;
    }


    /**
     * Returns the MIME type of data for the content URI.
     */
    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case USERS:
                return SegEntry.CONTENT_LIST_TYPE;
            case USER_ID:
                return SegEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }


}
