package com.example.enzo.practicedemos.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.enzo.practicedemos.Core.Toolkit.ListItemInflator;
import com.example.enzo.practicedemos.R;
import com.example.enzo.practicedemos.Views.PullToRefreshListView;

public class PullToRefreshListviewActivity extends AppCompatActivity {

    PullToRefreshListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_refresh_listview);

        listview = (PullToRefreshListView) findViewById(R.id.listview_pulltorefresh);
        ListItemInflator.inflate(this, listview);
    }
}
