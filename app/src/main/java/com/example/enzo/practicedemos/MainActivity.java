package com.example.enzo.practicedemos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btn_create_service;
    private Button btn_drag_and_drop;
    private Button btn_base_adapter;
    private Button btn_animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_create_service = (Button) findViewById(R.id.btn_create_service);
        btn_create_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateServiceActivity.class);
                startActivity(intent);
            }
        });


        btn_drag_and_drop = (Button) findViewById(R.id.btn_drag_and_drop);
        btn_drag_and_drop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DragAndDropActivity.class);
                startActivity(intent);
            }
        });

        btn_base_adapter = (Button) findViewById(R.id.btn_base_adapter);
        btn_base_adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BaseAdapterActivity.class);
                startActivity(intent);
            }
        });

        btn_animation = (Button) findViewById(R.id.btn_animation);
        btn_animation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PropertyAnimationActivity.class);
                startActivity(intent);
            }
        });
    }
}