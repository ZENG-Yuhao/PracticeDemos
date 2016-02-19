package com.example.enzo.practicedemos;

import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DragAndDropActivity extends AppCompatActivity {

    private final String IMAGEVIEW_TAG = "ZENG Yuhao";
    private ImageView imageView;
    private LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_and_drop);
        final TextView title = (TextView) findViewById(R.id.title);

        imageView = (ImageView) findViewById(R.id.img);
        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((String) v.getTag());
                ClipData data = new ClipData(IMAGEVIEW_TAG, new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN}, item);
                v.startDrag(data, new View.DragShadowBuilder(v), null, 0);
                return true;
            }
        });

        container = (LinearLayout) findViewById(R.id.container);
        container.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {


                final int action = event.getAction();
                switch (action) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                            return true;
                        }
                        return false;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        container.setBackgroundColor(Color.YELLOW);
                        return true;
                    case DragEvent.ACTION_DRAG_LOCATION:
                        return true;
                    case DragEvent.ACTION_DRAG_EXITED:
                        container.setBackgroundColor(Color.BLUE);
                        title.setText("");
                        return true;
                    case DragEvent.ACTION_DROP:
                        ClipData.Item item= event.getClipData().getItemAt(0);
                        String dragData = item.getText().toString();
                        title.setText(dragData+event.getY()+":" +event.getX());
                        return true;
                    case DragEvent.ACTION_DRAG_ENDED:
                        return true;
                    default:
                        break;

                }
                return false;
            }
        });

    }
}
