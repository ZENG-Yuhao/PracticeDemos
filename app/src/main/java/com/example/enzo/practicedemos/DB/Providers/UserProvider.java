package com.example.enzo.practicedemos.DB.Providers;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.example.enzo.practicedemos.DB.UserDB.UserDbHelper;
import com.example.enzo.practicedemos.DB.UserDB.UserDbManager;

import java.util.List;
import java.util.Objects;

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
        sURIMatcher.addURI(AUTHORITY,"user/#/name", USER_NAME);
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
        List pathSegments = uri.getPathSegments();
        int param_2nd = (int) pathSegments.get(2);
        int id;
        switch (sURIMatcher.match(uri))
        {
            case ALL_USER:
                break;
            case USER:
                break;
            case USER_NAME:

                break;
            case USER_ADDRESS:
                break;
            case USER_EMAIL:
                break;
            case USER_ACCOUNT:
                break;
            case USER_PASSWORD:
                break;
            default:
                // exception
        }
        manager.close();
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri)
    {
        String type="";
        switch (sURIMatcher.match(uri)){
            case ALL_USER:
                break;
            case USER:
                break;
            case USER_NAME:
            case USER_ADDRESS:
            case USER_EMAIL:
            case USER_ACCOUNT:
            case USER_PASSWORD:
                type = "vnd.android.cursor.item/String";
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
