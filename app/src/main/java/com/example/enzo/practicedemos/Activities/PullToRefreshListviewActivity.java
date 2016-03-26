package com.example.enzo.practicedemos.Activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.enzo.practicedemos.Core.Toolkit.ListItemInflator;
import com.example.enzo.practicedemos.R;
import com.example.enzo.practicedemos.Views.PullToRefreshListView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PullToRefreshListviewActivity extends AppCompatActivity {

    PullToRefreshListView listview;
    Handler xHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_refresh_listview);

        listview = (PullToRefreshListView) findViewById(R.id.listview_pulltorefresh);
        ListItemInflator.inflate(this, listview);
        xHandler = new Handler();
        listview.setOnRefreshListener(new PullToRefreshListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                xHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(PullToRefreshListviewActivity.this, "Refresh finished.", Toast.LENGTH_LONG)
                                .show();
                        listview.setUpdateTime(getTime());
                        listview.stopRefresh();
                    }
                }, 2000);

            }
        });
    }

    private String getTime() {
        return new SimpleDateFormat("MM-dd HH:mm", Locale.FRANCE).format(new Date());
    }
}
