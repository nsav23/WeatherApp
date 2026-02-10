var req;

function readValues() {

    clear();    /* Clear before fetching new values */

    fetch("/WeatherApp/read")
        .then(response => response.json())
        .then(data => {
            let temperatureValue = parseFloat(data.temperature.value);
            let temperatureIcon = document.getElementById("tempIcon");
            let humidityValue = parseFloat(data.humidity.value);
            let humidityIcon = document.getElementById("humIcon");

            console.log("Received JSON:", data);
            document.getElementById("tempReading").textContent = data.temperature.value;
            document.getElementById("tempText").textContent = data.temptext.value;
            document.getElementById("humText").textContent = data.humtext.value;
            document.getElementById("humidityReading").textContent = data.humidity.value;
            document.getElementById("atmPressureReading").textContent = data.pressure.value;
            document.getElementById("altitudeReading").textContent = data.altitude.value;
            /*document.getElementById("windSpeedReading").textContent = "";*/
            document.getElementById("chanceOfRain").textContent = data.cor.value;
            /*document.getElementById("uvReading").textContent = "";*/
            /*document.getElementById("airQualityReading").textContent = "";*/

            determineTemperatureIcon(temperatureValue, temperatureIcon);
            determineHumidityIcon(humidityValue, humidityIcon);
        }).catch(error => {
            console.error("Error when fetching data", error);
            console.error("Error when fetching data", error);
        });
}

function clear() {  /* Clears old values before displaying new ones(refreshing) */
     document.getElementById("tempReading").textContent = "";
     document.getElementById("humidityReading").textContent = "";
     document.getElementById("atmPressureReading").textContent = "";
     document.getElementById("altitudeReading").textContent = "";
     /*document.getElementById("windSpeedReading").textContent = "";*/
     document.getElementById("chanceOfRain").textContent = "";
     /*document.getElementById("uvReading").textContent = "";*/
     /*document.getElementById("airQualityReading").textContent = "";*/
}

document.addEventListener("DOMContentLoaded", () => {   /*Adds event listener to HTML and synchronises fetching values with Arduino writing them (4s interval),
                                                          DOMContentLoaded - after HTML is loaded but before CSS and graphical elements */
    readValues();   // Calls readValue() after page is loaded
    setInterval(readValues, 20000);     //Refresh every 20 seconds instead of 4 because flickering looks annoying
});

function determineTemperatureIcon(temperatureValue, temperatureIcon){
    if(temperatureValue <= -20){
        temperatureIcon.src = "img/extreme_cold.png";
    }else if(temperatureValue > -20 && temperatureValue <= -5){
        temperatureIcon.src = "img/very_cold.png";
    }else if(temperatureValue > -5 && temperatureValue <= 10){
        temperatureIcon.src = "img/cold.png";
    }else if(temperatureValue > 10 && temperatureValue <= 20){
        temperatureIcon.src = "img/warm.png";
    }else if(temperatureValue > 20 && temperatureValue <= 35){
        temperatureIcon.src = "img/hot.png";
    }else if(temperatureValue > 35){
        temperatureIcon.src = "img/extreme_heat.png";
    }
}

function determineHumidityIcon(humidityValue, humidityIcon){
    if(humidityValue > 0 && humidityValue <= 30){
        humidityIcon.src = "img/dry.png";
    }else if(humidityValue > 30 && humidityValue <= 60){
        humidityIcon.src = "img/normal.png";
    }else if(humidityValue > 60){
        humidityIcon.src = "img/humid.png";
    }
}
