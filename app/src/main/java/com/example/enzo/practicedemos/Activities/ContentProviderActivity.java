package com.example.enzo.practicedemos.Activities;

import android.content.UriMatcher;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.enzo.practicedemos.DB.UserDB.UserContract;
import com.example.enzo.practicedemos.R;

import java.util.List;

public class ContentProviderActivity extends AppCompatActivity {
    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    // UriMatcher
    private static final String AUTHORITY = "com.example.enzo.practicedemos.DB.Providers.UserProvider";
    private static final int ALL_USER = 0;
    private static final int USER = 1;
    private static final int USER_NAME = 2;
    private static final int USER_ADDRESS = 3;
    private static final int USER_EMAIL = 4;
    private static final int USER_ACCOUNT = 5;
    private static final int USER_PASSWORD = 6;

    static {
        sURIMatcher.addURI(AUTHORITY, "user", ALL_USER);
        sURIMatcher.addURI(AUTHORITY, "user/#", USER);
        sURIMatcher.addURI(AUTHORITY, "user/#/name", USER_NAME);
        sURIMatcher.addURI(AUTHORITY, "user/#/address", USER_ADDRESS);
        sURIMatcher.addURI(AUTHORITY, "user/#/email", USER_EMAIL);
        sURIMatcher.addURI(AUTHORITY, "user/#/account", USER_ACCOUNT);
        sURIMatcher.addURI(AUTHORITY, "user/#/password", USER_PASSWORD);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);

        TextView txt_1, txt_2, txt_3;
        txt_1 = (TextView) findViewById(R.id.txt_1);
        txt_2 = (TextView) findViewById(R.id.txt_2);
        txt_3 = (TextView) findViewById(R.id.txt_3);

        String str1, str2, str3;
        str1 = UserContract.Entry.SCHEMA + UserContract.Entry.AUTHORITY + "/user/2/account";
        Uri uri = Uri.parse(str1);
        List pathSegments = uri.getPathSegments();
        int param_2nd = Integer.valueOf((String) pathSegments.get(1));
        str3 = (String) pathSegments.get(1);
        switch (sURIMatcher.match(uri)) {
            case ALL_USER:
                str2 = "ALL_USER";
                break;
            case USER:
                str2 = "USER";
                break;
            case USER_NAME:
                str2 = "USER_NAME";
                break;
            case USER_ADDRESS:
                str2 = "USER_ADDRESS";
                break;
            case USER_EMAIL:
                str2 = "USER_EMAIL";
                break;
            case USER_ACCOUNT:
                str2 = "USER_ACCOUNT";
                break;
            case USER_PASSWORD:
                str2 = "USER_PASSWORD";
                break;
            default:
                str2 = "NONE";
                // exception
        }

        txt_1.setText(str1);
        txt_2.setText(str2);
        txt_3.setText(str3);
    }
}
