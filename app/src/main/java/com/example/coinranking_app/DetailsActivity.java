package com.example.coinranking_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.coinranking_app.databinding.ActivityDetailsBinding;
import com.example.coinranking_app.models.Coin;
import com.example.coinranking_app.models.CoinData;
import com.example.coinranking_app.storage.AppDatabase;
import com.example.coinranking_app.storage.CoinDao;
import com.example.coinranking_app.storage.DataRepository;
import com.example.coinranking_app.viewModels.DetailsViewModel;
import com.example.coinranking_app.viewModels.IDetailsVewModel;

public class DetailsActivity extends AppCompatActivity {

    private ActivityDetailsBinding binding;
    private IDetailsVewModel viewModel;

    private Button button;

    private String uuid;
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

        button = findViewById(R.id.button_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}