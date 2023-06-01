package com.example.coinranking_app.network;

import com.example.coinranking_app.models.CoinsListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface CoinRankingAPI {
    @Headers({
            "x-rapidapi-host: coinranking1.p.rapidapi.com",
            "x-rapidapi-key: 0ff3130a89msh8ce0c357b2f69f9p11f4bbjsnf6a87a57962a"
    })
    @GET("/coins")
    Call<CoinsListResponse> getCoinsList();

    @Headers({
            "x-rapidapi-host: coinranking1.p.rapidapi.com",
            "x-rapidapi-key: 0ff3130a89msh8ce0c357b2f69f9p11f4bbjsnf6a87a57962a"
    })
    @GET("/coin/uuid")
    Call<CoinsListResponse> getCoin( String uuid );
}
