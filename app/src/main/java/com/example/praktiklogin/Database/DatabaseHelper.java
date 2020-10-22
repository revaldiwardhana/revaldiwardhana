package com.example.praktiklogin.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.praktiklogin.Model.Modeluser;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "tes1";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_USERS = "users";
    public static final String KEY_ID = "id";
    public static final String KEY_USER_NAME = "username";
    public static final String KEY_NOMOR = "nomor";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_ALAMAT = "alamat";

    public static final String SQL_TABLE_USERS = " CREATE TABLE " + TABLE_USERS
            + " ( "
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_USER_NAME + " TEXT, "
            + KEY_PASSWORD + " TEXT, "
            + KEY_ALAMAT + " TEXT,"
            + KEY_NOMOR + " TEXT"
            + " ) ";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_TABLE_USERS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_USERS);
    }

    public void addUser(Modeluser user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USER_NAME, user.getUsername());
        values.put(KEY_PASSWORD, user.getPassword());
        values.put(KEY_NOMOR, user.getNomor());
        values.put(KEY_ALAMAT, user.getAlamat());
        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    public List<Modeluser> getAllUser() {
        String[] columns = {
                KEY_ID,
                KEY_USER_NAME,
                KEY_PASSWORD,
                KEY_ALAMAT,
                KEY_NOMOR
        };

        String sortOrder = KEY_USER_NAME + " ASC";
        List<Modeluser> userList = new ArrayList<Modeluser>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USERS,
                columns,
                null,
                null,
                null,
                null,
                sortOrder);
        if (cursor.moveToFirst()) {
            do {
                Modeluser user = new Modeluser();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_ID))));
                user.setUsername(cursor.getString(cursor.getColumnIndex(KEY_USER_NAME)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(KEY_PASSWORD)));
                user.setAlamat(cursor.getString(cursor.getColumnIndex(KEY_ALAMAT)));
                user.setNomor(cursor.getString(cursor.getColumnIndex(KEY_NOMOR)));
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return userList;
    }

    public void updateUser(Modeluser user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USER_NAME, user.getUsername());
        values.put(KEY_PASSWORD, user.getPassword());
        // updating row
        db.update(TABLE_USERS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    public void deleteUser(Modeluser user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_USERS, KEY_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    public boolean checkUser(String username) {
        String[] columns = {KEY_ID};
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = KEY_USER_NAME + " = ?";
        String[] selectionArgs = {username};
        Cursor cursor = db.query(TABLE_USERS,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    public boolean checkUser(String username, String password) {
        String[] columns = {KEY_ID};
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = KEY_USER_NAME + " = ?" + " AND " + KEY_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(TABLE_USERS,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }
}
