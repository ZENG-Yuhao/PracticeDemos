package com.example.enzo.practicedemos.DB.Providers;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.example.enzo.practicedemos.DB.UserDB.User;
import com.example.enzo.practicedemos.DB.UserDB.UserContract;
import com.example.enzo.practicedemos.DB.UserDB.UserDbManager;

import java.util.List;

/**
 * Created by enzoz on 2016/3/15.
 */
public class UserProvider extends ContentProvider
{
    UserDbManager manager;

    // UriMatcher
    private static final String AUTHORITY = "com.example.enzo.practicedemos.DB.Providers.UserProvider";
    private static final int ALL_USER = 0;
    private static final int USER = 1;
    private static final int USER_NAME = 2;
    private static final int USER_ADDRESS = 3;
    private static final int USER_EMAIL = 4;
    private static final int USER_ACCOUNT = 5;
    private static final int USER_PASSWORD = 6;


    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static
    {
        sURIMatcher.addURI(AUTHORITY, "user", ALL_USER);
        sURIMatcher.addURI(AUTHORITY, "user/#", USER);
        sURIMatcher.addURI(AUTHORITY, "user/#/name", USER_NAME);
        sURIMatcher.addURI(AUTHORITY, "user/#/address", USER_ADDRESS);
        sURIMatcher.addURI(AUTHORITY, "user/#/email", USER_EMAIL);
        sURIMatcher.addURI(AUTHORITY, "user/#/account", USER_ACCOUNT);
        sURIMatcher.addURI(AUTHORITY, "user/#/password", USER_PASSWORD);
    }

    @Override
    public boolean onCreate()
    {
        manager = new UserDbManager(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder)
    {
        manager.open();
        MatrixCursor cursor;
        User user;
        List pathSegments = uri.getPathSegments();
        int param_2nd = (int) pathSegments.get(2);
        int id;
        switch (sURIMatcher.match(uri))
        {
            case ALL_USER:
                cursor = (MatrixCursor) manager.getAllUser();
                break;
            case USER:
                cursor = (MatrixCursor) manager.getUserCursorById(param_2nd);
                break;
            case USER_NAME:
                user = manager.getUserById(param_2nd);
                cursor = new MatrixCursor(new String[]{UserContract.Entry.COLUMN_NAME_USER_NAME}, 1);
                cursor.addRow(new Object[]{user.getName()});
                break;
            case USER_ADDRESS:
                user = manager.getUserById(param_2nd);
                cursor = new MatrixCursor(new String[]{UserContract.Entry.COLUMN_NAME_ADDRESS}, 1);
                cursor.addRow(new Object[]{user.getAddress()});
                break;
            case USER_EMAIL:
                user = manager.getUserById(param_2nd);
                cursor = new MatrixCursor(new String[]{UserContract.Entry.COLUMN_NAME_EMAIL}, 1);
                cursor.addRow(new Object[]{user.getEmail()});
                break;
            case USER_ACCOUNT:
                user = manager.getUserById(param_2nd);
                cursor = new MatrixCursor(new String[]{UserContract.Entry.COLUMN_NAME_ACCOUNT}, 1);
                cursor.addRow(new Object[]{user.getAccount()});
                break;
            case USER_PASSWORD:
                user = manager.getUserById(param_2nd);
                cursor = new MatrixCursor(new String[]{UserContract.Entry.COLUMN_NAME_PASSWORD}, 1);
                cursor.addRow(new Object[]{user.getPassword()});
                break;
            default:
                cursor = new MatrixCursor(null);
                // exception
        }
        manager.close();
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri)
    {
        String type = "";
        switch (sURIMatcher.match(uri))
        {
            case ALL_USER:
                type = "vnd.android.cursor.dir/com.example.enzo.practicedemos.all_user";
                break;
            case USER:
                type = "vnd.android.cursor.item/com.example.enzo.practicedemos.user";
                break;
            case USER_NAME:
                type = "vnd.android.cursor.item/com.example.enzo.practicedemos.user_name";
                break;
            case USER_ADDRESS:
                type = "vnd.android.cursor.item/com.example.enzo.practicedemos.user_address";
                break;
            case USER_EMAIL:
                type = "vnd.android.cursor.item/com.example.enzo.practicedemos.user_email";
                break;
            case USER_ACCOUNT:
                type = "vnd.android.cursor.item/com.example.enzo.practicedemos.user_account";
                break;
            case USER_PASSWORD:
                type = "vnd.android.cursor.item/com.example.enzo.practicedemos.user_password";
                break;
            default:
                // exception
        }
        return type;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values)
    {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs)
    {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs)
    {
        return 0;
    }
}
