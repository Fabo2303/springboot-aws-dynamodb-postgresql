package com.grupo5.tareas.api;

import com.grupo5.tareas.api.cckphistoricaldata.CckpHistoricalData;
import com.grupo5.tareas.api.cckphistoricaldata.CckpHistoricalDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;
import java.util.Random;

@Controller
public class WeatherDataController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    private final List<CckpHistoricalData> cckpHistoricalDataList;
    private final Random random = new Random();

    public WeatherDataController(CckpHistoricalDataService cckpHistoricalDataService) {
        this.cckpHistoricalDataList = cckpHistoricalDataService.findAll();
    }

    @Scheduled(fixedRate = 60000)
    public void sendTemperatureAndPrecipitation() {
        int year = random.nextInt(2023 - 1901 + 1) + 1901;
        int month = random.nextInt(12) + 1;

        CckpHistoricalData cckpHistoricalData = cckpHistoricalDataList.stream()
                .filter(data -> data.getYear().equals(String.valueOf(year)) && data.getMonth().equals(String.valueOf(month)))
                .findFirst()
                .orElse(null);

        Map<String, Object> data;
        if (cckpHistoricalData == null) {
            data = Map.of("year", year, "month", month, "precipitation", 0, "temperature", 0);
        } else {
            data = Map.of(
                    "year", cckpHistoricalData.getYear(),
                    "month", cckpHistoricalData.getMonth(),
                    "precipitation", cckpHistoricalData.getPrecipitacion(),
                    "temperature", cckpHistoricalData.getTemperatura()
            );
        }

        messagingTemplate.convertAndSend("/topic/measurements", data);
    }
}
