package com.mind.Seg_app;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mind.Seg_app.SegContract.SegEntry;

/**
 * Database helper for Seg app. Manages database creation and version management.
 */
public class SegDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = SegDbHelper.class.getSimpleName();

    /**
     * Name of the database file
     */
    private static final String DATABASE_NAME = "Seg.db";


    SQLiteDatabase db;


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + SegEntry.TABLE_NAME;
    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 3;

    /**
     * Constructs a new instance of {@link SegDbHelper}.
     *
     * @param context of the app
     */
    public SegDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the user table
        String SQL_CREATE_PETS_TABLE = "CREATE TABLE " + SegEntry.TABLE_NAME + " ("
                + SegEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SegEntry.COLUMN_USER_EMAIL + " TEXT NOT NULL, "
                + SegEntry.COLUMN_USER_PASSWORD + " TEXT NOT NULL, "
                + SegEntry.COLUMN_MEMBERFULLNAME + " TEXT NOT NULL, "
                + SegEntry.COLUMN_USER_GENDER + " INTEGER NOT NULL,"
                + SegEntry.COLUMN_USER_AGE + " TEXT NOT NULL ,"
                + SegEntry.COLUMN_USER_PHONENUMBER + " TEXT NOT NULL , "
                + SegEntry.COLUMN_USER_CUNTRY + " TEXT NOT NULL ,"
                + SegEntry.COLUMN_USER_CITY + " TEXT NOT NULL ,"
                + SegEntry.COLUMN_USER_LEVEL + " TEXT NOT NULL  ,"
                + SegEntry.COLUMN_USER_SPECIALIZATION + " TEXT NOT NULL  ,"
                + SegEntry.COLUMN_USER_APPRECIATION + " TEXT NOT NULL  , "
                + SegEntry.COLUMN_MEMBER_YEAR + " TEXT NOT NULL  , "
                + SegEntry.COLUMN_USER_HOUR + " INTEGER NOT NULL  , "
                + SegEntry.COLUMN_USER_MEETING + " INTEGER NOT NULL , "
                + SegEntry.COLUMN_USER_ENGLISH + " INTEGER NOT NULL  , "
                + SegEntry.COLUMN_USER_STATE + " INTEGER NOT NULL,"
                + SegEntry.COLUMN_MEMBER_ADMIN + " INTEGER NOT NULL);";


        // Execute the SQL statement
        db.execSQL(SQL_CREATE_PETS_TABLE);
    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
        // The database is still at version 1, so there's nothing to do be done here.
    }


    /**
     * This method to check user and password while sign in or not
     *
     * @param email
     * @param password
     * @return true/false
     */
    public boolean checkMemberLogine(String email, String password) {
        // array of columns to fetch
        String[] columns = {
                SegEntry._ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = SegEntry.COLUMN_USER_EMAIL + " = ?" + " AND " + SegEntry.COLUMN_USER_PASSWORD + " = ?";
        // selection arguments
        String[] selectionArgs = {email, password};
        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(SegEntry.TABLE_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }


    /**
     * This method to check user and password while sign in or not
     *
     * @param email
     * @param password
     * @return true/false
     */
    public boolean checkMemberState(String email, String password, String state) {
        // array of columns to fetch
        String[] columns = {
                SegEntry._ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = SegEntry.COLUMN_USER_EMAIL + " = ?" + " AND " + SegEntry.COLUMN_USER_PASSWORD + " = ?" + " AND " + SegEntry.COLUMN_USER_STATE + " =?";
        // selection arguments
        String[] selectionArgs = {email, password, state};
        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(SegEntry.TABLE_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    /**
     * This method to check user and password while sign in or not
     *
     * @param email
     * @param password
     * @return true/false
     */
    public boolean checkMemberadmin(String email, String password, String admin) {
        // array of columns to fetch
        String[] columns = {
                SegEntry._ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = SegEntry.COLUMN_USER_EMAIL + " = ?" + " AND " + SegEntry.COLUMN_USER_PASSWORD + " = ?" + " AND " + SegEntry.COLUMN_MEMBER_ADMIN + " =?";
        // selection arguments
        String[] selectionArgs = {email, password, admin};
        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(SegEntry.TABLE_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }


}