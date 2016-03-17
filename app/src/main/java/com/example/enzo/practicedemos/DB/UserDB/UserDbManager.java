package com.example.enzo.practicedemos.DB.UserDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.session.PlaybackState;
import android.text.Selection;

import java.lang.reflect.Array;
import java.util.ArrayList;

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
        Cursor cursor = getUserCursorByName(name);
        return getFirstUser(cursor);
    }

    public Cursor getUserCursorByName(String name)
    {
        String selection = UserContract.Entry.COLUMN_NAME_USER_NAME + " LIKE ?";
        String[] selectionArgs = {name};

        return getUserCursor(selection, selectionArgs);
    }

    public User getUserById(int id)
    {
        Cursor cursor = getUserCursorById(id);
        return getFirstUser(cursor);
    }

    public Cursor getUserCursorById(int id)
    {
        String selection = UserContract.Entry._ID + " LIKE ?";
        String[] selectionArgs = {String.valueOf(id)};

        return getUserCursor(selection, selectionArgs);
    }

    public Cursor getUserCursor(String selection, String[] selectionArgs)
    {
        String[] projection = {
                UserContract.Entry._ID,
                UserContract.Entry.COLUMN_NAME_USER_NAME,
                UserContract.Entry.COLUMN_NAME_EMAIL,
                UserContract.Entry.COLUMN_NAME_ADDRESS,
                UserContract.Entry.COLUMN_NAME_ACCOUNT,
                UserContract.Entry.COLUMN_NAME_PASSWORD
        };

        return db.query(UserContract.Entry.TABLE_NAME, projection, selection, selectionArgs, null, null, null);
    }

    public User getFirstUser(Cursor cursor)
    {
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

    public ArrayList<User> getUserList()
    {
        return convertCursorToList(getAllUser());
    }

    public ArrayList<User> convertCursorToList(Cursor cursor)
    {
        if (cursor == null) return null;
        ArrayList<User> list = new ArrayList<>(cursor.getCount());
        // Determine the column index by their names
        int num_col_id = cursor.getColumnIndex(UserContract.Entry._ID);
        int num_col_name = cursor.getColumnIndex(UserContract.Entry.COLUMN_NAME_USER_NAME);
        int num_col_email = cursor.getColumnIndex(UserContract.Entry.COLUMN_NAME_EMAIL);
        int num_col_address = cursor.getColumnIndex(UserContract.Entry.COLUMN_NAME_ADDRESS);
        int num_col_account = cursor.getColumnIndex(UserContract.Entry.COLUMN_NAME_ACCOUNT);
        int num_col_password = cursor.getColumnIndex(UserContract.Entry.COLUMN_NAME_PASSWORD);

        String user_id, user_name, email, address, account, password;
        while (cursor.moveToNext())
        {
            user_id = cursor.getString(num_col_id);
            user_name = cursor.getString(num_col_name);
            email = cursor.getString(num_col_email);
            address = cursor.getString(num_col_address);
            account = cursor.getString(num_col_account);
            password = cursor.getString(num_col_password);

            User user = new User(user_id, user_name, address, email, account, password);
            list.add(user);
        }
        return list;
    }

    public int deleteUserByName(String name)
    {
        String selection = UserContract.Entry.COLUMN_NAME_USER_NAME + " LIKE ?";
        String[] selectionArgs = {name};
        return deleteUser(selection, selectionArgs);
    }

    public int deleteUserById(int id)
    {
        String selection = UserContract.Entry._ID + " LIKE ?";
        String[] selectionArgs = {String.valueOf(id)};
        return deleteUser(selection, selectionArgs);
    }

    public int deleteUser(String selection, String[] selectionArgs)
    {
        return db.delete(UserContract.Entry.TABLE_NAME, selection, selectionArgs);
    }

    public int modifyPasswordByName(String name, String newPassword)
    {
        ContentValues values = new ContentValues();
        values.put(UserContract.Entry.COLUMN_NAME_PASSWORD, newPassword);
        String selection = UserContract.Entry.COLUMN_NAME_USER_NAME + " LIKE ?";
        String[] selectionArgs = {name};
        return modifyPassword(selection, selectionArgs, values);
    }

    public int modifyPasswordById(int id, String newPassword)
    {
        ContentValues values = new ContentValues();
        values.put(UserContract.Entry.COLUMN_NAME_PASSWORD, newPassword);
        String selection = UserContract.Entry._ID + " LIKE ?";
        String[] selectionArgs = {String.valueOf(id)};
        return modifyPassword(selection, selectionArgs, values);
    }

    public int modifyPassword(String selection, String[] selectionArgs, ContentValues values)
    {
        return db.update(UserContract.Entry.TABLE_NAME, values, selection, selectionArgs);
    }

    public Cursor query(String uri, String[] projection, String selection, String[] selectionArgs, String sortOrder)
    {
        return db.query(uri, projection, selection, selectionArgs, null, null, sortOrder);
    }
}
