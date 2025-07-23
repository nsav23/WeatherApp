//Include the needed libraries ("DHT Sensor Library", "Adafruit Unified Sensor Lib", BMP180I2C, install from Arduino IDE Library Manager)
#include <DHT.h>
#include <DHT_U.h>
#include <Adafruit_Sensor.h>
#include <BMP180.h>
#include <MetricSystem.h>

//Define constants
#define _ADAFRUIT_SENSOR_H
#define DHTPIN 2  // Define which digital pin you'll be connecting to on the Arduino Uno board
#define DHTTYPE DHT22
#define BMPTYPE BMP18  

//Initialize the sensor instance of the class 'dht'
DHT dht(DHTPIN, DHTTYPE);
BMP180 bmp;

void setup() 
{

  //Initialize serial communication at baud - 9600 bps (baud per second - бода в секунда)
  Serial.begin(9600);
  
  //Initialize the sensors and print status message
  dht.begin();
  Serial.println(F("DHT22 init!"));
  delay(100);
  bmp.begin();
  Serial.println(F("BMP180 init!"));
  bmp.setMetricSystem(MetricSystem());

}

void loop() 
{

  //Set a delay/interval before measuring - 4000ms = 4s
  delay(4000);

  //Set variables for reading humidity, temperature, atmospheric pressure and altitude respectively
  float h = dht.readHumidity();
  float t = dht.readTemperature();  //I use dht.readTemperature(); because it measures in Celsius by default, for °F, check libraries
  float p = bmp.readPressure();
  float alt = bmp.readAltitude();

  //If the arduino fails to receive the sensor input for any reason(bad connection, mismatched wires, etc.):
  if (isnan(h) || isnan(t) || isnan(p) || isnan(alt)) 
  {

    Serial.println(F("Failed to read!"));

  }

  //Print the measurements for humidity in % and temperature in Celsius, pressure in hectopascals and altitude in meters respectively
  Serial.print(F("Humidity: "));
  Serial.print(h);
  Serial.print("%");
  Serial.println();
  Serial.print(F("Temperature: "));
  Serial.print(t);
  Serial.print(" °C");
  Serial.println();
  Serial.print(F("Pressure: "));
  Serial.print(p / 100);
  Serial.print(" hPa");
  Serial.println();
  Serial.print(F("Altitude: "));
  Serial.print(abs(alt / 10));
  Serial.print(" m");
  Serial.println();

}
