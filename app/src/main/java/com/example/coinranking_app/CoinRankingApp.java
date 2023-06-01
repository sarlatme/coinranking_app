package com.example.coinranking_app;

import android.app.Application;
import android.content.Context;

public class CoinRankingApp extends Application {
    private static Context APPLICATION_CONTEXT;

    public static Context getContext() {
        return APPLICATION_CONTEXT;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        APPLICATION_CONTEXT = this.getApplicationContext();
    }
}
