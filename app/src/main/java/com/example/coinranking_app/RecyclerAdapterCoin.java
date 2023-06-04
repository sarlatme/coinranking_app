package com.example.coinranking_app;
// TODO: imports inutiles
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coinranking_app.databinding.ItemCoinBinding;
import com.example.coinranking_app.models.Coin;
import com.example.coinranking_app.storage.PreferencesHelper;

import java.util.List;

public class RecyclerAdapterCoin extends RecyclerView.Adapter<RecyclerAdapterCoin.MyViewHolder> {
    public void setListener(OnCoinClickListener listener) {
        this.listener = listener;
    }

    private OnCoinClickListener listener;
    // TODO: camelCase
    private List<Coin> coin_list;
    private int preferedCoin;

    public RecyclerAdapterCoin(List<Coin> coinList) {
        this.coin_list = coinList;
    }
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new MyViewHolder(ItemCoinBinding.inflate(inflater, parent, false));
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        Coin coin = coin_list.get(position);
        holder.bind(coin);
        holder.binding.getRoot().setOnLongClickListener(v -> {
            // TODO: faire remonter le click au niveau de l'activity
            if (listener != null) {
                listener.onCoinClick(coin_list.get(position));
            }
            preferedCoin = holder.getBindingAdapterPosition();
            // TODO: on essaie de ne pas utiliser les prefs dans le RVA qui est une "view", les prefs c'est la gestion des données
            PreferencesHelper.getInstance().setCoin(coin);
            return false;
        });
    }

    public int getItemCount(){
        // TODO: attentoin si la liste est null
        return coin_list.size();
    }

    public void setCoinList(List<Coin> coins) {
        this.coin_list = coins;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ItemCoinBinding binding;
        public MyViewHolder(ItemCoinBinding itemCoinBinding) {
            super(itemCoinBinding.getRoot());
            this.binding = itemCoinBinding;
        }
        public void bind(Coin coin) {
            binding.textviewName.setText(coin.getName());
            binding.textviewPrice.setText("Price : " + String.valueOf(coin.getPrice()));
        }
    }
}
