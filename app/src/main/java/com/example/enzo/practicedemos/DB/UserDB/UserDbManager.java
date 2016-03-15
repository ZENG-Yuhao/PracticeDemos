package com.example.enzo.practicedemos.DB.UserDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by enzoz on 2016/3/11.
 */
public class UserDbManager
{
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "example.db";

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

    public User getUserByName(String name)
    {
        String[] projection = {
                UserContract.Entry._ID,
                UserContract.Entry.COLUMN_NAME_USER_NAME,
                UserContract.Entry.COLUMN_NAME_EMAIL,
                UserContract.Entry.COLUMN_NAME_ADDRESS,
                UserContract.Entry.COLUMN_NAME_ACCOUNT,
                UserContract.Entry.COLUMN_NAME_PASSWORD
        };

        String selection = UserContract.Entry.COLUMN_NAME_USER_NAME + " LIKE ?";
        String[] selectionArgs = {name};
        Cursor cursor = db.query(UserContract.Entry.TABLE_NAME, projection, selection, selectionArgs, null, null, null);
        if (cursor.getCount() <= 0)
            return null;

        cursor.moveToFirst();
        // only get the first one
        int num_col_id = cursor.getColumnIndex(UserContract.Entry._ID);
        int num_col_name = cursor.getColumnIndex(UserContract.Entry.COLUMN_NAME_USER_NAME);
        int num_col_email = cursor.getColumnIndex(UserContract.Entry.COLUMN_NAME_EMAIL);
        int num_col_address = cursor.getColumnIndex(UserContract.Entry.COLUMN_NAME_ADDRESS);
        int num_col_account = cursor.getColumnIndex(UserContract.Entry.COLUMN_NAME_ACCOUNT);
        int num_col_password = cursor.getColumnIndex(UserContract.Entry.COLUMN_NAME_PASSWORD);

        String user_id = cursor.getString(num_col_id);
        String user_name = cursor.getString(num_col_name);
        String email = cursor.getString(num_col_email);
        String address = cursor.getString(num_col_address);
        String account = cursor.getString(num_col_account);
        String password = cursor.getString(num_col_password);

        User user = new User(user_id, user_name, address, email, account, password);
        cursor.close();
        return user;
    }

    public Cursor getUserCursorByName(String name)
    {
        String[] projection = {
                UserContract.Entry._ID,
                UserContract.Entry.COLUMN_NAME_USER_NAME,
                UserContract.Entry.COLUMN_NAME_EMAIL,
                UserContract.Entry.COLUMN_NAME_ADDRESS,
                UserContract.Entry.COLUMN_NAME_ACCOUNT,
                UserContract.Entry.COLUMN_NAME_PASSWORD
        };

        String selection = UserContract.Entry.COLUMN_NAME_USER_NAME + " LIKE ?";
        String[] selectionArgs = {name};
        Cursor cursor = db.query(UserContract.Entry.TABLE_NAME, projection, selection, selectionArgs, null, null, null);
        return cursor;
    }

    public User getUserById(int id)
    {
        String[] projection = {
                UserContract.Entry._ID,
                UserContract.Entry.COLUMN_NAME_USER_NAME,
                UserContract.Entry.COLUMN_NAME_EMAIL,
                UserContract.Entry.COLUMN_NAME_ADDRESS,
                UserContract.Entry.COLUMN_NAME_ACCOUNT,
                UserContract.Entry.COLUMN_NAME_PASSWORD
        };

        String selection = UserContract.Entry._ID + " LIKE ?";
        String[] selectionArgs = {String.valueOf(id)};
        Cursor cursor = db.query(UserContract.Entry.TABLE_NAME, projection, selection, selectionArgs, null, null, null);
        if (cursor.getCount() <= 0)
            return null;

        cursor.moveToFirst();
        // only get the first one
        int num_col_id = cursor.getColumnIndex(UserContract.Entry._ID);
        int num_col_name = cursor.getColumnIndex(UserContract.Entry.COLUMN_NAME_USER_NAME);
        int num_col_email = cursor.getColumnIndex(UserContract.Entry.COLUMN_NAME_EMAIL);
        int num_col_address = cursor.getColumnIndex(UserContract.Entry.COLUMN_NAME_ADDRESS);
        int num_col_account = cursor.getColumnIndex(UserContract.Entry.COLUMN_NAME_ACCOUNT);
        int num_col_password = cursor.getColumnIndex(UserContract.Entry.COLUMN_NAME_PASSWORD);

        String user_id = cursor.getString(num_col_id);
        String user_name = cursor.getString(num_col_name);
        String email = cursor.getString(num_col_email);
        String address = cursor.getString(num_col_address);
        String account = cursor.getString(num_col_account);
        String password = cursor.getString(num_col_password);

        User user = new User(user_id, user_name, address, email, account, password);
        cursor.close();
        return user;
    }

    public Cursor getUserCursorById(int id)
    {
        String[] projection = {
                UserContract.Entry._ID,
                UserContract.Entry.COLUMN_NAME_USER_NAME,
                UserContract.Entry.COLUMN_NAME_EMAIL,
                UserContract.Entry.COLUMN_NAME_ADDRESS,
                UserContract.Entry.COLUMN_NAME_ACCOUNT,
                UserContract.Entry.COLUMN_NAME_PASSWORD
        };

        String selection = UserContract.Entry._ID + " LIKE ?";
        String [] selectionArgs = {String.valueOf(id)};
        Cursor cursor = db.query(UserContract.Entry.TABLE_NAME, projection, selection, selectionArgs, null, null, null);
        return cursor;
    }

    public Cursor getAllUser()
    {
        String[] projection = {
                UserContract.Entry._ID,
                UserContract.Entry.COLUMN_NAME_USER_NAME,
                UserContract.Entry.COLUMN_NAME_EMAIL,
                UserContract.Entry.COLUMN_NAME_ADDRESS,
                UserContract.Entry.COLUMN_NAME_ACCOUNT,
                UserContract.Entry.COLUMN_NAME_PASSWORD
        };
        Cursor cursor = db.query(UserContract.Entry.TABLE_NAME, projection, null, null, null, null, null);
        return cursor;
    }

    public int deleteUserByName(String name)
    {
        String selection = UserContract.Entry.COLUMN_NAME_USER_NAME + " LIKE ?";
        String[] selectionArgs = {name};
        return db.delete(UserContract.Entry.TABLE_NAME, selection, selectionArgs);
    }

    public int modifyPassword(String name, String newPassword)
    {
        ContentValues values = new ContentValues();
        values.put(UserContract.Entry.COLUMN_NAME_PASSWORD, newPassword);
        String selection = UserContract.Entry.COLUMN_NAME_USER_NAME + " LIKE ?";
        String[] selectionArgs = {name};

        return db.update(UserContract.Entry.TABLE_NAME, values, selection, selectionArgs);
    }

    public Cursor query(String uri, String[] projection, String selection, String[] selectionArgs, String sortOrder)
    {
        return db.query(uri, projection, selection, selectionArgs, null, null, sortOrder);
    }
}
