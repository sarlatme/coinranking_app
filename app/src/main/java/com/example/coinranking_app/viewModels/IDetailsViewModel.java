package com.example.coinranking_app.viewModels;

import androidx.lifecycle.LiveData;

import com.example.coinranking_app.models.CoinData;

public interface IDetailsViewModel {
    LiveData<CoinData> getData();
    LiveData<String> getError();

    void getDetailsCoin( String uuid );
}
