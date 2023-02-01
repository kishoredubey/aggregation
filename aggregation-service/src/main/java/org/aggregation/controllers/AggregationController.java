package org.aggregation.controllers;

import org.aggregation.dto.AggregationResponse;
import org.aggregation.services.AggregationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AggregationController {
    AggregationService aggregationService;

    @Autowired
    public AggregationController(AggregationService aggregationService) {
        this.aggregationService = aggregationService;
    }

    @GetMapping("/aggregation")
    public ResponseEntity<AggregationResponse> getProductPrice(
            @RequestParam(required = false) List<String> shipmentOrderNumbers,
            @RequestParam(required = false) List<String> trackOrderNumbers,
            @RequestParam(required = false) List<String> pricingCountryCodes) {
        AggregationResponse aggregation = aggregationService.getAggregatedDetails(shipmentOrderNumbers, trackOrderNumbers, pricingCountryCodes);
        return new ResponseEntity<>(aggregation, HttpStatus.OK);
    }
}
