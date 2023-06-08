package com.example.coinranking_app.models;

public class CoinResponse {

    private String status;
    private CoinData data;

    public CoinData getData() {
        return data;
    }

    public void setData(CoinData data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
