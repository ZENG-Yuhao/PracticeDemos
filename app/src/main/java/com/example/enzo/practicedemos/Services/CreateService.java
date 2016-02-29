package com.example.enzo.practicedemos.Services;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.enzo.practicedemos.R;

import java.lang.ref.WeakReference;

public class CreateService extends Service
{

    private StopHandler handler;
    private NotificationManager manager;
    private NotificationCompat.Builder builder;

    public CreateService()
    {
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        Log.i("Service", "----> onCreate");
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        builder = new NotificationCompat.Builder(getApplicationContext());
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("Status");
        builder.setContentText("Downlaoding...");
        manager.notify(1001, builder.build());
        handler = new StopHandler(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Log.i("Service", "----> onStartCommand");
        new Thread(new MyRunnable()).start();
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy()
    {
        Log.i("Service", "----> onDestroy");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    public class MyRunnable implements Runnable
    {
        @Override
        public void run()
        {
            //Sleep for 5 seconds
//            long startTime = System.currentTimeMillis();
//            long endTime = startTime + 5 * 1000;
//            while (System.currentTimeMillis() < endTime) {
//                synchronized (this) {
//                    try {
//                        wait(endTime - System.currentTimeMillis());
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
            int duration = 50;
            for (int i = 1; i <= duration; i++)
            {
                synchronized (this)
                {
                    try
                    {
                        int value = (int) (i / (float) duration * 100);
                        Message msg = Message.obtain();
                        msg.arg1 = value;
                        handler.sendMessage(msg);
                        wait(100);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            handler.sendEmptyMessage(1);

        }
    }

    public static class StopHandler extends Handler
    {
        private WeakReference<CreateService> refSender = null;

        public StopHandler(CreateService sender)
        {
            this.refSender = new WeakReference<>(sender);
        }

        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            CreateService service = refSender.get();
            if (service == null) return;
            service.builder.setProgress(100, msg.arg1, false);
            service.manager.notify(1001, service.builder.build());
            if (msg.what == 1)
            {
                service.stopSelf();
                Toast.makeText(service.getApplicationContext(), "Download finished!", Toast
                        .LENGTH_SHORT).show();
                Log.i("Service", "---->Task Finished.");
            }
        }


    }
}
