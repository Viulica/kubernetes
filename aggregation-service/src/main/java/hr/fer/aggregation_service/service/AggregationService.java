package hr.fer.aggregation_service.service;

import hr.fer.aggregation_service.model.AggregatedReading;
import hr.fer.aggregation_service.model.SensorReading;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Service
public class AggregationService {
    
    private final WebClient webClient;
    
    @Value("${service.temperature.url}")
    private String temperatureServiceUrl;
    
    @Value("${service.humidity.url}")
    private String humidityServiceUrl;
    
    @Value("${temperature.unit}")
    private String temperatureUnit;
    
    @Autowired
    private TemperatureConverter temperatureConverter;
    
    public AggregationService() {
        this.webClient = WebClient.create();
    }
    
    public AggregatedReading getAggregatedReadings() {
        Mono<SensorReading> temperatureReading = webClient.get()
                .uri(temperatureServiceUrl)
                .retrieve()
                .bodyToMono(SensorReading.class)
                .map(this::convertTemperatureIfNeeded);
                
        Mono<SensorReading> humidityReading = webClient.get()
                .uri(humidityServiceUrl)
                .retrieve()
                .bodyToMono(SensorReading.class);
                
        return new AggregatedReading(Arrays.asList(
            temperatureReading.block(),
            humidityReading.block()
        ));
    }
    
    private SensorReading convertTemperatureIfNeeded(SensorReading reading) {
        String configuredUnit = temperatureUnit;
        String currentUnit = reading.getUnit();

        if (!configuredUnit.equals(currentUnit)) {
            if ("K".equals(configuredUnit)) {
                double kelvin = temperatureConverter.celsiusToKelvin(reading.getValue());
                return new SensorReading(reading.getName(), "K", kelvin);
            } else if ("C".equals(configuredUnit)) {
                double celsius = temperatureConverter.kelvinToCelsius(reading.getValue());
                return new SensorReading(reading.getName(), "C", celsius);
            }
        }
        return reading;
    }
} 