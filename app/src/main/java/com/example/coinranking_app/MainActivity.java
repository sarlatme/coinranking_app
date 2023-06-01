package com.example.coinranking_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.coinranking_app.models.Coin;
import com.example.coinranking_app.models.CoinsList;
import com.example.coinranking_app.viewModels.IViewModel;
import com.example.coinranking_app.viewModels.RetrofitViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private IViewModel viewModel;

    private RecyclerView recycler_view_coins;
    private RecyclerAdapterCoin recycler_adapter_coin;
    private RetrofitViewModel retrofit_view_model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler_view_coins = findViewById(R.id.recyclerview_coins);

        List<Coin> empty_list = new ArrayList<>();
        recycler_adapter_coin = new RecyclerAdapterCoin(empty_list);

        recycler_view_coins.setLayoutManager(new LinearLayoutManager(this));
        recycler_view_coins.setAdapter(recycler_adapter_coin);

        viewModel = new ViewModelProvider(this).get(RetrofitViewModel.class);

        viewModel.getData().observe(this, new Observer<CoinsList>() {
            @Override
            public void onChanged(CoinsList coinsList) {
                if (coinsList != null) {
                    recycler_adapter_coin.setCoinList(coinsList.getCoins());
                    recycler_adapter_coin.notifyItemRangeInserted(0,coinsList.getCoins().size());
                }
            }
        });

        viewModel.generateListCoins();

    }


}