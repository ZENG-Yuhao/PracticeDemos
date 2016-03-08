package com.example.enzo.practicedemos.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.enzo.practicedemos.Core.CustomBaseAdapter;
import com.example.enzo.practicedemos.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BaseAdapterActivity extends AppCompatActivity
{

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_adapter);

        CustomBaseAdapter adapter = new CustomBaseAdapter(this, getData(), R.layout
                .item_listview_base_adapter, new String[]{"title", "content", "points"}, new int[]{R.id.title, R.id
                .content, R.id.points});

        listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }

    public ArrayList<Map<String, Object>> getData()
    {
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < 100; i++)
        {
            Map<String, Object> listItem = new HashMap<>();
            listItem.put("title", "user " + i);
            listItem.put("content", "this is content " + i);
            listItem.put("points", "points" + i);
            list.add(listItem);
        }
        return list;
    }

}
