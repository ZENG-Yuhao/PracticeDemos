package com.example.enzo.practicedemos.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.example.enzo.practicedemos.R;

public class MainActivity extends AppCompatActivity
{
    private BootstrapButton btn_create_service;
    private BootstrapButton btn_drag_and_drop;
    private BootstrapButton btn_base_adapter;
    private BootstrapButton btn_animation;
    private BootstrapButton btn_swipe_view;
    private BootstrapButton btn_OverScroller;
    private BootstrapButton btn_tool_bar;
    private BootstrapButton btn_drawer_layout;
    private BootstrapButton btn_header_footer;
    private BootstrapButton btn_data_binding;
    private BootstrapButton btn_drawer_pager;
    private BootstrapButton btn_content_provider;
    private BootstrapButton btn_shared_preferences;
    private BootstrapButton btn_sqlite;
    private BootstrapButton btn_activity_translate;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_create_service = (BootstrapButton) findViewById(R.id.btn_create_service);
        btn_create_service.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, CreateServiceActivity.class);
                startActivity(intent);
            }
        });


        btn_drag_and_drop = (BootstrapButton) findViewById(R.id.btn_drag_and_drop);
        btn_drag_and_drop.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, DragAndDropActivity.class);
                startActivity(intent);
            }
        });

        btn_base_adapter = (BootstrapButton) findViewById(R.id.btn_base_adapter);
        btn_base_adapter.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, BaseAdapterActivity.class);
                startActivity(intent);
            }
        });

        btn_animation = (BootstrapButton) findViewById(R.id.btn_animation);
        btn_animation.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, PropertyAnimationActivity.class);
                startActivity(intent);
            }
        });

        btn_swipe_view = (BootstrapButton) findViewById(R.id.btn_swipe_view);
        btn_swipe_view.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, SwipeViewActivity.class);
                startActivity(intent);
            }
        });

        btn_OverScroller = (BootstrapButton) findViewById(R.id.btn_OverScroller);
        btn_OverScroller.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, OverScrollerActivity.class);
                startActivity(intent);
            }
        });

        btn_tool_bar = (BootstrapButton) findViewById(R.id.btn_tool_bar);
        btn_tool_bar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, ToolBarActivity.class);
                startActivity(intent);
            }
        });

        btn_drawer_layout = (BootstrapButton) findViewById(R.id.btn_drawer_layout);
        btn_drawer_layout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, DrawerLayoutActivity.class);
                startActivity(intent);
            }
        });

        btn_header_footer = (BootstrapButton) findViewById(R.id.btn_header_footer);
        btn_header_footer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, HeaderFooterActivity.class);
                startActivity(intent);
            }
        });

        btn_data_binding = (BootstrapButton) findViewById(R.id.btn_data_binding);
        btn_data_binding.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, DataBindingActivity.class);
                startActivity(intent);
            }
        });

        btn_drawer_pager = (BootstrapButton) findViewById(R.id.btn_drawer_pager);
        btn_drawer_pager.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, DrawerPagerActivity.class);
                startActivity(intent);
            }
        });

        btn_content_provider = (BootstrapButton) findViewById(R.id.btn_content_provider);
        btn_content_provider.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, ContentProviderActivity.class);
                startActivity(intent);
            }
        });

        btn_shared_preferences = (BootstrapButton) findViewById(R.id.btn_shared_preferences);
        btn_shared_preferences.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, SharedPreferencesActivity.class);
                startActivity(intent);
            }
        });

        btn_sqlite = (BootstrapButton) findViewById(R.id.btn_sqlite);
        btn_sqlite.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, SQLiteActivity.class);
                startActivity(intent);
            }
        });

        btn_activity_translate = (BootstrapButton) findViewById(R.id.btn_activity_translate);
        btn_activity_translate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, TransitionActivity1.class);
                startActivity(intent);
            }
        });
    }
}