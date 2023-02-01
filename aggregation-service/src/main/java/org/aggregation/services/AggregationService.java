package org.aggregation.services;

import org.aggregation.dto.AggregationResponse;

import java.util.List;

public interface AggregationService {
    public AggregationResponse getAggregatedDetails(
            List<String> shipmentOrderNumbers,
            List<String> trackOrderNumbers,
            List<String> pricingCountryCodes
    );
}
