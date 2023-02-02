package org.aggregation.services;

import org.aggregation.model.AggregationResponse;

import java.util.List;

public interface AggregationService {
            AggregationResponse getAggregatedDetails(
            List<String> shipmentOrderNumbers,
            List<String> trackOrderNumbers,
            List<String> pricingCountryCodes
    );
}
