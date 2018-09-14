package ru.pioneersystem.testcoinapp.data.network;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.pioneersystem.testcoinapp.App;
import ru.pioneersystem.testcoinapp.BuildConfig;

public class ServiceGenerator {
    private static OkHttpClient.Builder sHttpClient = new OkHttpClient.Builder()
            .connectTimeout(BuildConfig.REST_MAX_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
            .readTimeout(BuildConfig.REST_MAX_READ_TIMEOUT, TimeUnit.MILLISECONDS)
            .cache(new Cache(App.getContext().getCacheDir(), Integer.MAX_VALUE));

    private static Retrofit.Builder sBuilder = new Retrofit.Builder()
            .baseUrl(BuildConfig.REST_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = sBuilder
                .client(sHttpClient.build())
                .build();
        return retrofit.create(serviceClass);
    }
}
