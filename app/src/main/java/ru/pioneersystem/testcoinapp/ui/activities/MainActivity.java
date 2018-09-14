package ru.pioneersystem.testcoinapp.ui.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.pioneersystem.testcoinapp.data.managers.DataManager;
import ru.pioneersystem.testcoinapp.data.network.responses.CoinResponse;
import ru.pioneersystem.testcoinapp.R;
import ru.pioneersystem.testcoinapp.ui.adapters.CoinsAdapter;
import ru.pioneersystem.testcoinapp.utils.NetworkStatusChecker;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @BindView(R.id.frameLayout) FrameLayout mFrameLayout;
    @BindView(R.id.coins_list) RecyclerView mCoinsList;
    @BindView(R.id.empty_list) TextView mEmptyView;
    @BindView(R.id.progress_bar) ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mCoinsList.setHasFixedSize(true);
        mCoinsList.setLayoutManager(new LinearLayoutManager(this));
        mCoinsList.swapAdapter(new CoinsAdapter(new ArrayList<CoinResponse.Coin>()), false);

        if (!NetworkStatusChecker.isNetworkAvailable(this)) {
            showSnackbar("Сеть недоступна");
            return;
        }

        loadCoins();
    }

    private void loadCoins() {
        setCoinListVisible(false);
        DataManager.getInstance().loadCoinsFromNetwork().enqueue(new Callback<CoinResponse>() {
            @Override
            public void onResponse(@NonNull Call<CoinResponse> call, @NonNull Response<CoinResponse> response) {
                if (response.code() == 200) {
                    CoinResponse coinsRes = response.body();
                    if (coinsRes != null) {
                        mCoinsList.swapAdapter(new CoinsAdapter(coinsRes.getData()), false);
                        setCoinListVisible(true);
                    }
                } else {
                    showSnackbar("Проблема с сервером");
                    Log.e(TAG, "Response error: " + response.code() + ": " + response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<CoinResponse> call, @NonNull Throwable t) {
                showSnackbar("Проблема с сервером");
                Log.e(TAG, "Network error: " + t.getMessage());
            }
        });
    }

    private void setCoinListVisible(boolean visible) {
        mCoinsList.setVisibility(visible ? View.VISIBLE : View.GONE);
        mEmptyView.setVisibility(!visible ? View.VISIBLE : View.GONE);
        mProgressBar.setVisibility(!visible ? View.VISIBLE : View.GONE);
    }

    private void showSnackbar(String message) {
        Snackbar.make(mFrameLayout, message, Snackbar.LENGTH_LONG).show();
    }
}
