package com.example.enzo.practicedemos.DB.UserDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;

/**
 * Created by enzoz on 2016/3/11.
 */
public class UserDbManager
{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "example.db";

    private SQLiteDatabase db;
    private UserDbHelper mHelper;

    public UserDbManager(Context context)
    {
        mHelper = new UserDbHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void open()
    {
        db = mHelper.getWritableDatabase();
    }

    public void close()
    {
        db.close();
    }

    public long addUser(User user)
    {
        if (user == null) return 0;

        ContentValues values = new ContentValues();
        values.put(UserContract.Entry.COLUMN_NAME_USER_NAME, user.getName());
        values.put(UserContract.Entry.COLUMN_NAME_ADDRESS, user.getAddress());
        values.put(UserContract.Entry.COLUMN_NAME_EMAIL, user.getEmail());
        values.put(UserContract.Entry.COLUMN_NAME_ACCOUNT, user.getAccount());
        values.put(UserContract.Entry.COLUMN_NAME_PASSWORD, user.getPassword());
        //The first argument for insert() is simply the table name. The second argument provides the name of a column
        // in which the framework can insert NULL in the event that the ContentValues is empty (if you instead set
        // this to "null", then the framework will not insert a row when there are no values).
        return db.insert(UserContract.Entry.TABLE_NAME, null, values);
    }
}
