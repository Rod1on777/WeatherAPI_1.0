package com.example.WeatherAPI;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

public class JsonPlaceholderService {

    private Retrofit retrofit;
    private static JsonPlaceholderService instance;
    private final String BASE_URL = "https://api.weatherapi.com";

    private JsonPlaceholderService(){
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static JsonPlaceholderService getInstance(){
        if(instance == null){
            instance = new JsonPlaceholderService();
        }
        return instance;
    }

    public Weather api(){
        return retrofit.create(Weather.class);
    }
}
