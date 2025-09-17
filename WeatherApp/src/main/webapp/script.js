var req;

function readValues() {

    clear();    /* Clear before fetching new values */

    fetch("/WeatherApp/read")
        .then(response => response.json())
        .then(data => {
            document.getElementById("tempReading").textContent = data.temperature.label + "\n" + data.temperature.value;
            document.getElementById("humidityReading").textContent = data.humidity.label + "\n" + data.humidity.value;
            document.getElementById("atmPressureReading").textContent = data.pressure.label + "\n" + data.pressure.value;
            document.getElementById("altitudeReading").textContent = data.altitude.label + "\n" + data.altitude.value;
            /*document.getElementById("windSpeedReading").textContent = "";*/
            document.getElementById("chanceOfRain").textContent = data.cor.label + "\n" + data.cor.value;
            /*document.getElementById("uvReading").textContent = "";*/
            /*document.getElementById("airQualityReading").textContent = "";*/
        }).catch(error => {
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
    setInterval(readValues, 4000);  //refreshes every 4 seconds to match Arduino data write

});
// TODO