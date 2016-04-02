package com.example.enzo.practicedemos.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.enzo.practicedemos.R;
import com.example.enzo.practicedemos.Services.CreateService;
import com.example.enzo.practicedemos.Views.PullToRefreshListView;

public class Main2Activity extends AppCompatActivity {
    private static final String[] titles = {
            "Service and Backend Notification",
            "PullToRefresh ListView",
            "Digital Indicator",
            "SQLite",
            "ContentProvider",
            "SharedPreferences",
            "Drag and Drop",
            "BaseAdapter",
            "Animation",
            "SwipeView",
            "OverScroller",
            "ToolBar",
            "DrawerLayout",
            "Header & Footer",
            "Binding",
            "DrawerPager",
            "OverridePendingTransition"
    };

    private static final Class[] activities = {
            CreateServiceActivity.class,
            PullToRefreshListviewActivity.class,
            DigitalIndicatorActivity.class,
            SQLiteActivity.class,
            ContentProviderActivity.class,
            SharedPreferencesActivity.class,
            DragAndDropActivity.class,
            BaseAdapterActivity.class,
            PropertyAnimationActivity.class,
            SwipeViewActivity.class,
            OverScrollerActivity.class,
            ToolBarActivity.class,
            DrawerLayoutActivity.class,
            HeaderFooterActivity.class,
            DataBindingActivity.class,
            DrawerPagerActivity.class,
            TransitionActivity1.class
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
}
