package hr.fer.humidity_service.service;

import hr.fer.humidity_service.model.Humidity;
import hr.fer.humidity_service.model.HumidityReading;
import hr.fer.humidity_service.repository.HumidityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.Instant;

@Service
public class HumidityService {
    
    @Autowired
    private HumidityRepository repository;
    
    public Humidity getCurrentHumidity() {
        int row = calculateCurrentRow();
        double value = getValueFromCsv(row);
        repository.save(new HumidityReading(value));
        return new Humidity("Humidity", "%", value);
    }
    
    private int calculateCurrentRow() {
        long currentSeconds = Instant.now().getEpochSecond();
        long secondsSince1970 = currentSeconds;
        return (int)(secondsSince1970 % 100) + 1;
    }
    
    private double getValueFromCsv(int row) {
        try {
            ClassPathResource resource = new ClassPathResource("readings.csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
            
            String line;
            int currentRow = 0;
            while ((line = reader.readLine()) != null && currentRow < row) {
                currentRow++;
                if (currentRow == row) {
                    String[] values = line.split(",");
                    return Double.parseDouble(values[2]);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0.0; 
    }
} 