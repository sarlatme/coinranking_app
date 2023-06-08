package com.example.coinranking_app.network;

import com.example.coinranking_app.models.Coin;
import com.example.coinranking_app.models.CoinResponse;
import com.example.coinranking_app.models.CoinsListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface CoinRankingAPI {
    @GET("/coins")
    Call<CoinsListResponse> getCoinsList();
    @GET("/coin/{uuid}")
    Call<CoinResponse> getCoin(@Path("uuid") String uuid );
}
