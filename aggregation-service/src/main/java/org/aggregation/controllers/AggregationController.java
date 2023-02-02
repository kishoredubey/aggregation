package org.aggregation.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.aggregation.model.AggregationResponse;
import org.aggregation.services.AggregationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AggregationController {
    private AggregationService aggregationService;

    public AggregationController(AggregationService aggregationService) {
        this.aggregationService = aggregationService;
    }

    @GetMapping("/aggregation")
    @Operation(summary = "Get aggregated response for Shipment status, Tracking or pricing")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found details about the requested data",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AggregationResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content)})
    public ResponseEntity<AggregationResponse> getProductPrice(
            @RequestParam(required = false) List<String> shipmentOrderNumbers,
            @RequestParam(required = false) List<String> trackOrderNumbers,
            @RequestParam(required = false) List<String> pricingCountryCodes) {
        AggregationResponse aggregation = aggregationService.getAggregatedDetails(shipmentOrderNumbers, trackOrderNumbers, pricingCountryCodes);
        return new ResponseEntity<>(aggregation, HttpStatus.OK);
    }
}
