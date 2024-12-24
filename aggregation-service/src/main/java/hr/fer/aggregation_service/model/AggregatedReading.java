package hr.fer.aggregation_service.model;

import java.util.List;

public class AggregatedReading {
    private List<SensorReading> readings;

    public AggregatedReading(List<SensorReading> readings) {
        this.readings = readings;
    }

    public List<SensorReading> getReadings() {
        return readings;
    }

    public void setReadings(List<SensorReading> readings) {
        this.readings = readings;
    }
} 