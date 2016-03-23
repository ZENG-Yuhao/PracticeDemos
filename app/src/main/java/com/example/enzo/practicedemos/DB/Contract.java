package com.example.enzo.practicedemos.DB;

import android.provider.BaseColumns;

/**
 * Created by enzoz on 2016/3/11.
 */
public abstract class Contract {
    public Contract() {
        // To prevent someone from accidentally instantiating the contract class,
        // give it an empty constructor.
    }

    public abstract static class Entry implements BaseColumns {

    }
}
