package hr.fer.temperature_service.controller;

import hr.fer.temperature_service.model.Temperature;
import hr.fer.temperature_service.service.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/temperature")
public class TemperatureController {

    @Autowired
    private TemperatureService temperatureService;

    @GetMapping
    public Temperature getTemperature() {
        return temperatureService.getCurrentTemperature();
    }
} 