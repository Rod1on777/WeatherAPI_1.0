package com.example.WeatherAPI;

import com.example.WeatherAPI.DTO.CurrentResponse;
import com.example.WeatherAPI.DTO.Location;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;
import java.util.Locale;

public interface Weather {

    @GET("/v1/current.json")
    Call<CurrentResponse> weather(@Query("key") String key, @Query("q") String location);

}
