package com.example.enzo.practicedemos;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BaseAdapterActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_adapter);

        CustomBaseAdapter adapter = new CustomBaseAdapter(this, getData(), R.layout
                .item_listview_base_adapter, new String[]{"title", "content", "points"}, new
                int[]{R.id.title, R.id.content, R.id.points});

        listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }

    public ArrayList<Map<String, Object>> getData() {
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Map<String, Object> listItem = new HashMap<>();
            listItem.put("title", "user "+i);
            listItem.put("content", "this is content " + i);
            listItem.put("points", "points"+i);
            list.add(listItem);
        }
        return list;
    }

    public class CustomBaseAdapter extends BaseAdapter {

        private Context context;
        private ArrayList<? extends Map<String, ?>> data;
        private int resource;
        private String[] from;
        private int[] to;

        public CustomBaseAdapter(Context context, ArrayList<? extends Map<String, ?>> data, int
                resource, String[] from, int[] to) {
            this.context = context;
            this.data = data;
            this.resource = resource;
            this.from = from;
            this.to = to;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = null;
            if (convertView == null) {
                view = LayoutInflater.from(context).inflate(resource, null);
            } else {
                view = convertView;
            }

            for (int i = 0; i < from.length; i++) {
                HashMap<String, Object> map = (HashMap<String, Object>) getItem(position);
                Object obj = map.get(from[i]);
                TextView textView = (TextView) view.findViewById(to[i]);
                textView.setText((CharSequence) obj);
            }
            return view;
        }
    }
}
