package com.example.coinranking_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.coinranking_app.viewModels.IViewModel;
import com.example.coinranking_app.viewModels.RetrofitViewModel;

public class MainActivity extends AppCompatActivity {

    private IViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(RetrofitViewModel.class);
        viewModel.generateListCoins();


    }
}