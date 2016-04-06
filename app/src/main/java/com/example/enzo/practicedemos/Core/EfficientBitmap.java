package com.example.enzo.practicedemos.Core;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

/**
 * Created by enzoz on 2016/4/6.
 */
public class EfficientBitmap {

    /**
     * @param options   target bitmap's BitmapFactory.Options
     * @param reqWidth  the required width
     * @param reqHeight the required height
     * @return inSampleSize calculated.
     */
    public static int calcInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidht = width / 2;

            while ((halfHeight / inSampleSize) > reqHeight || (halfWidht / inSampleSize) > reqHeight) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    /**
     * Decode sampled Bitmap from resource
     *
     * @param res       resource
     * @param resId     resource id
     * @param reqWidth  required width
     * @param reqHeight required height
     * @return Bitmap decoded
     */
    public static Bitmap decodeBitmap(Resources res, int resId, int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        // check dimensions without loading the resource
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // calculate inSampleSize
        options.inSampleSize = calcInSampleSize(options, reqWidth, reqHeight);

        // decode bitmap with the inSampleSize calculated
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeResource(res, resId, options);
    }

    // Helper class that treats the bitmap processing off the UI thread
    public static class DecoderAsyncTask extends AsyncTask<Integer, Void, Bitmap> {
        private final WeakReference<ImageView> wkrefImageView;
        private final Resources res;
        private int resId = 0;

        /**
         * execute(params...) method returns the Bitmap loaded.<br/>
         * params...<br/>
         * params[0] = resource id. <br/>
         * params[1] = required width. <br/>
         * params[2] = required height. <br/>
         */
        public DecoderAsyncTask(Resources resources, ImageView imageView) {
            wkrefImageView = new WeakReference<ImageView>(imageView);
            this.res = resources;
        }


        @Override
        protected Bitmap doInBackground(Integer... params) {
            resId = params[0];
            int reqWidth = params[1];
            int reqHeight = params[2];
            return decodeBitmap(res, resId, reqWidth, reqHeight);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            // isCancelled() has just invoked before onPostExecute() in source code.
            // this method won't be invoked if the task was cancelled.

//            if (isCancelled()){
//                bitmap = null;
//            }

            // the condition WeakReference !=null is always true
            if (bitmap != null) {
                final ImageView imageView = wkrefImageView.get();
                if (imageView != null) {
                    final DecoderAsyncTask decoderAsyncTask = getDecoderAsyncTask(imageView);
                    // check if is the same target
                    if (this == decoderAsyncTask)
                        imageView.setImageBitmap(bitmap);
                }
            }
        }

    }

    // Wrapper Drawable class that helps dealing with the concurrency.
    public static class AsyncDrawable extends BitmapDrawable {
        private final WeakReference<DecoderAsyncTask> wkrefDecoderAsyncTask;

        public AsyncDrawable(Resources resources, Bitmap bitmap, DecoderAsyncTask decoderAsyncTask) {
            super(resources, bitmap);
            wkrefDecoderAsyncTask = new WeakReference<DecoderAsyncTask>(decoderAsyncTask);
        }

        public DecoderAsyncTask getDecoderAsyncTask() {
            return wkrefDecoderAsyncTask.get();
        }
    }

    public static void bindAsyncDrawableToImageView(BitmapDrawable drawable, ImageView imageView) {
        imageView.setImageDrawable(drawable);
    }

    public static boolean cancelPotentialTask(int resId, ImageView imageView) {
        // get the current task
        final DecoderAsyncTask decoderAsyncTask = getDecoderAsyncTask(imageView);

        if (decoderAsyncTask != null) {
            final int bitmapResId = decoderAsyncTask.resId;
            if (bitmapResId == 0 || bitmapResId != resId) {
                // cancel the current task
                decoderAsyncTask.cancel(true);
            } else {
                // the same task is already in progress
                return false;
            }
        }
        // no task associate with the ImageView
        return true;
    }

    // get current task of the ImageView
    public static DecoderAsyncTask getDecoderAsyncTask(ImageView imageView) {
        if (imageView != null) {
            final Drawable drawable = imageView.getDrawable();
            if (drawable instanceof AsyncDrawable) {
                final AsyncDrawable asyncDrawable = (AsyncDrawable) drawable;
                return asyncDrawable.getDecoderAsyncTask();
            }
        }
        return null;
    }

    public static void loadBitmap(Resources res, ImageView imageView, int resId, int reqWidth, int reqHeight) {
        if (cancelPotentialTask(resId, imageView)) {
            final DecoderAsyncTask task = new DecoderAsyncTask(res, imageView);
            final AsyncDrawable asyncDrawable = new AsyncDrawable(res, null, task);
            bindAsyncDrawableToImageView(asyncDrawable, imageView);
            task.execute(resId, reqWidth, reqHeight);
        }
    }
}
