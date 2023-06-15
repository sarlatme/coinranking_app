package com.example.mvvmexample;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;

import com.example.mvvmexample.models.PriceResponse;
import com.example.mvvmexample.network.RetrofitNetworkManager;
import com.example.mvvmexample.utils.DateUtils;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForegroundService extends Service {

    public static boolean isRunning;

    private static final int NOTIFICATION_ID = 1001;
    private static final String CHANNEL_ID = "some ID";

    private HandlerThread handlerThread;
    private Handler handler;

    public ForegroundService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        handlerThread = new HandlerThread("service_thread");
        handlerThread.start();
        Looper looper = handlerThread.getLooper();
        handler = new Handler(looper);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        isRunning = true;
        setupForeground();
        startMonitoring();
        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        isRunning = false;
        handlerThread.quit();
        super.onDestroy();
    }

    private void setupForeground() {
        // TODO: compléter
        startForeground(NOTIFICATION_ID, notification);
    }

    private void startMonitoring() {
        // TODO: compléter
        handler.post(runnable);
    }
}
