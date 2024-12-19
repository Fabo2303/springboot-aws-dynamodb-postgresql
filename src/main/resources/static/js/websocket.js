let stompClient = null;
const months = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio",
    "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"];

function connectWebSocket() {
    const socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function () {
        stompClient.subscribe('/topic/measurements', function (message) {
            const data = JSON.parse(message.body);
            console.log("Data received:", data);
            updateMeasurements(data);
        });
    });
}

function updateMeasurements(data) {
    console.log("Data received:", data); // Mostrar datos en la consola
    document.getElementById("month").innerText = months[data.month - 1];
    document.getElementById("year").innerText = data.year;

    const temperatureElement = document.getElementById("temperature");
    temperatureElement.innerText = data.temperature + " °C";
    setTemperatureColor(temperatureElement, data.temperature);

    const precipitationElement = document.getElementById("precipitation");
    precipitationElement.innerText = data.precipitation + " mm";
    setPrecipitationColor(precipitationElement, data.precipitation);
}


function setTemperatureColor(element, temperature) {
    if (temperature < 10) {
        element.setAttribute("data-value", "low");
    } else if (temperature < 25) {
        element.setAttribute("data-value", "normal");
    } else {
        element.setAttribute("data-value", "high");
    }
}

function setPrecipitationColor(element, precipitation) {
    if (precipitation < 30) {
        element.setAttribute("data-value", "low");
    } else if (precipitation < 70) {
        element.setAttribute("data-value", "normal");
    } else {
        element.setAttribute("data-value", "high");
    }
}

connectWebSocket();
