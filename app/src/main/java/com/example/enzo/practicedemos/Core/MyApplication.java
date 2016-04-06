package com.example.enzo.practicedemos.Core;

import android.app.Application;
import android.content.res.Resources;

import com.beardedhen.androidbootstrap.TypefaceProvider;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        TypefaceProvider.registerDefaultIconSets();
    }

}
