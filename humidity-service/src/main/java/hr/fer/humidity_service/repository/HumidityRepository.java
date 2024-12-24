package hr.fer.humidity_service.repository;

import hr.fer.humidity_service.model.HumidityReading;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HumidityRepository extends JpaRepository<HumidityReading, Long> {
} 