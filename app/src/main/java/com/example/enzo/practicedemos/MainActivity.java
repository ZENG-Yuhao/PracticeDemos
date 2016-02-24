package com.example.enzo.practicedemos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.beardedhen.androidbootstrap.BootstrapButton;

public class MainActivity extends AppCompatActivity {
    private BootstrapButton btn_create_service;
    private BootstrapButton btn_drag_and_drop;
    private BootstrapButton btn_base_adapter;
    private BootstrapButton btn_animation;
    private BootstrapButton btn_swipe_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_create_service = (BootstrapButton) findViewById(R.id.btn_create_service);
        btn_create_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateServiceActivity.class);
                startActivity(intent);
            }
        });


        btn_drag_and_drop = (BootstrapButton) findViewById(R.id.btn_drag_and_drop);
        btn_drag_and_drop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DragAndDropActivity.class);
                startActivity(intent);
            }
        });

        btn_base_adapter = (BootstrapButton) findViewById(R.id.btn_base_adapter);
        btn_base_adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BaseAdapterActivity.class);
                startActivity(intent);
            }
        });

        btn_animation = (BootstrapButton) findViewById(R.id.btn_animation);
        btn_animation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PropertyAnimationActivity.class);
                startActivity(intent);
            }
        });

        btn_swipe_view = (BootstrapButton) findViewById(R.id.btn_swipe_view);
        btn_swipe_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SwipeViewActivity.class);
                startActivity(intent);
            }
        });
    }
}