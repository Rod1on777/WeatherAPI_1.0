package com.example.WeatherAPI;

import com.example.WeatherAPI.DTO.CurrentResponse;
import com.example.WeatherAPI.DTO.Location;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Locale;

public interface Weather {

    @GET("/v1/current.json")
    Call<CurrentResponse> weather(@Query("key") String key, @Query("q") String location);

    @PUT("/v1/current.json")
    Call<CurrentResponse> putRegion(@Query("key") String key, @Query("q") String location, @Body CurrentResponse currentResponse);

    @DELETE("/v1/current.json")
    Call<CurrentResponse> delete(@Query("key") String key, @Query("q") String location);

    @POST("/v1/current.json")
    Call<CurrentResponse> create(@Query("key") String key, @Query("q") String location, @Body CurrentResponse currentResponse);
}
