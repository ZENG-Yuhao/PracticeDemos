package com.example.enzo.practicedemos.Activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.enzo.practicedemos.Core.Toolkit.ListItemInflator;
import com.example.enzo.practicedemos.R;
import com.example.enzo.practicedemos.Views.PullToRefreshListView;

public class PullToRefreshListviewActivity extends AppCompatActivity {

    PullToRefreshListView listview;
    Handler xHandlerHeader, xHandlerFooter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.activity_translate_in_r2l, R.anim.activity_translate_out_r2l);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_refresh_listview);

        listview = (PullToRefreshListView) findViewById(R.id.listview_pulltorefresh);
        ListItemInflator.inflate(this, listview);
        xHandlerHeader = new Handler();
        xHandlerFooter = new Handler();
        listview.setOnRefreshListener(new PullToRefreshListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                xHandlerHeader.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(PullToRefreshListviewActivity.this, "Refreshing finished.", Toast.LENGTH_SHORT)
                                .show();
                        listview.setUpdateTime();
                        listview.stopRefreshing();
                    }
                }, 2000);

            }
        });

        listview.setOnLoadMoreListner(new PullToRefreshListView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                xHandlerFooter.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(PullToRefreshListviewActivity.this, "Loading finished.", Toast.LENGTH_SHORT)
                                .show();
                        listview.stopLoading();
                    }
                }, 2000);
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        listview.onFinished();
        overridePendingTransition(R.anim.activity_translate_in_l2r, R.anim.activity_translate_out_l2r);
    }
}
