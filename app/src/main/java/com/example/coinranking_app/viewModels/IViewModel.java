package com.example.coinranking_app.viewModels;

import androidx.lifecycle.LiveData;

import com.example.coinranking_app.models.CoinsListData;

public interface IViewModel {

    LiveData<CoinsListData> getData();
    void generateListCoins();

}
