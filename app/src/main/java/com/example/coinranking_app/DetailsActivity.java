package com.example.coinranking_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.coinranking_app.databinding.ActivityDetailsBinding;
import com.example.coinranking_app.models.Coin;
import com.example.coinranking_app.models.CoinData;
import com.example.coinranking_app.viewModels.DetailsViewModel;
import com.example.coinranking_app.viewModels.IDetailsViewModel;

public class DetailsActivity extends AppCompatActivity {

    private ActivityDetailsBinding binding;
    private IDetailsViewModel viewModel;

    private Button button;

    // TODO : inutile de conserver cette valeur ici, elle est déjà envoyée au VM
    private String uuid;
    // TODO : pas utilisé et pas à sa place, c'est le VM qui garde les données
    private Coin coin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent intent = getIntent();
        uuid = intent.getStringExtra("COIN");

        viewModel = new ViewModelProvider(this).get(DetailsViewModel.class);
        viewModel.getDetailsCoin(uuid);

        viewModel.getData().observe(this, new Observer<CoinData>() {
            @Override
            public void onChanged(CoinData coin) {
                if (coin != null) {
                    binding.textViewName.setText("Name : " + coin.getCoin().getName());
                    binding.textViewPrice.setText("Price : " + coin.getCoin().getPrice());
                    binding.textViewRank.setText("Rank : " + coin.getCoin().getRank());
                    binding.textViewDescription.setText(coin.getCoin().getDescription());
                }
            }
        });

        viewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String error) {
                if (error != null) {
                    Toast.makeText(DetailsActivity.this, error, Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

        // TODO : pourquoi utiliser findViewById et pas binding.buttonBack ???
        button = findViewById(R.id.button_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}