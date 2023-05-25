package com.example.coinranking_app.viewModels;

import androidx.lifecycle.LiveData;

import com.example.coinranking_app.models.CoinsList;
import com.example.coinranking_app.models.CoinsListResponse;

public interface IViewModel {

    LiveData<CoinsList> getData();
    void generateListCoins();

}
