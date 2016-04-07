package com.example.enzo.practicedemos.Activities;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.enzo.practicedemos.Core.Image.EfficientBitmap;
import com.example.enzo.practicedemos.R;

import java.util.Random;

public class EfficientBitmapActivity extends AppCompatActivity {
    public static int[] IMG_SRC = {R.drawable.img0, R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable
            .img4, R.drawable.img5};

    private static Bitmap[] mPlaceHolderBitmaps;
    private static Bitmap mPlaceHolderBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_efficient_bitmap);

        mPlaceHolderBitmaps = new Bitmap[4];
        for (int i = 0; i < 4; i++)
            mPlaceHolderBitmaps[i] = EfficientBitmap.decodeBitmap(getResources(), R.drawable.ic_action_replay,
                    75, 75);

        mPlaceHolderBitmap = EfficientBitmap.decodeBitmap(getResources(), R.drawable.ic_action_replay,
                75, 75);

        ListView listView = (ListView) findViewById(R.id.list_view);
        MyAdapter adapter = new MyAdapter(getApplicationContext());
        listView.setAdapter(adapter);
    }

    private class MyAdapter extends BaseAdapter {

        final private static int TOTAL = 100;


        private Context xContext;

        public MyAdapter(Context context) {
            xContext = context;

        }

        @Override
        public int getCount() {
            return TOTAL;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = null;
            if (convertView == null)
                view = LayoutInflater.from(xContext).inflate(R.layout.item_list_efficientbitmap, null);
            else view = convertView;

            ImageView imageView0 = (ImageView) view.findViewById(R.id.img_0);
            ImageView imageView1 = (ImageView) view.findViewById(R.id.img_1);
            ImageView imageView2 = (ImageView) view.findViewById(R.id.img_2);
            ImageView imageView3 = (ImageView) view.findViewById(R.id.img_3);

            Random rd = new Random();

//            EfficientBitmap.loadBitmap(xContext.getResources(), imageView0, mPlaceHolderBitmaps[0], IMG_SRC[rd
// .nextInt(5)
//                    ], 75, 75);
//            EfficientBitmap.loadBitmap(xContext.getResources(), imageView1, mPlaceHolderBitmaps[1], IMG_SRC[rd
// .nextInt(5)
//                    ], 75, 75);
//            EfficientBitmap.loadBitmap(xContext.getResources(), imageView2, mPlaceHolderBitmaps[2], IMG_SRC[rd
// .nextInt(5)
//                    ], 75, 75);
//            EfficientBitmap.loadBitmap(xContext.getResources(), imageView3, mPlaceHolderBitmaps[3], IMG_SRC[rd
// .nextInt(5)
//                    ], 75, 75);

            EfficientBitmap.loadBitmap(xContext.getResources(), imageView0, mPlaceHolderBitmap, IMG_SRC[rd.nextInt(5)
                    ], 75, 75, EfficientBitmap.DecoderAsyncTask.MODE_MEMORY_CACHE);
            EfficientBitmap.loadBitmap(xContext.getResources(), imageView1, mPlaceHolderBitmap, IMG_SRC[rd.nextInt(5)
                    ], 75, 75, EfficientBitmap.DecoderAsyncTask.MODE_MEMORY_CACHE);
            EfficientBitmap.loadBitmap(xContext.getResources(), imageView2, mPlaceHolderBitmap, IMG_SRC[rd.nextInt(5)
                    ], 75, 75, EfficientBitmap.DecoderAsyncTask.MODE_MEMORY_CACHE);
            EfficientBitmap.loadBitmap(xContext.getResources(), imageView3, mPlaceHolderBitmap, IMG_SRC[rd.nextInt(5)
                    ], 75, 75, EfficientBitmap.DecoderAsyncTask.MODE_MEMORY_CACHE);


            return view;
        }
    }
}
