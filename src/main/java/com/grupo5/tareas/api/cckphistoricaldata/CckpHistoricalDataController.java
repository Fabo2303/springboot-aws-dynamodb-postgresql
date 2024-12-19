package com.grupo5.tareas.api.cckphistoricaldata;

import com.grupo5.tareas.config.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/cckp-historical-data")
public class CckpHistoricalDataController {

    private final CckpHistoricalDataService cckpHistoricalDataService;

    public CckpHistoricalDataController(CckpHistoricalDataService cckpHistoricalDataService) {
        this.cckpHistoricalDataService = cckpHistoricalDataService;
    }

    @PostMapping()
    public ResponseEntity<Map<String, String>> saveCckpHistoricalData(@RequestParam("csv")MultipartFile file) {
        System.out.println("File: " + file);
        if (file.isEmpty()) {
            throw new CustomException("No file uploaded", HttpStatus.BAD_REQUEST);
        }

        Boolean saved = cckpHistoricalDataService.saveAll(file);

        if (!saved) {
            throw new CustomException("Error saving file", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(Map.of("message", "File uploaded successfully"));
    }

}
