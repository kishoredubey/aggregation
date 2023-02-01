package org.aggregation.controllers;

import java.util.List;

import org.aggregation.dto.AggregationDto;
import org.aggregation.services.IAggregationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AggregationController {
	
	@Autowired
	IAggregationService aggregationService;

	@GetMapping(produces = "application/json")
	public ResponseEntity<AggregationDto> getProductPrice(
			@RequestParam List<String> shipmentOrderNumbers,
			@RequestParam List<String> trackOrderNumbers,
			@RequestParam List<String> pricingCountryCodes){
		AggregationDto aggregation = aggregationService.getAggregatedProductDetail(shipmentOrderNumbers, trackOrderNumbers, pricingCountryCodes);
		return new ResponseEntity<>(aggregation, HttpStatus.OK);
	}
}
