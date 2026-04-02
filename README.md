# 🌤️ WeatherApp

A full-stack weather monitoring project that reads sensor data from an Arduino, processes it via a Java servlet, and displays real-time weather information on a website.

---

## ✨ Features

- 🌡️ Reads temperature, humidity, atmospheric pressure, and altitude from Arduino sensors  
- ⚙️ Processes data in real-time via a Java servlet backend  
- 🖥️ Displays live weather data on a web frontend  
- 🔧 Modular and easy to extend
- TODO

### 🛠️ Prerequisites

- Arduino Uno with sensors (DHT22, BMP180) connected  
- Java 8+ and Servlet container (e.g., Apache Tomcat)  
- Python 3 (optional, for data logging or forwarding)  
- Web browser

### ⚡ Installation

1. Connect the ardunio and the sensors as shown in these digrams:
   (TODO)
2. Upload the .ino file from the project to the Arduino board
3. If you choose to log the sensor readings with a python script using Linux:
   - install pyserial: pip install pyserial   
   - download the .py script from the project
   - find out which port the Arduino is connected to(use either of these commands in the terminal): ls /dev/ttyACM* or dmesg | grep tty
4. While the Arduino is running execute the python script from the terminal: python3 dht_data_log.py
(make sure the Serial port in the Arduino IDE is closed before doing that)
==TODO==

🛠️ Technologies Used

1. Arduino IDE (C/C++)
2. Python 3
3. Java Servlet API
4. Apache Tomcat
5. HTML/CSS/JavaScript (Frontend)

📚 Third-party Libraries

This project uses the following open-source Arduino libraries:

- [Adafruit Sensor Library](https://github.com/adafruit/Adafruit_Sensor) (MIT License)
- [DHT Sensor Library by Adafruit](https://github.com/adafruit/DHT-sensor-library) (MIT License)
- [BMP180 Library](https://github.com/styropyr0/BMP180) (MIT License)
- [MetricSystem Library](https://github.com/styropyr0/SensorHub) (MIT License)

These libraries are maintained by their respective authors and licensed under the MIT License.

📄 License
This project is licensed under the MIT License - [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](./LICENSE)
