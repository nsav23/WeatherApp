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

    protected void doGet (HttpServlet req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("application/json");     //Set content type to json
        res.setHeader("Cache-Control", "no-cache");

        String path = "/path_to_log/dht_readings_log.txt";  //You can name your .txt file differently
        File file = new File(path);

        String valueH = "";
        String valueT = "";
        String valueP = "";
        String valueAlt = "";

        try (Scanner scanner = new Scanner(new FileReader(file))) {

            //Execute continuously so long as there is data in the log
            while (scanner.hasNextLine()) {

                String input = scanner.nextLine().trim();

                if (input.isEmpty() || input.equals("END")) {

                    continue;

                }

                String[] data = input.split("\\s+");

                switch (data[0]) {

                    case "Humidity" -> {

                        valueH = data[1];

                    }
                    case "Temperature" -> {

                        valueT = data[1];

                    }
                    case "Pressure" -> {

                        valueP = data[1];

                    }
                    case "Altitude" -> {

                        valueAlt = data[1];

                    }

                }

            }

        } catch (Exception e) { //To prevent crashing during execution if something goes wrong with the entire process

            e.printStackTrace();    // For debugging purposes

        }

        String valueCOR = "";

        try {

            valueCOR = calculateCOR(valueH, valueP, valueT);    //Stands for "chance of rain"

        } catch (Exception e) {

            valueCOR = "?";

        }

        String json = String.format("{ \"temperature\": \"%s\", \"humidity\": \"%s\", \"pressure\": \"%s\", \"altitude\": \"%s\", \"COR\": \"%s\" }",
                valueT, valueH, valueP, valueAlt, valueCOR);    //Format all values as .json to return as response

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

}
