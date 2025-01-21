package hr.fer.humidity_service.service;

import hr.fer.humidity_service.model.Humidity;
import hr.fer.humidity_service.model.HumidityReading;
import hr.fer.humidity_service.repository.HumidityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.Instant;

@Service
public class HumidityService {
    
    @Autowired
    private HumidityRepository repository;
    
    private static final String CSV_PATH = "/data/readings.csv";
    
    public Humidity getCurrentHumidity() {
        int row = calculateCurrentRow();
        double value = getValueFromCsv(row);
        repository.save(new HumidityReading(value));
        return new Humidity("Humidity", "%", value);
    }
    
    private int calculateCurrentRow() {
        long currentSeconds = Instant.now().getEpochSecond();
        return (int)(currentSeconds % 100) + 1;
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
                    return Double.parseDouble(values[2]); // Humidity je u trećem stupcu
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0.0;
    }
} 