package com.man293.regisloginapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBName = "dbRegisterLogin";

    public DBHelper(@Nullable Context context) {
        super(context, DBName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users(username TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
    }

    public boolean insertData(String username, String pass) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues ctval = new ContentValues();
        ctval.put("username", username);
        ctval.put("password", pass);
        long result = myDB.insert("users", null, ctval);
        if (result == -1)
            return false;
        else return true;
    }

    public boolean checkUserExist(String username) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT * FROM users WHERE username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public boolean checkLogin(String username, String pass) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT * FROM users WHERE username = ? and password = ?", new String[]{username, pass});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }


}
