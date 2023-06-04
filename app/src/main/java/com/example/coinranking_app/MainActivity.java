package com.example.coinranking_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
// TODO: imports inutiles
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coinranking_app.databinding.ActivityMainBinding;
import com.example.coinranking_app.models.Coin;
import com.example.coinranking_app.models.CoinsList;
import com.example.coinranking_app.storage.PreferencesHelper;
import com.example.coinranking_app.viewModels.IViewModel;
import com.example.coinranking_app.viewModels.RetrofitViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    private IViewModel viewModel;
    // TODO: jamais utilisé
    private RecyclerView recycler_view_coins;
    // TODO: nom des variables en camelCase
    private RecyclerAdapterCoin recycler_adapter_coin;
    // TODO: jamais utilisé
    private RetrofitViewModel retrofit_view_model;


    // TODO: méthode trop longue à découper
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.textviewFavName.setText(PreferencesHelper.getInstance().getCoin().getName());
        binding.textviewFavPrice.setText(Double.toString(PreferencesHelper.getInstance().getCoin().getPrice()));
        List<Coin> empty_list = new ArrayList<>();
        // TODO: on va plutôt mettre la liste vide dans le constructeur, la vue ne manipule pas de données (même vides)
        recycler_adapter_coin = new RecyclerAdapterCoin(empty_list);
        recycler_adapter_coin.setListener(new OnCoinClickListener() {
            @Override
            public void onCoinClick(Coin coin) {
                Toast.makeText(MainActivity.this, coin.getName(), Toast.LENGTH_SHORT).show();
                PreferencesHelper.getInstance().setCoin(coin);
                Log.d("SP",PreferencesHelper.getInstance().getCoin().getName());
                binding.textviewFavName.setText(coin.getName());
                binding.textviewFavPrice.setText(Double.toString(coin.getPrice()));
            }
        });

        binding.recyclerviewCoins.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerviewCoins.setAdapter(recycler_adapter_coin);

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