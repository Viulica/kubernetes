package hr.fer.humidity_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class HumidityReading {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double humidity;
    
    public HumidityReading() {}
    
    public HumidityReading(double humidity) {
        this.humidity = humidity;
    }
    
    public Long getId() {
        return id;
    }
    
    public double getHumidity() {
        return humidity;
    }
    
    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }
} 