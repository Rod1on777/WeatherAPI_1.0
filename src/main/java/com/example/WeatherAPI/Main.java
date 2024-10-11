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
        String action = "";

        while(true){
            Scanner in = new Scanner(System.in);
            System.out.print("Enter your city (or type stop to exit): ");
            location = in.next().toLowerCase();
            if(location.equals("stop")){
                break;
            }

            Response<CurrentResponse> weatherResponse = api.weather(key, location).execute();

            if(!weatherResponse.isSuccessful()) {
                System.out.println("wrong input");
                continue;
            }

            System.out.println("Action (GET, PUT, DELETE, CREATE):");
            action = in.next().toLowerCase();
            System.out.println("Action: " + action.toUpperCase());


            if(action.equals("get")){           //GET --------------
                System.out.println(weatherResponse);
                System.out.println("In " + weatherResponse.body().getLocation().getName() +
                        " region " + weatherResponse.body().getLocation().getRegion() +
                        " - Temperature is " + weatherResponse.body().getCurrent().getTemp_c() +
                        " and it feels like " + weatherResponse.body().getCurrent().getFeelslike_c());

            }else if (action.equals("put")){    //PUT ---------------
                CurrentResponse regionChange = new CurrentResponse();
                Location newLocation = weatherResponse.body().getLocation();
                newLocation.setRegion("Bebria");
                regionChange.setLocation(newLocation);
                CurrentResponse currentResponsePut = api.putRegion(key, location, regionChange).execute().body();
                System.out.println("Region now - " + weatherResponse.body().getLocation().getRegion());
            }else if(action.equals("delete")){
                Integer code = api.delete(key, location).execute().code();
                System.out.println("Code:"+code);
            }else if(action.equals("create")){
                CurrentResponse createResponse = new CurrentResponse();
                Location newLocation = weatherResponse.body().getLocation();
                newLocation.setCountry("Created country");
                createResponse.setLocation(newLocation);
                CurrentResponse currentResponseCreate = api.create(key, location, createResponse).execute().body();
                Integer code = api.create(key, location, createResponse).execute().code();
                System.out.println("Status:" + currentResponseCreate.getLocation().getCountry() + " " + code);
            }else{
                    System.out.println("Invalid action");
            }

        }

        System.out.println("Stopping...");
        System.exit(0);

    }
}
