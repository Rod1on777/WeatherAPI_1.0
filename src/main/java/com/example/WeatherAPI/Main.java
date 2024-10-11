package com.example.WeatherAPI;

import com.example.WeatherAPI.DTO.Current;
import com.example.WeatherAPI.DTO.CurrentResponse;
import com.example.WeatherAPI.DTO.Location;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] arg) throws IOException {

        final String key = "b9ada80041884ef8b6762943241010";
        Weather api = JsonPlaceholderService.getInstance().api();


        System.out.println("Weather API Console App");
        String location = "";

        while(true){
            Scanner in = new Scanner(System.in);
            System.out.print("Enter your city (or type stop to exit): ");
            location = in.next().toLowerCase();
            if(location.equals("stop")){
                break;
            }

            try{
                Response<CurrentResponse> weatherResponse = api.weather(key, location).execute();
                System.out.println("In " + weatherResponse.body().getLocation().name +
                        " - Temperature is " + weatherResponse.body().getCurrent().getTemp_c() +
                        " and it feels like " + weatherResponse.body().getCurrent().getFeelslike_c());
            }catch (Exception e){
                System.out.println("wrong input");
            }


        }

        System.out.println("Stopping...");
        System.exit(0);

    }
}
