package com.example.signupproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    public DBHandler(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        // Create a table with email as primary key
        DB.execSQL("create Table Userdetails(email TEXT primary key, username TEXT, dob TEXT, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Userdetails");
    }

    // 1. Insert Data (Sign Up)
    public Boolean insertUserData(String email, String username, String dob, String password) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("username", username);
        contentValues.put("dob", dob);
        contentValues.put("password", password);
        long result = DB.insert("Userdetails", null, contentValues);
        return result != -1;
    }

    // 2. Check Username and Password (Sign In)
    // Note: Usually we login with Email, but per your request, I'm checking Username/Password
    public Boolean checkUsernamePassword(String username, String password) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails where username = ? and password = ?", new String[]{username, password});
        return cursor.getCount() > 0;
    }

    // 3. Delete Data (Delete Account)
    public Boolean deleteData(String email) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails where email = ?", new String[]{email});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Userdetails", "email=?", new String[]{email});
            return result != -1;
        }
        return false;
    }

    // 4. Update Password (Reset Password)
    public Boolean updatePassword(String email, String newPassword) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", newPassword);
        Cursor cursor = DB.rawQuery("Select * from Userdetails where email = ?", new String[]{email});
        if (cursor.getCount() > 0) {
            long result = DB.update("Userdetails", contentValues, "email=?", new String[]{email});
            return result != -1;
        }
        return false;
    }
}