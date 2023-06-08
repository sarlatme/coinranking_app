package com.example.coinranking_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.coinranking_app.databinding.ActivityMainBinding;
import com.example.coinranking_app.models.Coin;
import com.example.coinranking_app.models.CoinsListData;
import com.example.coinranking_app.storage.PreferencesHelper;
import com.example.coinranking_app.viewModels.IViewModel;
import com.example.coinranking_app.viewModels.RetrofitViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    private IViewModel viewModel;
    private RecyclerAdapterCoin recyclerAdapterCoin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        printFav(binding);
              
        NotificationHelper.createNotificationChannel(this);
        recyclerAdapterCoin = new RecyclerAdapterCoin(new ArrayList<>());
        setRecyclerAdapterCoin(recyclerAdapterCoin);

        binding.recyclerviewCoins.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerviewCoins.setAdapter(recyclerAdapterCoin);

        viewModel = new ViewModelProvider(this).get(RetrofitViewModel.class);
        viewModel.generateListCoins();
    }

    private void printFav(ActivityMainBinding binding){
        binding.textviewFavName.setText(PreferencesHelper.getInstance().getCoinFavName());
        binding.textviewFavPrice.setText(PreferencesHelper.getInstance().getCoinFavPrice());
    }

    private void setRecyclerAdapterCoin(RecyclerAdapterCoin recyclerAdapterCoin){
        recyclerAdapterCoin.setListener(new OnCoinClickListener() {
            @Override
            public void onCoinLongClick(Coin coin) {
                PreferencesHelper.getInstance().setCoinFav(coin.getName(), coin.getPrice());
                binding.textviewFavName.setText(PreferencesHelper.getInstance().getCoinFavName());
                binding.textviewFavPrice.setText(String.format("%.2f", PreferencesHelper.getInstance().getCoinFavPrice()));
                Picasso.get().load(coin.getIconUrl().replace("svg", "png")).into(binding.imageviewFavicon);
                NotificationHelper.showPersistentNotification(MainActivity.this, "Cryptomonnaie favorite", PreferencesHelper.getInstance().getCoinFavName());
            }

            @Override
            public void onCoinClick(Coin coin) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("COIN", coin.getUuid());
                MainActivity.this.startActivity(intent);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        viewModel.getData().observe(this, coins -> {
            recyclerAdapterCoin.setCoinList(coins);
        });
    }
}