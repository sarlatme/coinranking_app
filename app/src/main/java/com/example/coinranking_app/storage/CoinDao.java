package com.example.coinranking_app.storage;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.coinranking_app.models.Coin;

import java.util.List;

@Dao
public interface CoinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Coin coin);

    @Query("SELECT * FROM coin")
    LiveData<List<Coin>> getAll();
}
