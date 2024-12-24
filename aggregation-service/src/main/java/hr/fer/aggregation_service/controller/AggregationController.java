package hr.fer.aggregation_service.controller;

import hr.fer.aggregation_service.model.AggregatedReading;
import hr.fer.aggregation_service.service.AggregationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/aggregate")
public class AggregationController {

    @Autowired
    private AggregationService aggregationService;

    @GetMapping
    public AggregatedReading getAggregatedReadings() {
        return aggregationService.getAggregatedReadings();
    }
} 