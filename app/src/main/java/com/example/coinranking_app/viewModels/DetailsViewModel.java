package com.example.coinranking_app.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.coinranking_app.models.CoinData;
import com.example.coinranking_app.models.CoinResponse;
import com.example.coinranking_app.network.RetrofitNetworkManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsViewModel extends ViewModel implements IDetailsVewModel {

    private final MutableLiveData<CoinData> data = new MutableLiveData<>();
    private final MutableLiveData<String> error = new MutableLiveData<>();

    public LiveData<CoinData> getData() {
        return data;
    }

    public LiveData<String> getError() {
        return error;
    }

    public void getDetailsCoin( String uuid ){
        RetrofitNetworkManager.coinRankingAPI.getCoin(uuid).enqueue(new Callback<CoinResponse>() {
            @Override
            public void onResponse(Call<CoinResponse> call, Response<CoinResponse> response) {
                if(response.body() != null){
                    handleResponse(response.body());
                } else {
                    handleError(response.message());
                }
            }

            @Override
            public void onFailure(Call<CoinResponse> call, Throwable t) {
                handleError(t.getMessage());
            }
        });
    }

    private void handleResponse(CoinResponse response){
        data.postValue(response.getData());
    }

    private void handleError(String errorMessage){
        error.postValue(errorMessage);
    }
}
