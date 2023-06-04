package com.example.coinranking_app.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.coinranking_app.CoinRankingApp;
import com.example.coinranking_app.models.Coin;
import com.google.gson.Gson;

public class PreferencesHelper {

    private static PreferencesHelper INSTANCE;

    private static final String SHARED_PREFERENCES_NAME = "coinRankingPreferences";
    // TODO: plus utile
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

    // TODO: nope, les prefs ne sont pas faites pour sauvegarder des objets complexes
    public Coin getCoin() {
        Gson gson = new Gson();
        String json = preferences.getString("COIN", null);
        Coin coin = gson.fromJson(json, Coin.class);
        return coin;
    }

    public void setCoin(Coin coin) {
        Gson gson = new Gson();
        String json = gson.toJson(coin);
        preferences.edit().putString("COIN", json).apply();
    }

    // TODO: code mort
    public String getApiKey() {
        return preferences.getString(API_KEY, null);
    }

    public void setApiKey(String apiKey) {
        preferences.edit().putString(API_KEY, apiKey).apply();
    }
}
