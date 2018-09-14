package ru.pioneersystem.testcoinapp.data.network.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CoinResponse {
    @SerializedName("data")
    @Expose
    private List<Coin> data;

    @SerializedName("metadata")
    @Expose
    private Metadata metadata;

    public List<Coin> getData() {
        return data;
    }

    public static class Coin {
        @SerializedName("id")
        @Expose
        private int id;

        @SerializedName("name")
        @Expose
        private String name;

        @SerializedName("symbol")
        @Expose
        private String symbol;

        @SerializedName("website_slug")
        @Expose
        private String websiteSlug;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getSymbol() {
            return symbol;
        }

        public String getWebsiteSlug() {
            return websiteSlug;
        }
    }

    public static class Metadata {
        @SerializedName("timestamp")
        @Expose
        private int timestamp;

        @SerializedName("num_cryptocurrencies")
        @Expose
        private int numCryptocurrencies;

        @SerializedName("error")
        @Expose
        private String error;
    }
}