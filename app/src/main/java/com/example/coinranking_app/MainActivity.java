package com.example.coinranking_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.coinranking_app.databinding.ActivityMainBinding;
import com.example.coinranking_app.models.Coin;
import com.example.coinranking_app.models.CoinsListData;
import com.example.coinranking_app.storage.PreferencesHelper;
import com.example.coinranking_app.viewModels.IViewModel;
import com.example.coinranking_app.viewModels.RetrofitViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    private IViewModel viewModel;

    private RecyclerView recycler_view_coins;
    private RecyclerAdapterCoin recycler_adapter_coin;
    private RetrofitViewModel retrofit_view_model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //binding.textviewFavName.setText(PreferencesHelper.getInstance().getCoin().getName());
        //binding.textviewFavPrice.setText(Double.toString(PreferencesHelper.getInstance().getCoin().getPrice()));
        List<Coin> empty_list = new ArrayList<>();
        recycler_adapter_coin = new RecyclerAdapterCoin(empty_list);
        recycler_adapter_coin.setListener(new OnCoinClickListener() {
            @Override
            public void onCoinLongClick(Coin coin) {
                Toast.makeText(MainActivity.this, coin.getName(), Toast.LENGTH_SHORT).show();
                PreferencesHelper.getInstance().setCoin(coin);
                Log.d("SP",PreferencesHelper.getInstance().getCoin().getName());
                binding.textviewFavName.setText(coin.getName());
                binding.textviewFavPrice.setText(Double.toString(coin.getPrice()));
            }

            @Override
            public void onCoinClick(Coin coin) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("COIN", coin.getUuid());
                MainActivity.this.startActivity(intent);
            }
        });

        binding.recyclerviewCoins.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerviewCoins.setAdapter(recycler_adapter_coin);

        viewModel = new ViewModelProvider(this).get(RetrofitViewModel.class);

        viewModel.getData().observe(this, new Observer<CoinsListData>() {
            @Override
            public void onChanged(CoinsListData coinsList) {
                if (coinsList != null) {
                    recycler_adapter_coin.setCoinList(coinsList.getCoins());
                    recycler_adapter_coin.notifyItemRangeInserted(0,coinsList.getCoins().size());
                }
            }
        });

        viewModel.generateListCoins();

    }
}