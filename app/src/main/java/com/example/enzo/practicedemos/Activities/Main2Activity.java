package com.example.enzo.practicedemos.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.enzo.practicedemos.R;
import com.example.enzo.practicedemos.Services.CreateService;
import com.example.enzo.practicedemos.Views.DigitalIndicatorLayout;
import com.example.enzo.practicedemos.Views.PullToRefreshListView;
import com.example.enzo.practicedemos.Views.SpringBackListView;

public class Main2Activity extends AppCompatActivity {
    private static final String[] activity_titles = {
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

        SpringBackListView listView = (SpringBackListView) findViewById(R.id.list_view);
        listView.setAdapter(new MyBaseAdapter(this));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Main2Activity.this, activities[position]);
                startActivity(intent);
            }
        });
    }

    class MyBaseAdapter extends BaseAdapter {
        private Context xContext;

        public MyBaseAdapter(Context context) {
            xContext = context;
        }

        @Override
        public int getCount() {
            return activities.length;
        }

        @Override
        public Object getItem(int position) {
            return activities[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = null;
            if (convertView == null) {
                view = LayoutInflater.from(xContext).inflate(R.layout.item_list_main2, null);
            } else {
                view = convertView;
            }

            DigitalIndicatorLayout indicator = (DigitalIndicatorLayout) view.findViewById(R.id.digital_indicator);
            indicator.setNumber(position + 1);

            TextView demoName = (TextView) view.findViewById(R.id.txtvw_demo_name);
            demoName.setText(activity_titles[position]);

            TextView index = (TextView) view.findViewById(R.id.txtvw_index);
            String str = (position + 1) + "/" + getCount();
            index.setText(str);

            return view;
        }
    }
}
