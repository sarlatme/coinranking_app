package com.example.coinranking_app.storage;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.ViewModelProvider;

import com.example.coinranking_app.CoinRankingApp;
import com.example.coinranking_app.DetailsActivity;
import com.example.coinranking_app.MainActivity;
import com.example.coinranking_app.models.Coin;
import com.example.coinranking_app.viewModels.DetailsViewModel;
import com.example.coinranking_app.viewModels.IDetailsVewModel;
import com.google.gson.Gson;

import java.util.List;

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

    public String getCoinFavPrice(){
        String price = preferences.getString("coin.price", null);
        return price;
    }

    public void setCoinFav(String name, Double price) {
        preferences.edit().putString("coin.name", name).apply();
        preferences.edit().putString("coin.price", price.toString()).apply();
    }
}
