package com.example.coinranking_app.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.coinranking_app.models.Coin;
import com.example.coinranking_app.models.CoinResponse;
import com.example.coinranking_app.models.CoinsListData;
import com.example.coinranking_app.models.CoinsListResponse;
import com.example.coinranking_app.network.RetrofitNetworkManager;
import com.example.coinranking_app.storage.DataRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitViewModel extends AndroidViewModel implements IViewModel {

    private final MutableLiveData<Coin> dataCoin = new MutableLiveData<>();

    private final DataRepository dataRepository;
    private final LiveData<List<Coin>> data;

    public RetrofitViewModel(@NonNull Application application) {
        super(application);
        dataRepository = new DataRepository(application);
        data = dataRepository.getData();
    }
    public LiveData<List<Coin>> getDataCoins() {
        return data;
    }

    public LiveData<Coin> getDataCoin() { return dataCoin; }

    @Override
    public LiveData<List<Coin>> getData() {
        return this.data;
    }

    public void generateListCoins() {
        RetrofitNetworkManager.coinRankingAPI.getCoinsList().enqueue(new Callback<CoinsListResponse>() {
            @Override
            public void onResponse(Call<CoinsListResponse> call, Response<CoinsListResponse> response) {
                if (response.body() != null) { // TODO: si body null ?
                    handleCoinListResponse(response.body());
                } else {
                    handleCoinListError();
                }
            }

            @Override
            public void onFailure(Call<CoinsListResponse> call, Throwable t) {
                t.printStackTrace();
                handleCoinListError();

                // TODO: gestion des erreurs
            }
        });
    }

    private void handleCoinListResponse(CoinsListResponse response) {
        for(Coin coin : response.getData().getCoins()) {
            dataRepository.insertData(coin);
        }
    }

    private void handleCoinListError() {

    }

    public void generateCoin(String uuid) {
        RetrofitNetworkManager.coinRankingAPI.getCoin(uuid).enqueue(new Callback<CoinResponse>() {
            @Override
            public void onResponse(Call<CoinResponse> call, Response<CoinResponse> response) {
                if (response.body() != null) {
                    handleCoinResponse(response.body());
                }
            }

            @Override
            public void onFailure(Call<CoinResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void handleCoinResponse(CoinResponse response) {
        //dataCoin.postValue(response.getData());
    }
}