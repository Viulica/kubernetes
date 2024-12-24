package hr.fer.aggregation_service.service;

import org.springframework.stereotype.Service;

@Service
public class TemperatureConverter {
    
    public double celsiusToKelvin(double celsius) {
        return celsius + 273.15;
    }
    
    public double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }
} 