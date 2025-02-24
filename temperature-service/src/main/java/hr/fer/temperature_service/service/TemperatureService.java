package hr.fer.temperature_service.service;

import hr.fer.temperature_service.model.Temperature;
import hr.fer.temperature_service.model.TemperatureReading;
import hr.fer.temperature_service.repository.TemperatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.Instant;

@Service
public class TemperatureService {
    
    @Autowired
    private TemperatureRepository repository;
    
    private static final String CSV_PATH = "/data/readings.csv";
    
    public Temperature getCurrentTemperature() {
        int row = calculateCurrentRow();
        double value = getValueFromCsv(row);
        repository.save(new TemperatureReading(value));
        return new Temperature("Temperature", "C", value);
    }
    
    private int calculateCurrentRow() {
        long currentSeconds = Instant.now().getEpochSecond();
        long secondsSince1970 = currentSeconds;
        return (int)(secondsSince1970 % 100) + 1;
    }
    
    private double getValueFromCsv(int row) {
        try {
            File csvFile = new File(CSV_PATH);
            BufferedReader reader = new BufferedReader(new FileReader(csvFile));
            
            String line;
            int currentRow = 0;
            while ((line = reader.readLine()) != null && currentRow < row) {
                currentRow++;
                if (currentRow == row) {
                    String[] values = line.split(",");
                    return Double.parseDouble(values[0]);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0.0;
    }
} 