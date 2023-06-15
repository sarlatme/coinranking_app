package com.example.coinranking_app.viewModels;

import androidx.lifecycle.LiveData;

import com.example.coinranking_app.models.Coin;

import java.util.List;

public interface IViewModel {

    LiveData<List<Coin>> getData();
    LiveData<String> getError();
    void generateListCoins();

}
