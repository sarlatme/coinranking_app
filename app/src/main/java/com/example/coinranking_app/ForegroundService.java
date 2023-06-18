package com.example.coinranking_app;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;

import androidx.core.app.NotificationCompat;
import androidx.lifecycle.Observer;

import com.example.coinranking_app.models.CoinData;
import com.example.coinranking_app.storage.PreferencesHelper;
import com.example.coinranking_app.viewModels.DetailsViewModel;
import com.example.coinranking_app.viewModels.IDetailsViewModel;

public class ForegroundService extends Service {

    public static boolean isRunning;

    private static final int NOTIFICATION_ID = 1001;
    private static final String CHANNEL_ID = "favorite coin";
    private static final CharSequence CHANNEL_NAME = "My favorite coin";

    private HandlerThread handlerThread;
    private Handler handler;

    private static final int DELAY = 600000;

    private IDetailsViewModel viewModel;

    public ForegroundService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        handlerThread = new HandlerThread("service_thread");
        handlerThread.start();
        Looper looper = handlerThread.getLooper();
        handler = new Handler(looper);

        viewModel = new DetailsViewModel(this.getApplication());
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
        // TODO : il faut remove les observers dans le onDestroy
        super.onDestroy();
    }

    private Notification createNotification(String text) {
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID).setSmallIcon(R.drawable.ic_launcher_foreground).setContentTitle("Favorite coin").setContentText(text).setPriority(NotificationCompat.PRIORITY_DEFAULT).build();
        return notification;
    }

    private void setupForeground() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance);
            channel.enableLights(true);
            channel.setLightColor(Color.RED);

            NotificationManager notificationManager = this.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
        Notification notification = createNotification("default");
        startForeground(NOTIFICATION_ID, notification);
    }

    private Runnable createRunnable() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                String uuid = PreferencesHelper.getInstance().getCoinFavUuid();
                viewModel.getDetailsCoin(uuid);
                handler.postDelayed(this, DELAY);
            }
        };
        return runnable;
    }

    private void startMonitoring() {
        Runnable runnable = createRunnable();
        handler.post(runnable);

        Observer<CoinData> observer = coinData -> {
            if (coinData != null) {
                NotificationManager notificationManager = this.getSystemService(NotificationManager.class);
                notificationManager.notify(NOTIFICATION_ID, createNotification(coinData.getCoin().getName() + " -> Price : " + coinData.getCoin().getPrice() + "$"));
            }
        };
        viewModel.getData().observeForever(observer);
    }


}
