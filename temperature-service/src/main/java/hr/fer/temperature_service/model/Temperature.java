package hr.fer.temperature_service.model;

public class Temperature {
    private String name;
    private String unit;
    private double value;

    public Temperature() {
    }

    public Temperature(String name, String unit, double value) {
        this.name = name;
        this.unit = unit;
        this.value = value;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
} 