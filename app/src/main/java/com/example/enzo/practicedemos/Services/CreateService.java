package com.example.enzo.practicedemos.Services;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class CreateService extends Service {

    private StopHandler handler;
    private NotificationManager manager;

    public CreateService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("Service", "----> onCreate");
        handler = new StopHandler(this);
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("Service", "----> onStartCommand");
        new Thread(new MyRunnable()).start();
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        Log.i("Service", "----> onDestroy");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public class MyRunnable implements Runnable {
        @Override
        public void run() {
            //Sleep for 5 seconds
            long startTime = System.currentTimeMillis();
            long endTime = startTime + 5 * 1000;
            while (System.currentTimeMillis() < endTime) {
                synchronized (this) {
                    try {
                        wait(endTime - System.currentTimeMillis());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            handler.sendEmptyMessage(1);

        }
    }

    public static class StopHandler extends Handler {
        private WeakReference<CreateService> refSender = null;

        public StopHandler(CreateService sender) {
            refSender = new WeakReference<>(sender);
        }

        @Override
        public void handleMessage(Message msg) {
            CreateService service = refSender.get();
            if (service == null) return;

            super.handleMessage(msg);
            if (msg.what == 1) {
                service.stopSelf();
                Toast.makeText(service.getApplicationContext(), "Download finished!", Toast.LENGTH_SHORT).show();
                Log.i("Service", "---->Task Finished.");
            }
        }
    }
}
