package com.example.enzo.practicedemos.Core.Toolkit;

import android.content.Context;
import android.widget.ListView;

import com.example.enzo.practicedemos.Core.CustomBaseAdapter;
import com.example.enzo.practicedemos.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Enzo(ZENG Yuhao) on 16/3/23.
 */
public class ListItemInflator {
    public static void inflate(Context context, ListView target) {
        CustomBaseAdapter adapter = new CustomBaseAdapter(context, getData(), R.layout
                .item_listview_base_adapter, new String[]{"title", "content", "points"}, new int[]{R.id.title, R.id
                .content, R.id.points});

        target.setAdapter(adapter);
    }

    public static ArrayList<Map<String, Object>> getData() {
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Map<String, Object> listItem = new HashMap<>();
            listItem.put("title", "user " + i);
            listItem.put("content", "this is content " + i);
            listItem.put("points", "points" + i);
            list.add(listItem);
        }
        return list;
    }

}
