package com.example.coinranking_app;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coinranking_app.databinding.ItemCoinBinding;
import com.example.coinranking_app.models.Coin;
import com.example.coinranking_app.storage.PreferencesHelper;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerAdapterCoin extends RecyclerView.Adapter<RecyclerAdapterCoin.MyViewHolder> {
    private OnCoinClickListener listener;
    private List<Coin> coinList;
    private int preferedCoin;
    public void setListener(OnCoinClickListener listener) {
        this.listener = listener;
    }

    public RecyclerAdapterCoin(List<Coin> coinList) {
        this.coinList = coinList;
    }
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new MyViewHolder(ItemCoinBinding.inflate(inflater, parent, false));
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        Coin coin = coinList.get(position);
        holder.bind(coin);
        holder.binding.getRoot().setOnLongClickListener(v -> {
            if (listener != null) {
                // Envoie le coin pour le passer dans la nouvelle activité
                listener.onCoinLongClick(coinList.get(position));
            }
            preferedCoin = holder.getBindingAdapterPosition();
            return true;
        });
        holder.binding.getRoot().setOnClickListener(v -> {
            if (listener != null) {
                // Envoie le coin pour le passer dans la nouvelle activité
                listener.onCoinClick(coinList.get(position));
            }
            preferedCoin = holder.getBindingAdapterPosition();
        });
    }

    // TODO : attention si le liste est nulle
    public int getItemCount(){
        return coinList.size();
    }

    public void setCoinList(List<Coin> coins) {
        this.coinList = coins;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ItemCoinBinding binding;
        public MyViewHolder(ItemCoinBinding itemCoinBinding) {
            super(itemCoinBinding.getRoot());
            this.binding = itemCoinBinding;
        }
        public void bind(Coin coin) {
            binding.textviewName.setText(coin.getName());
            binding.textviewPrice.setText("Price : " + String.format("%.2f", coin.getPrice()));
            Picasso.get().load(coin.getIconUrl().replace("svg", "png")).into(binding.imageviewIcon);
        }
    }
}
