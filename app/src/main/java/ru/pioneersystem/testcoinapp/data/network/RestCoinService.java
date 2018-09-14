package ru.pioneersystem.testcoinapp.data.network;

import retrofit2.Call;
import retrofit2.http.GET;
import ru.pioneersystem.testcoinapp.data.network.responses.CoinResponse;

public interface RestCoinService {
    @GET("listings/")
    Call<CoinResponse> getCoinsList();
}
