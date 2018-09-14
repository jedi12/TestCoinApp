package ru.pioneersystem.testcoinapp.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.pioneersystem.testcoinapp.data.network.responses.CoinResponse;
import ru.pioneersystem.testcoinapp.R;

public class CoinsAdapter extends RecyclerView.Adapter<CoinsAdapter.CoinsViewHolder> {
    private List<CoinResponse.Coin> mCoins;

    public CoinsAdapter(List<CoinResponse.Coin> coins) {
        mCoins = coins;
    }

    @NonNull
    @Override
    public CoinsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coin_list, parent, false);
        return new CoinsViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull CoinsViewHolder holder, int position) {
        CoinResponse.Coin coin = mCoins.get(position);
        holder.mCoinName.setText(coin.getName());
        holder.mCoinSymbol.setText(coin.getSymbol());
        holder.mCoinSlug.setText(coin.getWebsiteSlug());
    }

    @Override
    public int getItemCount() {
        return mCoins.size();
    }

    static class CoinsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.coinName) TextView mCoinName;
        @BindView(R.id.coinSymbol) TextView mCoinSymbol;
        @BindView(R.id.coinSlug) TextView mCoinSlug;

        CoinsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
