package com.grupo5.tareas.api.cckphistoricaldata;

import com.opencsv.CSVReader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CckpHistoricalDataService {

    private final CckpHistoricalDataRepository cckpHistoricalDataRepository;

    public CckpHistoricalDataService(CckpHistoricalDataRepository cckpHistoricalDataRepository) {
        this.cckpHistoricalDataRepository = cckpHistoricalDataRepository;
    }

    public void save(CckpHistoricalData cckpHistoricalData) {
        cckpHistoricalDataRepository.save(cckpHistoricalData);
    }

    public Boolean isDigitOrFloat(String str) {
        str = str.trim();
        str = str.replace(",", ".");
        str = str.replace("-", "");
        if (str.isEmpty()) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i)) && str.charAt(i) != '.') {
                return false;
            }
        }
        return true;
    }

    public Boolean saveAll(MultipartFile file) {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(file.getInputStream());
            CSVReader csvReader = new CSVReader(inputStreamReader);

            List<CckpHistoricalData> dataList = new ArrayList<>();
            String[] nextLine;

            while ((nextLine = csvReader.readNext()) != null) {
                if (!isDigitOrFloat(nextLine[0]) || !isDigitOrFloat(nextLine[1]) || !isDigitOrFloat(nextLine[2]) || !isDigitOrFloat(nextLine[3])) {
                    continue;
                }
                CckpHistoricalData data = new CckpHistoricalData();
                data.setYear(nextLine[0]);
                data.setMonth(nextLine[1]);
                data.setPrecipitacion(nextLine[2]);
                data.setTemperatura(nextLine[3]);

                dataList.add(data);
            }

            cckpHistoricalDataRepository.saveAll(dataList);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<CckpHistoricalData> findAll() {
        return cckpHistoricalDataRepository.findAll();
    }

    public CckpHistoricalData findByYearAndMonth(String year, String month) {
        return cckpHistoricalDataRepository.findByYearAndMonth(year, month);
    }
}
