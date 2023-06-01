package com.example.coinranking_app.viewModels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.coinranking_app.models.CoinsList;
import com.example.coinranking_app.models.CoinsListResponse;
import com.example.coinranking_app.network.RetrofitNetworkManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitViewModel extends ViewModel implements IViewModel {

    private final MutableLiveData<CoinsList> data = new MutableLiveData<>();

    public LiveData<CoinsList> getData() {
        return data;
    }

    public void generateListCoins(){
        RetrofitNetworkManager.coinRankingAPI.getCoinsList().enqueue(new Callback<CoinsListResponse>() {
            @Override
            public void onResponse(Call<CoinsListResponse> call, Response<CoinsListResponse> response) {
                if(response.body() != null){
                    handleResponse(response.body());
                }
            }

            @Override
            public void onFailure(Call<CoinsListResponse> call, Throwable t) {
                // TO DO
            }
        });
    }

    private void handleResponse(CoinsListResponse response){
        data.postValue(response.getData());
    }
}
