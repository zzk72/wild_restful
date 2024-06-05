package com.example.wild_restful;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.HashMap;
import java.util.Map;

@Path("/weather")
public class WeatherService {

    @GET
    @Path("/forecast")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWeatherForecast(@QueryParam("date") String date, @QueryParam("viewpointId") int viewpointId) {
        // Simulate weather information
        String[] weatherConditions = {"Sunny", "Cloudy", "Overcast", "Light Rain", "Moderate Rain", "Heavy Rain", "Storm", "Thunderstorm"};
        int index = (int) (Math.random() * weatherConditions.length);
        Map<String, String> result = new HashMap<>();
        result.put("date", date);
        result.put("viewpointId", String.valueOf(viewpointId));
        result.put("weather", weatherConditions[index]);
        return Response.ok(result).encoding("UTF-8").build();
    }

    @GET
    @Path("/air")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAirQuality(@QueryParam("date") String date, @QueryParam("viewpointId") int viewpointId) {
        // Simulate air quality information
        String[] airQuality = {"Excellent", "Good", "Light Pollution", "Moderate Pollution", "Heavy Pollution", "Severe Pollution"};
        int index = (int) (Math.random() * airQuality.length);
        Map<String, String> result = new HashMap<>();
        result.put("date", date);
        result.put("viewpointId", String.valueOf(viewpointId));
        result.put("airQuality", airQuality[index]);
        return Response.ok(result).encoding("UTF-8").build();
    }

    @GET
    @Path("/temperature")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTemperature(@QueryParam("date") String date, @QueryParam("viewpointId") int viewpointId) {
        // Simulate temperature information
        Map<String, String> result = new HashMap<>();
        result.put("date", date);
        result.put("viewpointId", String.valueOf(viewpointId));
        result.put("temperature", String.valueOf((15 + (int)(Math.random() * 15))));
        return Response.ok(result).encoding("UTF-8").build();
    }
}
