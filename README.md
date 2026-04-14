# 🌤️ WeatherApp

A full-stack weather monitoring project that reads sensor data from an Arduino, processes it via a Java servlet, and displays real-time weather information on a website.

---

## ✨ Features

- 🌡️ Reads temperature, humidity, atmospheric pressure, and altitude from Arduino sensors  
- ⚙️ Processes data in real-time via a Java servlet backend  
- 🖥️ Auto-refreshing UI using AJAX (Fetch API)
- ⚡ Dynamic icons based on weather conditions
- 🔧 Modular and easy to extend

### 🛠️ Prerequisites

- Arduino Uno with sensors (DHT22, BMP180) connected  
- Java 8+ and Servlet container (e.g., Apache Tomcat)  
- Python 3 (optional, for data logging or forwarding)  
- Web browser

### ⚡ Installation

1. Connect the Arduino and the sensors as shown in these diagrams:
   
    BMP180  
   ![bmp180_img](https://github.com/nsav23/WeatherApp/blob/main/Other%20Images/bmp180.png?raw=true)      
    DHT22   
   ![dht22_img](https://github.com/nsav23/WeatherApp/blob/main/Other%20Images/dht22.png?raw=true)

   The simplest way to do it is using a breadboard as shown in the photo: (TODO)
    
3. Upload the .ino file from the project to the Arduino board
4. If you choose to log the sensor readings with a Python script using Linux:
   - Install pyserial:
     pip install pyserial
   - Download the .py script from the project
   - Find the port the Arduino is connected to:
     ls /dev/ttyACM*
     or
     dmesg | grep tty
5. While the Arduino is running, execute the Python script:
   python3 dht_data_log.py
   (Make sure the Serial port in the Arduino IDE is closed before doing that)
6. Build the Java Web App:
   - From the project root (where pom.xml is):
     mvn clean package
   - This generates a .war file inside the target/ directory
7. Deploy to Tomcat:
   - Rename the .war file to WeatherApp.war (optional)
   - Copy it to apache-tomcat/webapps/
   - Start Tomcat:
     ./catalina.sh start
8. Run the app:
   http://localhost:8080/WeatherApp/

```
📁 Project Structure
WeatherApp/
├── pom.xml
└── src
    └── main
        ├── java
        │   └── com
        │       └── nikolay
        │           └── weatherapp
        │               └── WeatherAppServlet.java
        └── webapp
            ├── index.html
            ├── style.css
            ├── script.js
            ├── img/
            │   ├── cold.png
            │   ├── warm.png
            │   ├── hot.png
            │   └── ...
            └── WEB-INF
                └── web.xml
```

🛠️Design Prototype
![concept_img](https://github.com/nsav23/WeatherApp/blob/main/Other%20Images/concept.png)

🛠️ Technologies Used
1.	Arduino IDE (C/C++) 
2.	Python 3 
3.	Java (Jakarta Servlet API) 
4.	Apache Tomcat
5.	Maven (build tool) 
6.	HTML/CSS/JavaScript (Frontend)

🧠 How It Works
1.	Arduino reads sensor data 
2.	(Optional) Python logs data into a .txt file 
3.	Java Servlet reads the latest values 
4.	Frontend fetches data via AJAX (/WeatherApp/read) 
5.	UI updates dynamically without page reload

✨Working App
![app_img](https://github.com/nsav23/WeatherApp/blob/main/Other%20Images/Animation.gif)
📚 Third-party Libraries

This project uses the following open-source Arduino libraries:

- [Adafruit Sensor Library](https://github.com/adafruit/Adafruit_Sensor) (MIT License)
- [DHT Sensor Library by Adafruit](https://github.com/adafruit/DHT-sensor-library) (MIT License)
- [BMP180 Library](https://github.com/styropyr0/BMP180) (MIT License)
- [MetricSystem Library](https://github.com/styropyr0/SensorHub) (MIT License)

These libraries are maintained by their respective authors and licensed under the MIT License.

📄 License
This project is licensed under the MIT License - [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](./LICENSE)
