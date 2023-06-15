package com.example.coinranking_app.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "coin")
public class Coin {
    public String getTestNewField() {
        return testNewField;
    }

    public void setTestNewField(String testNewField) {
        this.testNewField = testNewField;
    }

    private String testNewField;
    @NonNull
    @PrimaryKey()
    private String uuid;
    private String symbol;
    private String name;
    private String description;
    private String color;
    private String iconUrl;
    private String websiteUrl;
    private String marketCap;
    private Double price;
    private String btcPrice;
    private String change;
    int rank;
    int numberOfMarkets;
    int numberOfExchanges;
    String coinrankingUrl;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(String marketCap) {
        this.marketCap = marketCap;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getBtcPrice() {
        return btcPrice;
    }

    public void setBtcPrice(String btcPrice) {
        this.btcPrice = btcPrice;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getNumberOfMarkets() {
        return numberOfMarkets;
    }

    public void setNumberOfMarkets(int numberOfMarkets) {
        this.numberOfMarkets = numberOfMarkets;
    }

    public int getNumberOfExchanges() {
        return numberOfExchanges;
    }

    public void setNumberOfExchanges(int numberOfExchanges) {
        this.numberOfExchanges = numberOfExchanges;
    }

    public String getCoinrankingUrl() {
        return coinrankingUrl;
    }

    public void setCoinrankingUrl(String coinrankingUrl) {
        this.coinrankingUrl = coinrankingUrl;
    }
}
