package ru.pioneersystem.testcoinapp.data.managers;

import retrofit2.Call;
import ru.pioneersystem.testcoinapp.data.network.RestCoinService;
import ru.pioneersystem.testcoinapp.data.network.ServiceGenerator;
import ru.pioneersystem.testcoinapp.data.network.responses.CoinResponse;

public class DataManager {
    private static DataManager INSTANCE = null;
    private RestCoinService mRestCoinService;

    private DataManager() {
        mRestCoinService = ServiceGenerator.createService(RestCoinService.class);
    }

    public static DataManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DataManager();
        }

        return INSTANCE;
    }

    public Call<CoinResponse> loadCoinsFromNetwork() {
        return mRestCoinService.getCoinsList();
    }
}
