package com.example.coinranking_app;

import static android.Manifest.permission.POST_NOTIFICATIONS;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.coinranking_app.databinding.ActivityMainBinding;
import com.example.coinranking_app.models.Coin;
import com.example.coinranking_app.storage.PreferencesHelper;
import com.example.coinranking_app.viewModels.IViewModel;
import com.example.coinranking_app.viewModels.MainViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    private IViewModel viewModel;
    private RecyclerAdapterCoin recyclerAdapterCoin;

    private Intent serviceIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        checkNotificationsPermission();

        printFav(binding);

        serviceIntent = new Intent(this, ForegroundService.class);

        recyclerAdapterCoin = new RecyclerAdapterCoin(new ArrayList<>());
        setRecyclerAdapterCoin(recyclerAdapterCoin);

        binding.recyclerviewCoins.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerviewCoins.setAdapter(recyclerAdapterCoin);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.generateListCoins();
        refreshAction();
    }

    private void checkNotificationsPermission() {
        if (ContextCompat.checkSelfPermission(this, POST_NOTIFICATIONS) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{POST_NOTIFICATIONS}, 1);
        }
    }

    private void refreshAction() {
        binding.sync.setOnClickListener((v) -> {
            viewModel.generateListCoins();
            Toast.makeText(MainActivity.this, "Sync completed", Toast.LENGTH_LONG).show();
        });
    }

    private void printFav(ActivityMainBinding binding) {
        binding.textviewFavName.setText(PreferencesHelper.getInstance().getCoinFavName());
        binding.textviewFavPrice.setText(PreferencesHelper.getInstance().getCoinFavPrice() + " $");
    }

    private void setRecyclerAdapterCoin(RecyclerAdapterCoin recyclerAdapterCoin) {
        recyclerAdapterCoin.setListener(new OnCoinClickListener() {
            @Override
            public void onCoinLongClick(Coin coin) {
                PreferencesHelper.getInstance().setCoinFav(coin.getUuid(), coin.getName(), coin.getPrice());
                binding.textviewFavName.setText(PreferencesHelper.getInstance().getCoinFavName());
                binding.textviewFavPrice.setText(PreferencesHelper.getInstance().getCoinFavPrice() + " $");
                Picasso.get().load(coin.getIconUrl().replace("svg", "png")).into(binding.imageviewFavicon);
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
            System.out.println("TEST" + coins.size());
            recyclerAdapterCoin.setCoinList(coins);
        });

        viewModel.getError().observe(this, error -> {
            if (error != null) {
                Toast.makeText(MainActivity.this, error, Toast.LENGTH_LONG).show();
            }
        });

        startService(serviceIntent);
    }
}