package com.example.coinranking_app.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.coinranking_app.CoinRankingApp;
import com.example.coinranking_app.models.Coin;

public class PreferencesHelper {

    private static PreferencesHelper INSTANCE;

    private static final String SHARED_PREFERENCES_NAME = "coinRankingPreferences";
    private static final String API_KEY = "apiKey";
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

    public String getCoin(Coin coin) {
        return preferences.getString("COIN", null);
    }

    public void setCoin(Coin coin) {
        preferences.edit().putString("COIN", coin.getName()).apply();
    }

    public String getApiKey() {
        return preferences.getString(API_KEY, null);
    }

    public void setApiKey(String apiKey) {
        preferences.edit().putString(API_KEY, apiKey).apply();
    }
}
