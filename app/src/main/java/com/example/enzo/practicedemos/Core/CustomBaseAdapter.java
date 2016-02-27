package com.example.enzo.practicedemos.Core;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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