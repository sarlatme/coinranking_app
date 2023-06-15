package com.example.coinranking_app.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.coinranking_app.CoinRankingApp;

public class PreferencesHelper {

    private static PreferencesHelper INSTANCE;

    private static final String SHARED_PREFERENCES_NAME = "coinRankingPreferences";
    private final SharedPreferences preferences;

    private PreferencesHelper() {
        preferences = CoinRankingApp.getContext().getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static PreferencesHelper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PreferencesHelper();
        }
        return INSTANCE;
    }

    public String getCoinFavName() {
        String name = preferences.getString("coin.name", null);
        return name;
    }

    public String getCoinFavPrice() {
        String price = preferences.getString("coin.price", null);
        return price;
    }

    public String getCoinFavUuid() {
        String uuid = preferences.getString("coin.uuid", null);
        return uuid;
    }

    public void setCoinFav(String uuid, String name, Double price) {
        preferences.edit().putString("coin.uuid", uuid).apply();
        preferences.edit().putString("coin.name", name).apply();
        preferences.edit().putString("coin.price", String.format("%.2f", price)).apply();
    }
}
