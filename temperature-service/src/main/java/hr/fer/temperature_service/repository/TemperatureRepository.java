package hr.fer.temperature_service.repository;

import hr.fer.temperature_service.model.TemperatureReading;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemperatureRepository extends JpaRepository<TemperatureReading, Long> {
} 