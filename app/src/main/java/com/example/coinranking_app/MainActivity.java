package com.example.coinranking_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.coinranking_app.models.Coin;
import com.example.coinranking_app.models.CoinsList;
import com.example.coinranking_app.viewModels.IViewModel;
import com.example.coinranking_app.viewModels.RetrofitViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private IViewModel viewModel;
    private TextView text_view_data;

    private ScrollView scrollview_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(RetrofitViewModel.class);


        text_view_data = findViewById(R.id.textview_data);
        scrollview_text = findViewById(R.id.scrollview_text);

        viewModel.getData().observe(this, new Observer<CoinsList>() {
            @Override
            public void onChanged(CoinsList coinsList) {
                if (coinsList != null) {
                    List<Coin> coins = coinsList.getCoins();

                    StringBuilder stringBuilder = new StringBuilder();

                    for (Coin coin : coins) {
                        String coinName = coin.getName();
                        double coinPrice = coin.getPrice();

                        stringBuilder.append("Name: ").append(coinName).append("\n");
                        stringBuilder.append("Coin Price: ").append(coinPrice).append("\n");
                        stringBuilder.append("---------------------").append("\n");
                    }

                    text_view_data.setText(stringBuilder.toString());

                    scrollview_text.post(new Runnable() {
                        @Override
                        public void run() {
                            scrollview_text.fullScroll(ScrollView.FOCUS_UP);
                        }
                    });
                }
            }
        });

        viewModel.generateListCoins();

    }
}