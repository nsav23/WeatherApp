package com.nikolay.weatherapp;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class WeatherAppServlet {

    public static void main(String[] args) throws IOException {

        //=== WIP (for logic test only!!!) ===

        String path = "/path_to_log/dht_readings_log.txt";  //You can name your .txt file differently
        File file = new File(path);

        try {

            while (true) {

                Scanner scanner = new Scanner(new FileReader(file));

                //Execute continuously so long as there is data in the log
                while (scanner.hasNextLine()) {

                    String input = scanner.nextLine().trim();

                    //
                    if (input.equals("END")) {

                        System.out.println("END found. Stopping.");
                        scanner.close();
                        return;

                    }

                    if (input.isEmpty())    // Skips reading the empty Serial.println() lines in the Arduino code
                    {

                        continue;

                    }

                    System.out.println("Read: " + input);   // Prints the readings to console (for debugging)

                }

                scanner.close();
                Thread.sleep(4000); /* Time interval before reading
                                           the log again(setting it the same as Arduino interval works best).
                                           */
            }

        } catch (Exception e) { //To prevent crashing during execution if something goes wrong with the entire process

            e.printStackTrace();    // For debugging purposes

        }

    }

}
