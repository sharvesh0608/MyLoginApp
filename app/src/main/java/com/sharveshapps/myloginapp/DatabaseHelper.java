package com.sharveshapps.myloginapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "UserDatabase";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_USERS = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_MOBILE = "mobile";

    private static final String COLUMN_PASSWORD = "password";

    private static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS +
            "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NAME + " TEXT, " +
            COLUMN_EMAIL + " TEXT, " +
            COLUMN_MOBILE + " TEXT," +
            COLUMN_PASSWORD + " TEXT" +

            ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public long insertUser(String name, String email, String mobile, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_MOBILE, mobile);
        values.put(COLUMN_PASSWORD, password);
        return db.insert(TABLE_USERS, null, values);
    }

    public boolean verifyCredentials(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID};
        String selection = COLUMN_EMAIL + " = ? AND " + COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {email, password}; // Assuming you have a password column

        Cursor cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count > 0;

    }
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_USERS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)));
                user.setMobile(cursor.getString(cursor.getColumnIndex(COLUMN_MOBILE)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)));
                userList.add(user);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return userList;
    }

    public List<User> getUsersWithEvenPosition() {
        List<User> evenUserList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_NAME, COLUMN_EMAIL, COLUMN_MOBILE, COLUMN_PASSWORD};
        String selection = COLUMN_ID + " % 2 = 0"; // Fetch rows with even positions
        Cursor cursor = db.query(TABLE_USERS, columns, selection, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)));
                user.setMobile(cursor.getString(cursor.getColumnIndex(COLUMN_MOBILE)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)));
                evenUserList.add(user);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return evenUserList;
    }

    public List<User> getUsersWithOddPosition() {
        List<User> oddUserList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_NAME, COLUMN_EMAIL, COLUMN_MOBILE, COLUMN_PASSWORD};
        String selection = COLUMN_ID + " % 2 = 1"; // Fetch rows with odd positions
        Cursor cursor = db.query(TABLE_USERS, columns, selection, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)));
                user.setMobile(cursor.getString(cursor.getColumnIndex(COLUMN_MOBILE)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)));
                oddUserList.add(user);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return oddUserList;
    }

}