package com.mind.Seg_app;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.v4.app.NavUtils;

import java.lang.reflect.Array;

public class SegContract {


    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private SegContract() {
    }

    /**
     * The "Content authority" is a name for the entire content provider, similar to the
     * relationship between a domain name and its website.  A convenient string to use for the
     * content authority is the package name for the app, which is guaranteed to be unique on the
     * device.
     */
    public static final String CONTENT_AUTHORITY = "com.mind.Seg_app";

    /**
     * Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
     * the content provider.
     */
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    /**
     * Possible path (appended to base content URI for possible URI's)
     * For instance, content://com.example.android.users/users/ is a valid path for
     * looking at user data. content://com.example.android.pets/staff/ will fail,
     * as the ContentProvider hasn't been given any information on what to do with "staff".
     */
    public static final String PATH_USERS = "users";

    /**
     * Inner class that defines constant values for the pets database table.
     * Each entry in the table represents a single usres.
     */
    public static final class SegEntry implements BaseColumns {

        /**
         * The content URI to access the pet data in the provider
         */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_USERS);

        /**
         * The MIME type of the {@link #CONTENT_URI} for a list of pets.
         */
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_USERS;

        /**
         * The MIME type of the {@link #CONTENT_URI} for a single pet.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_USERS;

        /**
         * Name of database table for users
         */
        public final static String TABLE_NAME = "users";

        /**
         * Unique ID number for the users (only for use in the database table).
         * <p>
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Email of the users.
         * <p>
         * Type: TEXT
         */
        public final static String COLUMN_USER_EMAIL = "email";

        /**
         * password of the user.
         * <p>
         * Type: TEXT
         */
        public final static String COLUMN_USER_PASSWORD = "password";

        /**
         * name of the users.
         * <p>
         * Type: TEXT
         */
        public final static String COLUMN_MEMBERFULLNAME = "name";


        public final static String COLUMN_USER_GENDER = "gender";

        /**
         * AGE of the USER.
         * <p>
         * Type: TEXT
         */
        public final static String COLUMN_USER_AGE = "age";

        /**
         * phone number of the user.
         * <p>
         * Type: TEXT
         */
        public final static String COLUMN_USER_PHONENUMBER = "phonenumber";

        /**
         * cuntry of the user.
         * <p>
         * Type: TEXT
         */
        public final static String COLUMN_USER_CUNTRY = "cuntry";

        /**
         * city of the user.
         * <p>
         * Type: TEXT
         */
        public final static String COLUMN_USER_CITY = "city";

        /**
         * level of the user.
         * <p>
         * Type: TEXT
         */
        public final static String COLUMN_USER_LEVEL = "level";

        /**
         * Specialization of the user.
         * <p>
         * Type: TEXT
         */
        public final static String COLUMN_USER_SPECIALIZATION = "Specialization";

        /**
         * Appreciation of the user.
         * <p>
         * Type: TEXT
         */
        public final static String COLUMN_USER_APPRECIATION = "Appreciation";

        /**
         * year of the user.
         * <p>
         * Type: TEXT
         */
        public final static String COLUMN_MEMBER_YEAR = "yearExperience";

        /**
         * Gender of the user.
         * <p>
         * The only possible values are {@link #TIME_UNKNOWN}, {@link #TENHOUR},
         * or {@link #FIFTEENHOUR},or {@link #TWENTYHOUR} ,{@link #ABOVETWENTYHOUR}.
         * <p>
         * Type: INTEGER
         */
        public final static String COLUMN_USER_HOUR = "hour";

        /**
         * Gender of the user.
         * <p>
         * The only possible values are {@link #MEETING_UNKNOWN}, {@link #CANMEETING},
         * or {@link #CANOTMEETING}.
         * <p>
         * Type: INTEGER
         */
        public final static String COLUMN_USER_MEETING = "meeting";


        /**
         * Gender of the user.
         * <p>
         * The only possible values are {@link #ENGLISH_UNKNOWN}, {@link #ENGLISHBEGINNER},
         * or {@link #ENGLISHINTERMEDIATE},or {@link #ENGLISHADVANCED} .
         * <p>
         * Type: INTEGER
         */
        public final static String COLUMN_USER_ENGLISH = "english";
        /**
         * state of the user.
         * <p>
         * Type: INTEGER
         */
        public final static String COLUMN_USER_STATE = "state";

        /**
         * Possible values for the Time of the user.
         */
        public static final int TIME_UNKNOWN = 0;
        public static final int TENHOUR = 10;
        public static final int FIFTEENHOUR = 15;
        public static final int TWENTYHOUR = 20;
        public static final int ABOVETWENTYHOUR = 25;

        /**
         * Returns whether or not the given gender is {@link #TIME_UNKNOWN}, {@link #TENHOUR},
         * or {@link #FIFTEENHOUR} or {@link #TWENTYHOUR} or {@link #ABOVETWENTYHOUR}.
         */
        public static boolean isValidHour(int hour) {
            if (hour == TIME_UNKNOWN || hour == TENHOUR || hour == FIFTEENHOUR || hour == TWENTYHOUR || hour == ABOVETWENTYHOUR) {
                return true;
            }
            return false;
        }


        /**
         * Possible values for the Meeting of the user.
         */
        public static final int MEETING_UNKNOWN = 0;
        public static final int CANMEETING = 1;
        public static final int CANOTMEETING = 2;

        /**
         * Returns whether or not the given Meeting is {@link #MEETING_UNKNOWN}, {@link #CANMEETING},
         * or {@link #CANOTMEETING}
         */
        public static boolean isValidMeeting(int meeting) {
            if (meeting == MEETING_UNKNOWN || meeting == CANMEETING || meeting == CANOTMEETING) {
                return true;
            }
            return false;
        }


        /**
         * Possible values for the English Level of the user.
         */
        public static final int ENGLISH_UNKNOWN = 0;
        public static final int ENGLISHBEGINNER = 1;
        public static final int ENGLISHINTERMEDIATE = 2;
        public static final int ENGLISHADVANCED = 3;


        /**
         * Returns whether or not the given English Level is {@link #ENGLISH_UNKNOWN}, {@link #ENGLISHBEGINNER},
         * or {@link #ENGLISHINTERMEDIATE} or {@link #ENGLISHADVANCED}.
         */
        public static boolean isValidEnglish(int english) {
            if (english == ENGLISH_UNKNOWN || english == ENGLISHBEGINNER || english == ENGLISHINTERMEDIATE || english == ENGLISHADVANCED) {
                return true;
            }
            return false;
        }

        /**
         * Possible values for the gender of the user.
         */
        public static final int GENDER_UNKNOWN = 0;
        public static final int GENDER_MALE = 1;
        public static final int GENDER_FEMALE = 2;

        /**
         * Returns whether or not the given gender is {@link #GENDER_UNKNOWN}, {@link #GENDER_MALE},
         * or {@link #GENDER_FEMALE}.
         */
        public static boolean isValidGender(int gender) {
            if (gender == GENDER_UNKNOWN || gender == GENDER_MALE || gender == GENDER_FEMALE) {
                return true;
            }
            return false;
        }
    }

}



