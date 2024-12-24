package hr.fer.humidity_service.controller;

import hr.fer.humidity_service.model.Humidity;
import hr.fer.humidity_service.service.HumidityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/humidity")
public class HumidityController {

    @Autowired
    private HumidityService humidityService;

    @GetMapping
    public Humidity getHumidity() {
        return humidityService.getCurrentHumidity();
    }
} 