package com.example.coinranking_app.storage;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.coinranking_app.models.Coin;

import java.util.List;

public class DataRepository {

    private final CoinDao coinDao;
    private final LiveData<List<Coin>> data;

    public DataRepository(Context applicationContext) {
        AppDatabase database = AppDatabase.getDatabase(applicationContext);
        this.coinDao = database.coinDaoDao();
        this.data = coinDao.getAll();
    }

    public LiveData<List<Coin>> getData() {
        return data;
    }

    public void insertData(Coin coin) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            coinDao.insert(coin);
        });
    }
}

