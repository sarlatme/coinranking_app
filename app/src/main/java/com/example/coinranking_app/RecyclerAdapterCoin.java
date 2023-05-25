package com.example.coinranking_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.coinranking_app.models.Coin;

import java.util.List;

public class RecyclerAdapterCoin extends RecyclerView.Adapter<RecyclerAdapterCoin.MyViewHolder>{

    private List<Coin> coin_list;

    public RecyclerAdapterCoin(List<Coin> coinList) {
        this.coin_list = coinList;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coin, parent, false);
        return new MyViewHolder(view);
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        Coin coin = coin_list.get(position);
        holder.bind(coin);
    }

    public int getItemCount(){
        return coin_list.size();
    }

    public void setCoinList(List<Coin> coins) {
        this.coin_list = coins;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textview_name;
        private TextView textview_price;

        public MyViewHolder(View itemView) {
            super(itemView);
            textview_name = itemView.findViewById(R.id.textview_name);
            textview_price = itemView.findViewById(R.id.textview_price);
        }

        public void bind(Coin coin) {
            textview_name.setText(coin.getName());
            textview_price.setText("Price : " + String.valueOf(coin.getPrice()));
        }
    }
}
