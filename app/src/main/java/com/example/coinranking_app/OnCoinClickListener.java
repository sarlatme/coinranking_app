package com.example.coinranking_app;

import com.example.coinranking_app.models.Coin;

interface OnCoinClickListener {
    void onCoinLongClick(Coin coin);

    void onCoinClick(Coin coin);
}
