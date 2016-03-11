package com.example.enzo.practicedemos.DB.UserDB;

import android.provider.BaseColumns;

import com.example.enzo.practicedemos.DB.Contract;

/**
 * Created by enzoz on 2016/3/11.
 */
public final class UserContract
{
    public UserContract()
    {
        // To prevent someone from accidentally instantiating the contract class,
        // give it an empty constructor.
    }

    public static abstract class Entry implements BaseColumns
    {
        public static final String TABLE_NAME = "User";
        //public static final String COLUMN_NAME_USER_ID = "user_id";
        public static final String COLUMN_NAME_USER_NAME = "user_name";
        public static final String COLUMN_NAME_ADDRESS = "address";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_ACCOUNT = "account";
        public static final String COLUMN_NAME_PASSWORD = "password";

    }
}
