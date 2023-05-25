package com.example.coinranking_app.models;

public class CoinsListResponse {

    private String status;
    private CoinsList data;

    public String getStatus() { return this.status; }
    public void setStatus( String status ){ this.status = status; }

    public CoinsList getData() { return this.data; }
    public void setData( CoinsList data ){ this.data = data; }
}
