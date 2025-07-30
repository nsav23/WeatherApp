var req;

function readValues() {
    var readTemperature = document.getElementById("tempReading");
    var tempValue = readTemperature.value;
    var readHumidity = document.getElementById("humidityReading");
    var humValue = readHumidity.value;
    //var readAQI = document.getElementById("airQualityReading");
    //var aqiValue = readAQI.value;
    var readPressure = document.getElementById("atmPressureReading");
    var pressureValue = pressureReading.value;
    var readAlt = document.getElementById("altitudeReading");
    var altValue = altitudeReading.value;
    //var readWindSpeed = document.getElementById("windSpeedReading");
    //var windSpeedValue = windSpeedReading.value;
    //var readChanceOfRain = document.getElementById("chanceOfRain");
    //var chanceOfRainValue = chanceOfRain.value;
    //var readUV = document.getElementById("uvReading");
    //var uvValue = uvReading.value;

    var url = "/WeatherApp/read?value=" + encodeURIComponent(value);

    req.open("Get", url, true);
    req.onreadystatechange = callback;
    req.send(null);
}

function callback() {
    /* placeholder code
    if (req.status === 4) {
        if (req.status === 200) {

        }
    }
    clear();
    */
}

function clear() {  /* Clears old values before displaying new ones(refreshing)
    /* This is one way to do it
    var readTemperature = document.getElementByID("tempReading");
    var tempValue = "";
    var readHumidity = document.getElementByID("humidityReading");
    var humValue = "";
    //var readAQI = document.getElementByID("airQualityReading");
    //var aqiValue = "";
    var readPressure = document.getElementByID("atmPressureReading");
    var pressureValue = "";
    var readAlt = document.getElementByID("altitudeReading");
    var altValue = "";
    //var readWindSpeed = document.getElementByID("windSpeedReading");
    //var windSpeedValue = "";
    //var readChanceOfRain = document.getElementByID("chanceOfRain");
    //var chanceOfRainValue = "";
    //var readUV = document.getElementByID("uvReading");
    //var uvValue = "";
    */
    /* TODO */
        /* fetch json response */
     document.getElementById("tempReading").textContent = "";
     document.getElementById("humidityReading").textContent = "";
     document.getElementById("atmPressureReading").textContent = "";
     document.getElementById("altitudeReading").textContent = "";
     document.getElementById("windSpeedReading").textContent = "";
     document.getElementById("chanceOfRain").textContent = "";
     document.getElementById("uvReading").textContent = "";
     document.getElementById("airQualityReading").textContent = "";
}
// TODO