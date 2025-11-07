package com.nikolay.weatherapp;

//=== WIP (for logic test only!!!) ===

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class WeatherAppServlet extends HttpServlet{

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("application/json");
        res.setHeader("Cache-Control", "no-cache");

        String path = "/path_to_log/dht_readings_log.txt";
        File file = new File(path);

        String valueH = "?";
        String valueT = "?";
        String valueP = "?";
        String valueAlt = "?";

        try (Scanner scanner = new Scanner(new FileReader(file))) {
            while (scanner.hasNextLine()) {

                String input = scanner.nextLine().trim();

                if (input.isEmpty() || input.equals("END")) continue;

                //Parse log data using ":"
                String[] data = input.split(":");

                if (data.length < 2) continue;

                String label = data[0].trim();
                String rawValue = data[1].trim();

                //Regex for removing special symbols when parsing log values
                String numeric = rawValue.replaceAll("[^0-9.\\-]", "");

                switch (label) {

                    case "Humidity" -> valueH = numeric;
                    case "Temperature" -> valueT = numeric;
                    case "Pressure" -> valueP = numeric;
                    case "Altitude" -> valueAlt = numeric;

                }

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        String valueCOR;

        try {

            valueCOR = calculateCOR(valueH, valueP, valueT);

        } catch (Exception e) {

            valueCOR = "?";

        }

        String temperatureText;

        try {

            temperatureText = defineTemperatureText(valueT);

        } catch (Exception e) {

            temperatureText = "?";

        }

        String humidityText;

        try {

            humidityText = defineHumidityText(valueH);

        } catch (Exception e) {

            humidityText = "?";

        }

        String json = "{"
                + "\"altitude\": {\"label\": \"Altitude\", \"value\": \"" + valueAlt + " m\"}, "
                + "\"pressure\": {\"label\": \"Pressure\", \"value\": \"" + valueP + " hPa\"}, "
                + "\"humidity\": {\"label\": \"Humidity\", \"value\": \"" + valueH + " %\"}, "
                + "\"temperature\": {\"label\": \"Temperature\", \"value\": \"" + valueT + " °C\"}, "
                + "\"cor\": {\"label\": \"Chance of Rain\", \"value\": \"" + valueCOR + " %\"}, "
                + "\"humtext\": {\"value\": \"" + humidityText + "\"}, "
                + "\"temptext\": {\"value\": \"" + temperatureText + "\"}"
                + "}";

        res.getWriter().write(json);

    }

    //Calculate COR(chance of rain) method
    private String calculateCOR(String valueH, String valueP, String valueT) {

        double humidity = Double.parseDouble(valueH);
        double pressure = Double.parseDouble(valueP);
        double temperature = Double.parseDouble(valueT);
        double baseCOR = (humidity - (pressure - 1010)) * 0.6;

        if (temperature < 20) {

            baseCOR += (20 - temperature) * 0.4;

        } else if (temperature > 25) {

            baseCOR -= (temperature - 25) * 0.3;

        }

         return String.format("%.1f", Math.max(0, Math.min(100, baseCOR)));

    }

    //Display temperature levels in words method
    private String defineTemperatureText(String valueT) {

        double temperature = Double.parseDouble(valueT);
        String tempWord = "";

        if (temperature <= -20){

            tempWord = "Extreme cold!";

        } else if (temperature > -20 && temperature <= -5) {

            tempWord = "Very cold";

        } else if (temperature > -5 && temperature <= 10) {

            tempWord = "Cold";

        } else if (temperature > 10 && temperature <= 20) {

            tempWord = "Warm";

        } else if (temperature > 20 && temperature <= 35) {

            tempWord = "Hot";

        } else if (temperature > 35) {

            tempWord = "Extreme heat!";

        }

        return tempWord;

    }

    //Display humidity levels in words method
    private String defineHumidityText(String valueH) {

        double humidity = Double.parseDouble(valueH);
        String humWord = "";

        if (humidity > 0 && humidity <= 30) {
            
            humWord = "Dry";
            
        } else if (humidity > 30 && humidity <= 60) {

            humWord = "Normal";
            
        } else if (humidity > 60) {

            humWord = "Humid";

        }

        return humWord;

    }

}
