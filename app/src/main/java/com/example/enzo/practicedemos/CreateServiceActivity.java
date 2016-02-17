package com.example.enzo.practicedemos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.enzo.practicedemos.Services.CreateService;

public class CreateServiceActivity extends AppCompatActivity {

    private Button btn_start_service;
    private Button btn_stop_service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_service);

        btn_start_service = (Button) findViewById(R.id.btn_start_service);
        btn_start_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateServiceActivity.this, CreateService.class);
                startService(intent);
            }
        });


        btn_stop_service = (Button) findViewById(R.id.btn_stop_service);
        btn_stop_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateServiceActivity.this, CreateService.class);
                stopService(intent);
            }
        });

    }
}
