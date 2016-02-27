package com.example.enzo.practicedemos.Activities;

import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.enzo.practicedemos.R;

public class DragAndDropActivity extends AppCompatActivity {

    private final String IMAGEVIEW_TAG = "{This is a Tag}";
    private ImageView imageView;
    private LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_and_drop);

        imageView = (ImageView) findViewById(R.id.img);
        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
                ClipData data = new ClipData((CharSequence) v.getTag(), new String[]{ClipDescription
                        .MIMETYPE_TEXT_PLAIN}, item);
                v.startDrag(data, new View.DragShadowBuilder(v), null, 0);
                return true;
            }
        });
        imageView.setTag(IMAGEVIEW_TAG);

        container = (LinearLayout) findViewById(R.id.container);
        container.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {

                TextView title = (TextView) findViewById(R.id.title);
                final int action = event.getAction();
                switch (action) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        if (event.getClipDescription().hasMimeType(ClipDescription
                                .MIMETYPE_TEXT_PLAIN)) {
                            Log.i("Drag", "ACTION_DRAG_STARTED");
                            return true;
                        }
                        return false;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        container.setBackgroundColor(Color.YELLOW);
                        Log.i("Drag", "ACTION_DRAG_ENTERED");
                        return true;
                    case DragEvent.ACTION_DRAG_LOCATION:
                        Log.i("Drag", "ACTION_DRAG_LOCATION");
                        return true;
                    case DragEvent.ACTION_DRAG_EXITED:
                        container.setBackgroundColor(Color.BLUE);
                        Log.i("Drag", "ACTION_DRAG_EXITED");
                        title.setText("");
                        return true;
                    case DragEvent.ACTION_DROP:
                        ClipData.Item item = event.getClipData().getItemAt(0);
                        String dragData = (String) item.getText();
                        title.setText(dragData + " - Location: " +event.getY() + "," + event.getX
                                ());
                        Log.i("Drag", "ACTION_DROP");
                        return true;
                    case DragEvent.ACTION_DRAG_ENDED:
                        Log.i("Drag", "ACTION_DRAG_ENDED");
                        return true;
                    default:
                        break;

                }
                return false;
            }
        });

    }
}
