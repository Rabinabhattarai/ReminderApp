package com.example.hp.myapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.hp.myapplication.model.Reminder;
import com.example.hp.myapplication.model.User;
import com.example.hp.myapplication.model.routine;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = DatabaseHelper.class.getName();

    // Database Version

    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "UserManager.db";

    // User table name
    private static final String TABLE_USER = "user";
    private static final String TABLE_BMI = "bmi";
    private static final String TABLE_REMINDER = "remindertable";
    private static final String TABLE_ROUTINE = "routinetable";

    // User Table Columns names
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";
    private static final String COLUMN_USER_IDNO = "user_idno";
    private static final String COLUMN_USER_BMI = "user_bmi";
    private static final String COLUMN_REMINDER_ID= "rem_id";
    private static final String COLUMN_DATE = "remdate";
    private static final String COLUMN_TIME = "remtime";
    private static final String COLUMN_COMMENT = "remcomment";
    private static final String COLUMN_UIDFORREM= "uidforrem";
    private static final String COLUMN_ROUTINE_ID= "routineid";
    private static final String COLUMN_ROUTINENAME = "routinename";
    private static final String COLUMN_ROUTINEDATE = "routinedate";
    private static final String COLUMN_TASKTODO = "routinetask";
    private static final String COLUMN_UIDFORROUTINE= "uidforroutine";

    // create table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT,"
            + COLUMN_USER_PASSWORD + " TEXT" + ")";

    private String CREATE_BMI_TABLE = "CREATE TABLE "
            + TABLE_BMI + " ("
            + COLUMN_USER_BMI + " DOUBLE NOT NULL ,"
            + COLUMN_USER_IDNO + " INTEGER, FOREIGN KEY (" + COLUMN_USER_IDNO + ") REFERENCES " + TABLE_USER + " (" + COLUMN_USER_ID + "))";// drop table sql query

    private String CREATE_REMINDER_TABLE = "CREATE TABLE " + TABLE_REMINDER + "("
            + COLUMN_REMINDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_DATE + " TEXT,"
            + COLUMN_TIME + " TEXT,"
            + COLUMN_COMMENT + " TEXT,"
    +COLUMN_UIDFORREM + " INTEGER, FOREIGN KEY (" + COLUMN_UIDFORREM + ") REFERENCES " + TABLE_USER + "(" + COLUMN_USER_ID + "))";

    private String CREATE_ROUTINE_TABLE = "CREATE TABLE " + TABLE_ROUTINE + "("
            + COLUMN_ROUTINE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_ROUTINENAME + " TEXT,"
            + COLUMN_ROUTINEDATE + " TEXT,"
            + COLUMN_TASKTODO + " TEXT,"
            +COLUMN_UIDFORROUTINE + " INTEGER, FOREIGN KEY (" + COLUMN_UIDFORROUTINE + ") REFERENCES " + TABLE_USER + "(" + COLUMN_USER_ID + "))";

    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;
    private String DROP_BMI_TABLE = "DROP TABLE IF EXISTS " + TABLE_BMI;
    private String DROP_REMINDER_TABLE = "DROP TABLE IF EXISTS " + TABLE_REMINDER;
    private String DROP_ROUTINE_TABLE = "DROP TABLE IF EXISTS" + TABLE_ROUTINE;

    /**
     * Constructor
     *
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_BMI_TABLE);
        db.execSQL(CREATE_REMINDER_TABLE);
        db.execSQL(CREATE_ROUTINE_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE);
        db.execSQL(DROP_BMI_TABLE);
        db.execSQL(DROP_REMINDER_TABLE);
        db.execSQL(DROP_ROUTINE_TABLE);
        // Create tables again
        onCreate(db);

    }

    /**
     * This method is to create user record
     *
     * @param user
     */
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    /**
     * This method is to create user record
     */
    public void addreminder(String date, String time, String comment, String rememail) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectquery = "SELECT " + COLUMN_USER_ID + " FROM " + TABLE_USER + " WHERE " +
                COLUMN_USER_EMAIL + "='" + rememail + "'";
        Cursor cursor = db.rawQuery(selectquery, null);

        if (cursor.moveToFirst()) {
            int id = cursor.getInt(0);

            ContentValues values = new ContentValues();
            values.put(COLUMN_DATE, date);
            values.put(COLUMN_TIME, time);
            values.put(COLUMN_COMMENT, comment);
            values.put(COLUMN_UIDFORREM, id);

            // Inserting Row
            db.insert(TABLE_REMINDER, null, values);
            Log.d("ValidTest", "sucessfull");
        }
            db.close();
    }

    public ArrayList<Reminder> getreminderDetails(String email) {

        SQLiteDatabase db = this.getReadableDatabase();
        String selectquery = "SELECT " + COLUMN_USER_ID + " FROM " + TABLE_USER + " WHERE " +
                COLUMN_USER_EMAIL + "='" + email + "'";
        Cursor cursor = db.rawQuery(selectquery, null);

        if (cursor.moveToFirst()) {
            int id = cursor.getInt(0);
            String idval = String.valueOf(id);


            ArrayList<Reminder> reminders = new ArrayList<Reminder>();

            // array of columns to fetch
            String[] columns = {
                    COLUMN_REMINDER_ID, COLUMN_DATE, COLUMN_TIME, COLUMN_COMMENT
            };
            // selection criteria
            String selection = COLUMN_UIDFORREM + " = ?";

            // selection argument
            String[] selectionArgs = {idval};

            // query user table with condition
            /**
             * Here query function is used to fetch records from user table this function works like we use sql query.
             * SQL query equivalent to this query function is
             * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
             */
            Cursor cursors = db.query(TABLE_REMINDER, //Table to query
                    columns,                    //columns to return
                    selection,                  //columns for the WHERE clause
                    selectionArgs,              //The values for the WHERE clause
                    null,                       //group the rows
                    null,                      //filter by row groups
                    null);


            if (cursors.moveToFirst()) {
                do {
                    Reminder reminder = new Reminder();
                    reminder.setRemid(cursors.getString(cursors.getColumnIndex(COLUMN_REMINDER_ID)));
                    reminder.setDate(cursors.getString(cursors.getColumnIndex(COLUMN_DATE)));
                    reminder.setTme(cursors.getString(cursors.getColumnIndex(COLUMN_TIME)));
                    reminder.setComment(cursors.getString(cursors.getColumnIndex(COLUMN_COMMENT)));
                    reminders.add(reminder);
                } while (cursors.moveToNext());

            }

            cursors.close();
            db.close();
            // return user

            return reminders;
        }
        return  null;
    }

    public ArrayList<routine> getroutineDetails(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectquery = "SELECT " + COLUMN_USER_ID + " FROM " + TABLE_USER + " WHERE " +
                COLUMN_USER_EMAIL + "='" + email + "'";
        Cursor cursor = db.rawQuery(selectquery, null);

        if (cursor.moveToFirst()) {
            int id = cursor.getInt(0);
            String idval = String.valueOf(id);


            ArrayList<routine> routines = new ArrayList<routine>();

            // array of columns to fetch
            String[] columns = {
                    COLUMN_ROUTINE_ID, COLUMN_ROUTINENAME, COLUMN_ROUTINEDATE, COLUMN_TASKTODO
            };
            // selection criteria
            String selection = COLUMN_UIDFORROUTINE + " = ?";

            // selection argument
            String[] selectionArgs = {idval};

            // query user table with condition
            /**
             * Here query function is used to fetch records from user table this function works like we use sql query.
             * SQL query equivalent to this query function is
             * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
             */
            Cursor cursors = db.query(TABLE_ROUTINE, //Table to query
                    columns,                    //columns to return
                    selection,                  //columns for the WHERE clause
                    selectionArgs,              //The values for the WHERE clause
                    null,                       //group the rows
                    null,                      //filter by row groups
                    null);


            if (cursors.moveToFirst()) {
                do {
                    routine routine = new routine();
                    routine.setRoutineid(cursors.getString(cursors.getColumnIndex(COLUMN_ROUTINE_ID)));
                    routine.setRoutinename(cursors.getString(cursors.getColumnIndex(COLUMN_ROUTINENAME)));
                    routine.setRoutinedate(cursors.getString(cursors.getColumnIndex(COLUMN_ROUTINEDATE)));
                    routine.setRoutinetasktodo(cursors.getString(cursors.getColumnIndex(COLUMN_TASKTODO)));
                    routines.add(routine);
                } while (cursors.moveToNext());

            }

            cursors.close();
            db.close();
            // return user

            return routines;
        }
        return  null;
    }

    long getProfilesCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, TABLE_REMINDER);
        db.close();
        return count;
    }

    public void addroutine(String routinename, String routinedate, String task, String routineemail) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectquery = "SELECT " + COLUMN_USER_ID + " FROM " + TABLE_USER + " WHERE " +
                COLUMN_USER_EMAIL + "='" + routineemail + "'";
        Cursor cursor = db.rawQuery(selectquery, null);

        if (cursor.moveToFirst()) {
            int id = cursor.getInt(0);

            ContentValues values = new ContentValues();
            values.put(COLUMN_ROUTINENAME, routinename);
            values.put(COLUMN_ROUTINEDATE, routinedate);
            values.put(COLUMN_TASKTODO, task);
            values.put(COLUMN_UIDFORROUTINE, id);

            // Inserting Row
            db.insert(TABLE_ROUTINE, null, values);
            Log.d("ValidTest", "sucessfull");
        }
        db.close();
    }

    public void addbmi(double bmi, String mEmail) {
        Log.d(TAG, "addbmi: bmi-" + bmi + " Email: " + mEmail);
        SQLiteDatabase db = this.getReadableDatabase();
        String selectquery = "SELECT " + COLUMN_USER_ID + " FROM " + TABLE_USER + " WHERE " +
                COLUMN_USER_EMAIL + "='" + mEmail + "'";
        Cursor cursor = db.rawQuery(selectquery, null);

        if (cursor.moveToFirst()) {
            int id = cursor.getInt(0);
            Log.d(TAG, "User id: " + id);

            ContentValues values = new ContentValues();

            values.put(COLUMN_USER_BMI, bmi);
            values.put(COLUMN_USER_IDNO, id);

            db.insert(TABLE_BMI, null, values);
        }
        db.close();
    }

    public Integer fordiet(String mEmail) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectquery = "SELECT " + COLUMN_USER_ID + " FROM " + TABLE_USER + " WHERE " +
                COLUMN_USER_EMAIL + "='" + mEmail + "'";
        Cursor cursor = db.rawQuery(selectquery, null);

        if (cursor.moveToFirst()) {
            int id = cursor.getInt(0);

            String selectquerynew = "SELECT " + COLUMN_USER_BMI + " FROM " + TABLE_BMI + " WHERE " +
                    COLUMN_USER_IDNO + "='" + id + "'";
            Cursor cursornew = db.rawQuery(selectquerynew, null);

            if (cursornew.moveToFirst()) {
                int bmi = cursor.getInt(0);
                Integer mbmi = Integer.valueOf(bmi);
                return mbmi;

            }
        }

        return null;
    }

    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */
    public List<User> getAllUser() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_USER_EMAIL,
                COLUMN_USER_NAME,

        };
        // sorting orders
        String sortOrder =
                COLUMN_USER_NAME + " ASC";
        List<User> userList = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }

    /**
     * This method to update user record
     *
     * @param user
     */

    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());

        // updating row
        db.update(TABLE_USER, values, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    public void viewuname() {
        SQLiteDatabase db = this.getReadableDatabase();

    }

    /**
     * This method is to delete user record
     *
     * @param user
     */

    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_USER, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @return true/false
     */
    public Cursor checkUseragain(String email) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID, COLUMN_USER_NAME
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        return cursor;
    }







    public Cursor Useridfornotify(String email) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        return cursor;
    }
     public Cursor checkUserfornotify (String id ) {
         SQLiteDatabase db = this.getReadableDatabase();

//         // array of columns to fetch
       String[] columns = {
                COLUMN_DATE, COLUMN_TIME, COLUMN_COMMENT
         };

         // selection criteria
         String selection = COLUMN_UIDFORREM + " = ?";

         // selection argument
         String[] selectionArgs = {id};
//
//         // query user table with condition
//         /**
//          * Here query function is used to fetch records from user table this function works like we use sql query.
//          * SQL query equivalent to this query function is
//          * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
//          */
        Cursor cursors = db.query(TABLE_REMINDER, //Table to query
                 columns,                    //columns to return
                 selection,                  //columns for the WHERE clause
                 selectionArgs,              //The values for the WHERE clause
                 null,                       //group the rows
                 null,                      //filter by row groups
                 null);
//
                 return cursors;
         }

             // r

    public Boolean checkUser(String email) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID, COLUMN_USER_NAME
        };
        SQLiteDatabase dbreadable = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = dbreadable.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        dbreadable.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    public double fetchbmi(String id) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_BMI
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_USER_IDNO + " = ?";

        // selection argument
        String[] selectionArgs = {id};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_BMI, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);

        double bmi = -1;
        if (cursor.moveToFirst()) {
            bmi = cursor.getDouble(0);
        }

        db.close();

        return bmi;
    }


    /**
     * This method to check user exist or not
     *
     * @param email
     * @param password
     * @return true/false
     */
    public Boolean checkUsers(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
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
