package com.example.enzo.practicedemos.DB.UserDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by enzoz on 2016/3/11.
 */
public class UserDbHelper extends SQLiteOpenHelper
{
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + UserContract.Entry.TABLE_NAME + " (" +
                    UserContract.Entry._ID + " INTEGER PRIMARY KEY," +
                    UserContract.Entry.COLUMN_NAME_USER_NAME + TEXT_TYPE + COMMA_SEP +
                    UserContract.Entry.COLUMN_NAME_ADDRESS + TEXT_TYPE + COMMA_SEP +
                    UserContract.Entry.COLUMN_NAME_EMAIL + TEXT_TYPE + COMMA_SEP +
                    UserContract.Entry.COLUMN_NAME_ACCOUNT + TEXT_TYPE + COMMA_SEP +
                    UserContract.Entry.COLUMN_NAME_PASSWORD + TEXT_TYPE + COMMA_SEP +
                    " )";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + UserContract.Entry.TABLE_NAME;

    public UserDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
