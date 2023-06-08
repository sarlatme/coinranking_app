package com.example.coinranking_app.models;

public class CoinsListResponse {

    private String status;
    private CoinsListData data;

    public String getStatus() { return this.status; }
    public void setStatus( String status ){ this.status = status; }

    public CoinsListData getData() { return this.data; }
    public void setData( CoinsListData data ){ this.data = data; }
}
